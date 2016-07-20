package com.secoo.bigdata.kaggle.talkingdata.service;

import java.util.Arrays;

import org.apache.commons.math3.distribution.PoissonDistribution;
import org.apache.log4j.Logger;
import org.junit.Test;

public class ForecastAgeServiceTest {
	Logger log = Logger.getLogger(getClass());

	@Test
	public void test() {
		ForecastAgeService s = new ForecastAgeService();
		double[] p = s.forecast(30.98684817720351, 9.61737115601204, 0.65793724042455, 0.3420627595754499);
		log.info(Arrays.toString(p));

	}

	@Test
	public void testPoisson() {
		PoissonDistribution pd = new PoissonDistribution(30.98684817720351);
		log.info(pd.getSupportLowerBound());
		log.info(pd.getSupportUpperBound());
		log.info(pd.probability(30));
		log.info(pd.probability(28));
		log.info(pd.probability(100));
		log.info(pd.probability(1));
		log.info(pd.probability(10));
		log.info(pd.probability(20));
	}

}
