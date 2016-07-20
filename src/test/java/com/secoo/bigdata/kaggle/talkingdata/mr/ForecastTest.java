package com.secoo.bigdata.kaggle.talkingdata.mr;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.junit.Test;
import org.mockito.Mockito;

public class ForecastTest {

	@SuppressWarnings("unchecked")
	@Test
	public void test() throws IOException, InterruptedException {
		Forecast.M map = new Forecast.M();
		Forecast.M.Context context = Mockito.mock(Forecast.M.Context.class);
		map.setup(context);

		List<String> lines = IOUtils
				.readLines(getClass().getResourceAsStream("/driver/kaggle_talkingdata_driver_train.txt"));
		for (String line : lines) {
			LongWritable key = null;
			Text value = new Text(line);
			map.map(key, value, context);
		}
	}

}
