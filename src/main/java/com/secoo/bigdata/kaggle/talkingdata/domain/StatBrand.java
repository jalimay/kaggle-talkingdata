package com.secoo.bigdata.kaggle.talkingdata.domain;

public class StatBrand extends Stat {
	private String phoneBrand;

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	@Override
	public String getDim() {
		return phoneBrand;
	}
}
