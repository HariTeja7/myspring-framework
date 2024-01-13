package com.myspring.helper;

public class StringUtils {

	private StringUtils() {

	}

	public static String toLowerCase(String str, int start, int end) {
		char[] charArray = str.toCharArray();
		for (int i = start; i < end; i++) {
			char ch = charArray[i];
			ch = Character.toLowerCase(ch);
			charArray[i] = ch;
		}
		return new String(charArray);
	}

}
