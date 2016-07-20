package com.secoo.bigdata.kaggle.talkingdata.service;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class WeightedService {
	double[] tmp;
	DescriptiveStatistics ds = new DescriptiveStatistics();

	/**
	 * 增加概率样本
	 * 
	 * @param sample
	 *            概率样本
	 * @param weight
	 *            样本权重
	 */
	public void add(double[] sample, double weight) {
		if (tmp == null) {
			tmp = weighted(normalize(sample), weight);
		} else {
			tmp = add(tmp, weighted(normalize(sample), weight));
		}
		ds.addValue(weight);
	}

	/**
	 * 求得所有概率样本的权重叠加结果
	 * 
	 * @return
	 */
	public double[] summary() {
		return weighted(tmp, 1 / ds.getSum());
	}

	/**
	 * 概率标准化，使得sample的和为1
	 * 
	 * @param sample
	 * @return
	 */
	private double[] normalize(double[] sample) {
		DescriptiveStatistics ds = new DescriptiveStatistics(sample);
		return weighted(sample, 1 / ds.getSum());
	}

	/**
	 * 对一组概率加权
	 * 
	 * @param sample
	 * @param weight
	 * @return
	 */
	private double[] weighted(double[] sample, double weight) {
		double[] t = new double[sample.length];
		for (int i = 0; i < sample.length; i++) {
			t[i] = sample[i] * weight;
		}
		return t;
	}

	/**
	 * 两组概率相加
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	private double[] add(double[] a, double[] b) {
		double[] t = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			t[i] = a[i] + b[i];
		}
		return t;
	}
}
