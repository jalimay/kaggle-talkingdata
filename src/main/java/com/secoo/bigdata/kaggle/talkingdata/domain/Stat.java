package com.secoo.bigdata.kaggle.talkingdata.domain;

/**
 * 统计指标
 * 
 * @author xiewei
 *
 */
public abstract class Stat {
	private int maleCnt;
	private int femaleCnt;
	private double ageMean;
	private double ageStd;

	public abstract String getDim();

	public int getMaleCnt() {
		return maleCnt;
	}

	public void setMaleCnt(int maleCnt) {
		this.maleCnt = maleCnt;
	}

	public int getFemaleCnt() {
		return femaleCnt;
	}

	public void setFemaleCnt(int femaleCnt) {
		this.femaleCnt = femaleCnt;
	}

	public double getAgeMean() {
		return ageMean;
	}

	public void setAgeMean(double ageMean) {
		this.ageMean = ageMean;
	}

	public double getAgeStd() {
		return ageStd;
	}

	public void setAgeStd(double ageStd) {
		this.ageStd = ageStd;
	}
}
