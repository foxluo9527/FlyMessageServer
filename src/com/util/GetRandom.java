package com.util;

import java.util.Random;

public class GetRandom {
	private static char[] checkwords= {'0','1','2','3','4','5','6','7','8','9'};
	public static String getRandom() {
		String words="";
		 for (int i = 0; i < 6; i++) {
				words=words+checkwords[new Random().nextInt(9)];
		}
		return words;
	}
}
