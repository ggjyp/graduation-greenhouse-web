package com.jyp.greenhouse.web.constants;

public class errorCode {

    public static final String SUCCESS = "0"; //成功
    public static final String SYSTEM_ERROR = "101";//系统故障
    public static final String PARAMETER_ERROR = "102"; //参数不合法
    public static final String SMS_ERROR = "103"; //手机验证码发送失败
    public static final String MOBILE_ILLEGAL = "104"; //手机号格式不正确
    public static final String MOBILE_CODEERR = "105"; //手机验证码错误
    public static final String CODEERR = "106"; //验证码错误
    public static final String ACTIONEERR = "107"; //操作失败

    public static final String USER_NOT_EXISTS = "201";//用户不存在
    public static final String MOBILE_UNDER_USED = "202";//手机号已使用
    public static final String MOBILE_NOE_EXISTS = "203";//手机号不存在
    public static final String LOGIN_EXPIRED = "204";//登录过期
    public static final String REWARD_ERROR = "205";//领取奖励失败
    public static final String BIND_ALEADY = "206";//您已绑定其他角色
    public static final String BIND_ERROR = "207";//绑定失败

    public static final String DB_WRITTEN_ERROR = "307";//写入数据库失败

    public static final String ZH_SUCCESS = "成功";
    public static final String ZH_SYSTEM_ERROR = "系统故障";
    public static final String ZH_PARAMETER_ERROR = "参数不合法";
    public static final String ZH_SMS_ERROR = "手机验证码发送失败";
    public static final String ZH_MOBILE_ILLEGAL = "手机号格式不正确";
    public static final String ZH_MOBILE_CODEERR = "手机验证码错误";
    public static final String ZH_CODEERR = "验证码错误";
    public static final String ZH_ACTIONEERR = "操作失败";
    public static final String ZH_USER_NOT_EXISTS = "用户不存在";
    public static final String ZH_MOBILE_UNDER_USED = "手机号已使用";
    public static final String ZH_MOBILE_NOE_EXISTS = "手机号不存在";
    public static final String ZH_LOGIN_EXPIRED = "登录过期";
    public static final String ZH_REWARD_ERROR = "领取奖励失败";
    public static final String ZH_BIND_ALEADY = "您已绑定其他角色";
    public static final String ZH_BIND_ERROR = "绑定失败";
    public static final String ZH_DB_WRITTEN_ERROR = "写入数据库失败";

    public static String getMsg(String code) {
        String msg = null;
        switch (code) {
            case SUCCESS:
                msg = ZH_SUCCESS;
                break;
            case SYSTEM_ERROR:
                msg = ZH_SYSTEM_ERROR;
                break;
            case PARAMETER_ERROR:
                msg = ZH_PARAMETER_ERROR;
                break;
            case USER_NOT_EXISTS:
                msg = ZH_USER_NOT_EXISTS;
                break;
            case MOBILE_UNDER_USED:
                msg = ZH_MOBILE_UNDER_USED;
                break;
            case MOBILE_NOE_EXISTS:
                msg = ZH_MOBILE_NOE_EXISTS;
                break;
            case LOGIN_EXPIRED:
                msg = ZH_LOGIN_EXPIRED;
                break;
            case DB_WRITTEN_ERROR:
                msg = ZH_DB_WRITTEN_ERROR;
                break;
            default:
                msg = "";
        }
        return msg;
    }


}
