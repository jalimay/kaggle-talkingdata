package com.secoo.bigdata.kaggle.talkingdata.domain;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;

public class DriverTrainTest {
	Logger log = Logger.getLogger(getClass());

	@Test
	public void test() throws IOException {
		Gson gson = new Gson();
		List<String> lines = IOUtils
				.readLines(getClass().getResourceAsStream("/driver/kaggle_talkingdata_driver_train.txt"));
		for (String line : lines) {
			DriverTrain dt = DriverTrain.make(line);
			log.info(gson.toJson(dt));
		}
	}

}
