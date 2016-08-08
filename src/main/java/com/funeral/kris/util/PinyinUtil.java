package com.funeral.kris.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * Created by kris.cheng on 8/8/2016.
 */
public class PinyinUtil {
    public static String getHanyuPinyin(String str) {
        String result = null;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < str.length(); ++i) {
            String c = getCharacterPinYin(str.charAt(i));
            sb.append(c);
        }
        return sb.toString().trim();
    }

    public static String getCharacterPinYin(char c) {
        HanyuPinyinOutputFormat format = null;
        format = new HanyuPinyinOutputFormat();
        String[] pinyin = null;
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        try {
            pinyin = PinyinHelper.toHanyuPinyinStringArray(c, format);
        } catch(BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        if(null == pinyin)
            return null;
        return pinyin[0];
    }
}
