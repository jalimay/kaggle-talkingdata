package com.secoo.bigdata.kaggle.talkingdata.service;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.junit.Test;

public class WeightedServiceTest {
	Logger log = Logger.getLogger(getClass());

	@Test
	public void test() {
		WeightedService ws = new WeightedService();
		ws.add(new double[] { 0, 0.5, 0, 0.5 }, 1.0 / 3);
		ws.add(new double[] { 0, 0.5, 0.5, 0 }, 1.0 / 6);
		ws.add(new double[] { 0, 1, 0, 0 }, 1.0 / 7);
		ws.add(new double[] { 20, 2, 2, 2 }, 1.0 / 2);
		log.info(Arrays.toString(ws.summary()));
	}

}
