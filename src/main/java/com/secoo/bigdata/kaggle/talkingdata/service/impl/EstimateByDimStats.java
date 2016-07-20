package com.secoo.bigdata.kaggle.talkingdata.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.secoo.bigdata.kaggle.talkingdata.domain.Stat;
import com.secoo.bigdata.kaggle.talkingdata.service.IEstimate;
import com.secoo.bigdata.kaggle.talkingdata.service.StatFactory;

public class EstimateByDimStats implements IEstimate {
	private final Map<String, Stat> statMap = new HashMap<String, Stat>();
	private String statDataPath;
	private StatFactory statFactory;
	private Class<? extends Stat> statClass;

	Map<String, Stat> getStatMap() {
		return statMap;
	}

	public void setStatDataPath(String statDataPath) {
		this.statDataPath = statDataPath;
	}

	public void setStatFactory(StatFactory statFactory) {
		this.statFactory = statFactory;
	}

	@SuppressWarnings("unchecked")
	public void setStatClass(String statClass) throws ClassNotFoundException {
		this.statClass = (Class<? extends Stat>) Class.forName(statClass);
	}

	public void load() throws IOException {
		List<String> lines = IOUtils.readLines(getClass().getResourceAsStream(statDataPath));
		for (String line : lines) {
			Stat s = statFactory.make(line, statClass);
			if (s != null)
				statMap.put(s.getDim(), s);
		}
	}

	@Override
	public double[] estimateAge(String dim) {
		Stat s = statMap.get(dim);
		if (s != null) {
			return new double[] { s.getAgeMean(), s.getAgeStd() == 0 ? IEstimate.AGE_STD : s.getAgeStd() };
		}
		return new double[] { IEstimate.AGE_MEAN, IEstimate.AGE_STD };
	}

	@Override
	public double[] estimateGender(String dim) {
		Stat s = statMap.get(dim);
		if (s != null) {
			return getGenderProbability(s.getMaleCnt(), s.getFemaleCnt());
		}
		return getGenderProbability(IEstimate.MALE_CNT, IEstimate.FEMALE_CNT);
	}

	private double[] getGenderProbability(double male, double female) {
		double sum = male + female;
		return new double[] { male / sum, female / sum };
	}
}
