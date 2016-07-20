package com.secoo.bigdata.kaggle.talkingdata.domain;

public class DriverTest {
	private long deviceId;
	private String phoneBrand;
	private String deviceModel;
	private String appIds;

	public static DriverTest make(String in) {
		DriverTest dt = new DriverTest();
		String[] cols = in.split("\1");
		int i = 0;
		dt.setDeviceId(Long.valueOf(cols[i++]));
		dt.setPhoneBrand(cols[i++]);
		dt.setDeviceModel(cols[i++]);
		dt.setAppIds(cols[i++]);
		return dt;
	}

	public long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(long deviceId) {
		this.deviceId = deviceId;
	}

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

	public String getAppIds() {
		return appIds;
	}

	public void setAppIds(String appIds) {
		this.appIds = appIds;
	}

}
