package com.sw.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

public class ChineseUtil {
	/**
	 * 
	 * @param str
	 *            需要提取首字母的汉字
	 * @return 汉字的首字母
	 */
	public static String getPinYinHeadChar(String str) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.UPPERCASE);// 小写
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
		format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v

		String convert = "";
		for (int j = 0; j < str.length(); j++) {
			char word = str.charAt(j);
			// 提取汉字的首字母
			try {
				String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(
						word, format);
				if (pinyinArray != null) {
					convert += pinyinArray[0].charAt(0);
				} else {
					convert += word;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return convert;
	}

	public static void main(String args[]) {
		String str = "你好！";

		System.out.println(ChineseUtil.getPinYinHeadChar(str));
	}
}
