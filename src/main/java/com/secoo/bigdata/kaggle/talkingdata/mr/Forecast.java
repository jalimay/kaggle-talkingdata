package com.secoo.bigdata.kaggle.talkingdata.mr;

import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

import com.secoo.bigdata.kaggle.talkingdata.domain.DriverTest;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatApp;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatBrand;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatDeviceModel;
import com.secoo.bigdata.kaggle.talkingdata.service.ForecastAgeService;
import com.secoo.bigdata.kaggle.talkingdata.service.IEstimate;
import com.secoo.bigdata.kaggle.talkingdata.service.StatFactory;
import com.secoo.bigdata.kaggle.talkingdata.service.WeightedService;
import com.secoo.bigdata.kaggle.talkingdata.service.impl.EstimateByDimStats;

public class Forecast {
	public static class M extends Mapper<LongWritable, Text, LongWritable, Text> {
		IEstimate estimateByApp;
		IEstimate estimateByBrand;
		IEstimate estimateByDeviceModel;
		ForecastAgeService forecastAgeService;
		Logger log = Logger.getLogger(getClass());

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			try {
				EstimateByDimStats estimateByDeviceModel = new EstimateByDimStats();
				estimateByDeviceModel.setStatClass(StatDeviceModel.class.getName());
				estimateByDeviceModel.setStatDataPath("/stat/kaggle_talkingdata_stat_device_model.txt");
				estimateByDeviceModel.setStatFactory(new StatFactory());
				estimateByDeviceModel.load();
				this.estimateByDeviceModel = estimateByDeviceModel;

				EstimateByDimStats estimateByBrand = new EstimateByDimStats();
				estimateByBrand.setStatClass(StatBrand.class.getName());
				estimateByBrand.setStatDataPath("/stat/kaggle_talkingdata_stat_brand.txt");
				estimateByBrand.setStatFactory(new StatFactory());
				estimateByBrand.load();
				this.estimateByBrand = estimateByBrand;

				EstimateByDimStats estimateByApp = new EstimateByDimStats();
				estimateByApp.setStatClass(StatApp.class.getName());
				estimateByApp.setStatDataPath("/stat/kaggle_talkingdata_stat_appid.txt");
				estimateByApp.setStatFactory(new StatFactory());
				estimateByApp.load();
				this.estimateByApp = estimateByApp;

				this.forecastAgeService = new ForecastAgeService();
			} catch (Exception e) {
				throw new IOException(e);
			}
		}

		@Override
		protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
			try {
				DriverTest dt = DriverTest.make(value.toString());

				dt.getDeviceId();
				List<String> appIds = splitAppIds(dt.getAppIds());
				String deviceModel = dt.getDeviceModel();
				String brand = dt.getPhoneBrand();

				WeightedService ws = new WeightedService();
				double[] brandAge = estimateByBrand.estimateAge(brand);
				double[] brandGender = estimateByBrand.estimateGender(brand);
				ws.add(forecastAgeService.forecast(brandAge[0], brandAge[1], brandGender[0], brandGender[1]),
						1 / brandAge[0]);
				double[] deviceModelAge = estimateByDeviceModel.estimateAge(deviceModel);
				double[] deviceModelGender = estimateByDeviceModel.estimateGender(deviceModel);
				ws.add(forecastAgeService.forecast(deviceModelAge[0], deviceModelAge[1], deviceModelGender[0],
						deviceModelGender[1]), 1 / deviceModelAge[0]);
				for (String appId : appIds) {
					double[] appIdAge = estimateByApp.estimateAge(appId);
					double[] appIdGender = estimateByApp.estimateGender(appId);
					ws.add(forecastAgeService.forecast(appIdAge[0], appIdAge[1], appIdGender[0], appIdGender[1]),
							1 / appIdAge[0]);
				}

				double[] probability = ws.summary();

				Text out = new Text(Arrays.toString(probability).replaceAll("\\[|\\]", "").replace(", ", "\t"));
				context.write(new LongWritable(dt.getDeviceId()), out);
			} catch (Exception e) {
				context.getCounter("error", e.getMessage()).increment(1);
			}
		}

		private List<String> splitAppIds(String str) {
			if (StringUtils.isEmpty(str) || str.equals("\\N")) {
				return new LinkedList<String>();
			} else {
				List<String> appIds = new LinkedList<String>();
				CollectionUtils.addAll(appIds, str.split("\2"));
				return appIds;
			}
		}
	}
}
