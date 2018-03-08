package com.jyp.greenhouse.core.util;


import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

/**
 * Created by oplsu on 2016/10/26.
 */
public class StringUtil {


    //随机数
    public static String getRandNum(int length) {
       /* String[] rNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};*/
        Random r = new Random();
        String s = "";
        for (int i = 0; i < length; i++) {
            int x = r.nextInt(10);
            s += x;
        }
        return s;
    }

    //随机字符验证码(包含大小写)
    public static String getRandChar(int length) {
        String[] rNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f",
                "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w",
                "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random r = new Random();
        String s = "";
        for (int i = 0; i < length; i++) {
            int x = r.nextInt(rNum.length);
            s += rNum[x];
        }
        return s;
    }

    //随机字符验证码(只有大写)
    public static String getRandCharUpper(int length) {
        String[] rNum = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        Random r = new Random();
        String s = "";
        for (int i = 0; i < length; i++) {
            int x = r.nextInt(rNum.length);
            s += rNum[x];
        }
        return s;
    }

    //得到随机上传文件名（不含后缀）
    public static String getRandFileName() {
        String now = TimeUtil.getNowTimestamp() + "";
        String r = getRandChar(6);
        return r + now;
    }


    private static SecureRandom random = new SecureRandom();

    //    随机字符串
    public static String randomString(int length) {
        String str = new BigInteger(130, random).toString(32);
        return str.substring(0, length);
    }

    //   字符串是否为空
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    //     数组中是否存在某值
    public static boolean str_is_exist(String[] arr, String targetValue) {
        if (arr != null)
            for (String s : arr) {
                if (targetValue != null && s.equals(targetValue.trim()))
                    return true;
            }
        return false;
    }

    //     首字母大写
    public static String captureName(String name) {
        char[] cs = name.toCharArray();
        cs[0] -= 32;
        return String.valueOf(cs);

    }

    //     首字母小写
    public static String lowerName(String name) {
        char[] cs = name.toCharArray();
        cs[0] += 32;
        return String.valueOf(cs);

    }

    public static String delLast(String str) {
        return str.substring(0, str.length() - 1);

    }

    //    删除数组总指定字符串 返回以英文逗号分隔的字符串
    public static String delstrFromArr(String[] strarr, String str) {
        String newstr = "";
        for (String s : strarr) {
            if (s.equals(str))
                continue;
            else
                newstr += s + ",";
        }
        if (newstr.equals(""))
            return newstr;
        else
            return newstr.substring(0, newstr.length() - 1);
    }

    public static String toChinese(String string) {
        String[] s1 = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] s2 = {"十", "百", "千", "万", "十", "百", "千", "亿", "十", "百", "千"};
        String result = "";
        int n = string.length();
        for (int i = 0; i < n; i++) {
            int num = string.charAt(i) - '0';
            if (i != n - 1 && num != 0) {
                result += s1[num] + s2[n - 2 - i];
            } else {
                result += s1[num];
            }
        }
        return result;

    }

    public static void main(String[] args) throws Exception {
        String id = IdGenerator.uuid19();
        String str = id + "###UUU" + IdGenerator.uuid32();
        System.out.println(str);
        System.out.println(str.substring(0, 19));
    }
}