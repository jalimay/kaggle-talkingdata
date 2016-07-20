package com.secoo.bigdata.kaggle.talkingdata.service;

import com.secoo.bigdata.kaggle.talkingdata.domain.Stat;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatApp;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatBrand;
import com.secoo.bigdata.kaggle.talkingdata.domain.StatDeviceModel;

public class StatFactory {

	int toInt(String col) {
		if (col.equals("\\N"))
			return 0;
		return Integer.valueOf(col);
	}

	double toDouble(String col) {
		if (col.equals("\\N"))
			return 0;
		return Double.valueOf(col);
	}

	public Stat make(String in, Class<? extends Stat> cl) {
		try {
			if (cl.equals(StatApp.class)) {
				StatApp s = new StatApp();
				String[] cols = in.split("\1");
				int i = 0;
				s.setAppId(Long.valueOf(cols[i++]));
				s.setMaleCnt(toInt(cols[i++]));
				s.setFemaleCnt(toInt(cols[i++]));
				s.setAgeMean(toDouble(cols[i++]));
				s.setAgeStd(toDouble(cols[i++]));
				return s;
			}
			if (cl.equals(StatBrand.class)) {
				StatBrand s = new StatBrand();
				String[] cols = in.split("\1");
				int i = 0;
				s.setPhoneBrand(cols[i++]);
				s.setMaleCnt(toInt(cols[i++]));
				s.setFemaleCnt(toInt(cols[i++]));
				s.setAgeMean(toDouble(cols[i++]));
				s.setAgeStd(toDouble(cols[i++]));
				return s;
			}
			if (cl.equals(StatDeviceModel.class)) {
				StatDeviceModel s = new StatDeviceModel();
				String[] cols = in.split("\1");
				int i = 0;
				s.setPhoneBrand(cols[i++]);
				s.setDeviceModel(cols[i++]);
				s.setMaleCnt(toInt(cols[i++]));
				s.setFemaleCnt(toInt(cols[i++]));
				s.setAgeMean(toDouble(cols[i++]));
				s.setAgeStd(toDouble(cols[i++]));
				return s;
			}
		} catch (Exception e) {
		}
		return null;
	}
}
