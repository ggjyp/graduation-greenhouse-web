package com.jyp.greenhouse.core.util;



import com.jyp.greenhouse.web.constants.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtil {

    private UrlUtil() {
        throw new AssertionError();
    }

    public static String encode(String url) {
        if (StringUtil.isBlank(url))
            return "";
        try {
            return URLEncoder.encode(url, Constants.ENCODING_UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public static String decode(String url) {
        if (StringUtil.isBlank(url))
            return "";
        try {
            return URLDecoder.decode(url, Constants.ENCODING_UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            return url;
        }
    }

    public static void main(String[] args) {
        System.out.println(decode("%E7%94%B5%E4%BB%8B%E8%B4%A8%E7%AE%80%E4%BB%8B"));
    }

}
