package com.jyp.greenhouse.core.security;

/**
 * Created by wjh on 2016/3/12.
 */


import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.web.constants.Constants;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
    public static final String MD5 = "MD5";

//    public static final String DEFAULT_SALT = "13f3edb209dc958e5d483ba09d1715d4";
    public static final String DEFAULT_SALT = "jyp";
    /**
     * 将源字符串使用MD5加密为字节数组
     *
     * @param source
     * @return
     */
    public static byte[] encode2bytes(String source) {
        byte[] result = null;
        try {
            MessageDigest md = MessageDigest.getInstance(MD5);
            md.reset();
            md.update(source.getBytes(Constants.ENCODING_UTF_8));
            result = md.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将源字符串使用MD5加密为32位16进制数
     *
     * @param source
     * @return
     */
    public static String encode2hex(String source) {
        if (StringUtil.isBlank(source))
            return "";
        byte[] data = encode2bytes(source);


        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            String hex = Integer.toHexString(0xff & data[i]);

            if (hex.length() == 1) {
                hexString.append('0');
            }

            hexString.append(hex);
        }

        return hexString.toString();
    }

    /**
     * 验证字符串是否匹配
     *
     * @param unknown 待验证的字符串
     * @param okHex   使用MD5加密过的16进制字符串
     * @return 匹配返回true，不匹配返回false
     */
    public static boolean validate(String unknown, String okHex) {
        return okHex.equals(encode2hex(unknown));
    }

    public static String getSalt() {
        String salt = DEFAULT_SALT;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            //要生成多少位，只需要修改这里即可128, 192或256
            SecretKey sk = kg.generateKey();
            byte[] b = sk.getEncoded();
            salt = EnryptUtil.byteToHexString(b);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("没有此算法。");
        }
        return salt;
    }


    public static void main(String[] strs)throws Exception {
        System.out.println(encode2hex("111111"));
        System.out.println(encode2hex(encode2hex("111111")+ DEFAULT_SALT));

    }
}
