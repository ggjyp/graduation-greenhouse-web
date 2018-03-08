package com.jyp.greenhouse.web.constants;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;


/**
 * 常量
 */
public final class Constants {
    private Constants() {
        throw new AssertionError();
    }

    /**
     * 程序默认字符集
     */
    public static final Charset ENCODING_UTF_8 = StandardCharsets.UTF_8;
    /**
     * 定义统一Locale.CHINA,程序中所有和Locale相关操作均默认使用此Locale
     */
    public static final Locale LOCALE_CHINA = Locale.CHINA;


    public static final String API_KEY = "API_KEY";
    public static final String SESSION_USER = "userid";
    public static final String SESSION_ADMIN = "adminid";


    public static final int ONEDAY = 86400;
    public static final BigDecimal BD100 = new BigDecimal(100);
    public static final BigDecimal BD1 = new BigDecimal(1);


    public static final String RECORD_CONTENT = "record_content";
    /**
     * 成功或失败的字符串常量
     */
    public static final String SUCCESS = "成功";
    public static final String SUCCESS_EH = "success";
    public static final String FAIL = "失败";
    public static final String STATE = "state";
    public static final String MSG = "msg";
    public static final String MSG_DETAIL = "msg_detail";

    public static final String SUCC = "00000";
    public static final String SYSERROR = "系统故障";
    public static final String SYSERROR_DETAIL = "系统故障,请稍后再试";
    public static final String PASSEERROR = "密码错误";
    public static final String UPDATEERROR = "更改失败";
    public static final String UPLOADFAIL = "文件上传失败";
    public static final String PARAMERROR = "参数不合法";
    public static final String ADDERROR = "添加失败";
    public static final String DELERROR = "删除失败";
    public static final String SETERROR = "设置失败";
    public static final String ACTIONERROR = "操作失败";

    /**
     * name常量
     */
    public static final String webview = "webview";//webview
    public static final String education_idea = "education_idea";//教育理念
    public static final String game_intro = "game_intro";//游戏介绍


    /**
     * 显示 隐藏常量
     */
    public static final String display = "0";
    public static final String hidden = "1";

    /**
     * ping++ 配置所需信息
     */
    public static final String ping_appid = "";
    public static final String ping_appkey = "";
    public static final String ping_privateKeyFilePath = "/WEB-INF/classes/pingpp/rsa_private_key.pem";
    public static final String ping_pubKeyPath = "/WEB-INF/classes/pingpp/pingpp_public_key.pem";

    /**
     * 微信配置所需的信息
     */
    public final static String wx_appid = "";
    public final static String wx_app_secret = "";
    public final static String wx_app_token = "";
    public final static boolean wx_app_issecret = true;
    public final static String wx_app_encodekey = "";

    /**
     * 微信开放第三方平台配置所需的信息
     */
    public final static String component_appid = ""; //第三方平台appid
    public final static String component_appsecret = "";//第三方平台appsecret


}
