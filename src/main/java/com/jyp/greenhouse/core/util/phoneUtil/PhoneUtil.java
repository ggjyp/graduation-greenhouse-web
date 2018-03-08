package com.jyp.greenhouse.core.util.phoneUtil;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by oplus on 2017/4/7.
 */
public class PhoneUtil {
    /**
     * 短信接口二，触发类模板短信接口，可以设置动态参数变量。适合：验证码、订单短信等。
     */
    public static String sms_api(String phone, String code) {
        String msg = "";
        Map<String, String> para = new HashMap<String, String>();
        /**
         * 目标手机号码，多个以“,”分隔，一次性调用最多100个号码，示例：139********,138********
         */
        para.put("mob", phone);
        /**
         * 微米账号的接口UID
         */
        para.put("uid", "0DacBS5fTyAA");
        /**
         * 微米账号的接口密码
         */
        para.put("pas", "q3vcr6r7");
        /**
         * 接口返回类型：json、xml、txt。默认值为txt
         */
        para.put("type", "json");
        /**
         * 短信模板cid，通过微米后台创建，由在线客服审核。必须设置好短信签名，签名规范：
         * 1、模板内容一定要带签名，签名放在模板内容的最前面；
         * 2、签名格式：【***】，签名内容为三个汉字以上（包括三个）；
         * 3、短信内容不允许双签名，即短信内容里只有一个“【】”
         */
        para.put("cid", "w1LxARBREA9e");
        /**
         * 传入模板参数。
         *
         * 短信模板示例：
         * 【微米】您的验证码是：%P%，%P%分钟内有效。如非您本人操作，可忽略本消息。
         *
         * 传入两个参数：
         * p1：610912
         * p2：3
         * 最终发送内容：
         * 【微米】您的验证码是：610912，3分钟内有效。如非您本人操作，可忽略本消息。
         */
        para.put("p1", code);
        para.put("p2", "3");
        try {

            String res = HttpClientHelper.convertStreamToString(
                    HttpClientHelper.post(
                            "http://api.weimi.cc/2/sms/send.html", para),
                    "UTF-8");

            WM wm = JSON.parseObject(res, WM.class);
            msg = wm.getCode();
        } catch (Exception e) {
            e.printStackTrace();
            msg = "error";
        }
        return msg;
    }


    public static void main(String[] a) {
        // 测试短信接口一
//        sms_api1();

        // 测试短信接口二
        System.out.print(sms_api("13757136643", "123456"));

        /*
        注意：以上参数传入时不包括“<>”符号
        */
    }
}

class WM {
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}