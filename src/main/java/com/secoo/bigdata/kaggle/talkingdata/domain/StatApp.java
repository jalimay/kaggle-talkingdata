package com.secoo.bigdata.kaggle.talkingdata.domain;

public class StatApp extends Stat {
	private long appId;

	public long getAppId() {
		return appId;
	}

	public void setAppId(long appId) {
		this.appId = appId;
	}

	@Override
	public String getDim() {
		return String.valueOf(appId);
	}
}
