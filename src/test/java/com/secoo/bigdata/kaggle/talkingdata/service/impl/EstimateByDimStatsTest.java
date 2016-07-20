package com.secoo.bigdata.kaggle.talkingdata.service.impl;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import com.secoo.bigdata.kaggle.talkingdata.domain.StatApp;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatBrand;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatDeviceModel;
import com.secoo.bigdata.kaggle.talkingdata.service.StatFactory;

public class EstimateByDimStatsTest {
	Logger log = Logger.getLogger(getClass());

	@Test
	public void testLoadApp() throws IOException, ClassNotFoundException {
		EstimateByDimStats e = new EstimateByDimStats();
		e.setStatClass(StatApp.class.getName());
		e.setStatDataPath("/stat/kaggle_talkingdata_stat_appid.txt");
		e.setStatFactory(new StatFactory());
		e.load();

		Assert.assertEquals(13762, e.getStatMap().size());
	}

	@Test
	public void testLoadBrand() throws IOException, ClassNotFoundException {
		EstimateByDimStats e = new EstimateByDimStats();
		e.setStatClass(StatBrand.class.getName());
		e.setStatDataPath("/stat/kaggle_talkingdata_stat_brand.txt");
		e.setStatFactory(new StatFactory());
		e.load();

		Assert.assertEquals(120, e.getStatMap().size());
	}

	@Test
	public void testLoadDeviceModel() throws IOException, ClassNotFoundException {
		EstimateByDimStats e = new EstimateByDimStats();
		e.setStatClass(StatDeviceModel.class.getName());
		e.setStatDataPath("/stat/kaggle_talkingdata_stat_device_model.txt");
		e.setStatFactory(new StatFactory());
		e.load();

		Assert.assertEquals(1486, e.getStatMap().size());
	}

	@Test
	public void testEstimateGender() throws ClassNotFoundException, IOException {
		EstimateByDimStats e = new EstimateByDimStats();
		e.setStatClass(StatBrand.class.getName());
		e.setStatDataPath("/stat/kaggle_talkingdata_stat_brand.txt");
		e.setStatFactory(new StatFactory());
		e.load();

		double[] gender = e.estimateGender("小米");
		log.info(gender[0] + "," + gender[1]);

		gender = e.estimateGender("XXPP");
		log.info(gender[0] + "," + gender[1]);
	}

	@Test
	public void testEstimateAge() throws ClassNotFoundException, IOException {
		EstimateByDimStats e = new EstimateByDimStats();
		e.setStatClass(StatBrand.class.getName());
		e.setStatDataPath("/stat/kaggle_talkingdata_stat_brand.txt");
		e.setStatFactory(new StatFactory());
		e.load();

		double[] gender = e.estimateAge("小米");
		log.info(gender[0] + "," + gender[1]);

		gender = e.estimateAge("XXPP");
		log.info(gender[0] + "," + gender[1]);
	}
}
