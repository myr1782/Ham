package cn.sjcloud.apm.core.util;

public class StringUtil {
	public static String toggleFirstLetter(String str) {
		String firstLetter = str.substring(0, 1);
		return str.replaceFirst(firstLetter, firstLetter.toUpperCase());
	}
}

