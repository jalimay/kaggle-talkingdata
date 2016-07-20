package com.secoo.bigdata.kaggle.talkingdata.domain;

public class StatDeviceModel extends Stat {
	private String phoneBrand;
	private String deviceModel;

	public String getPhoneBrand() {
		return phoneBrand;
	}

	public void setPhoneBrand(String phoneBrand) {
		this.phoneBrand = phoneBrand;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	@Override
	public String getDim() {
		return phoneBrand + "_" + deviceModel;
	}
}
