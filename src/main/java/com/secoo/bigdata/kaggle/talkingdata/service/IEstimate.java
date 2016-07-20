package com.secoo.bigdata.kaggle.talkingdata.service;

public interface IEstimate {
	public static double MALE_CNT = 47904;
	public static double FEMALE_CNT = 26741;
	public static double AGE_MEAN = 31.410342286824303;
	public static double AGE_STD = 9.868668865110184;

	public double[] estimateAge(String dim);

	/**
	 * 
	 * @param dim
	 * @return [male, female]
	 */
	public double[] estimateGender(String dim);
}
