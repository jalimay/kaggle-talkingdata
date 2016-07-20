package com.secoo.bigdata.kaggle.talkingdata.domain;

/**
 * F23- F24-26 F27-28 F29-32 F33-42 F43+ M22- M23-26 M27-28 M29-31 M32-38 M39+
 * 
 * @author xiewei
 *
 */
public class Group {
	public static final Group[] GROUPS = new Group[] { new Group("F", 0, 23), new Group("F", 24, 26),
			new Group("F", 27, 28), new Group("F", 29, 32), new Group("F", 33, 42), new Group("F", 43, 100),
			new Group("M", 0, 22), new Group("M", 23, 26), new Group("M", 27, 28), new Group("M", 29, 31),
			new Group("M", 32, 38), new Group("M", 39, 100) };
	String gender;
	int ageBottom;
	int ageTop;

	public Group(String gender, int ageBottom, int ageTop) {
		this.gender = gender;
		this.ageBottom = ageBottom;
		this.ageTop = ageTop;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAgeBottom() {
		return ageBottom;
	}

	public void setAgeBottom(int ageBottom) {
		this.ageBottom = ageBottom;
	}

	public int getAgeTop() {
		return ageTop;
	}

	public void setAgeTop(int ageTop) {
		this.ageTop = ageTop;
	}
}
