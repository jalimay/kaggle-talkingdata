package com.secoo.bigdata.kaggle.talkingdata.service;

import org.apache.commons.math3.distribution.PoissonDistribution;

import com.secoo.bigdata.kaggle.talkingdata.domain.Group;

public class ForecastAgeService {
	public double[] forecast(double meanAge, double stdAge, double maleProbability, double femaleProbability) {
		PoissonDistribution nd1 = new PoissonDistribution(meanAge);
		double[] p = new double[Group.GROUPS.length];
		int i = 0;
		for (Group group : Group.GROUPS) {
			double tp = 0;
			for (int age = group.getAgeBottom(); age <= group.getAgeTop(); age++) {
				tp += nd1.probability(age);
			}
			if (group.getGender().equals("F"))
				p[i++] = tp * femaleProbability;
			else
				p[i++] = tp * maleProbability;
		}
		return p;
	}
}
