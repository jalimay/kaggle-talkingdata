package com.secoo.bigdata.kaggle.talkingdata.domain;

public class DriverTrain {
	private long deviceId;
	private String phoneBrand;
	private String deviceModel;
	private String appIds;
	private int age;
	private String gender;
	private String groupName;

	public static DriverTrain make(String in) {
		DriverTrain dt = new DriverTrain();
		String[] cols = in.split("\1");
		int i = 0;
		dt.setDeviceId(Long.valueOf(cols[i++]));
		dt.setPhoneBrand(cols[i++]);
		dt.setDeviceModel(cols[i++]);
		dt.setAppIds(cols[i++]);
		dt.setAge(Integer.valueOf(cols[i++]));
		dt.setGender(cols[i++]);
		dt.setGroupName(cols[i++]);
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
