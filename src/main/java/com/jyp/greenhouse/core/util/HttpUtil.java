package com.jyp.greenhouse.core.util;

import com.alibaba.fastjson.JSONObject;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Author   : jyp
 * Date     : 2017-05-04 17:31
 * Describe :
 */
public class HttpUtil {
    /**
     * 访问urlTemp并获取JSON对象
     * @param urlTemp
     * @return
     */
    public static JSONObject getHtmlJsonByUrl(String urlTemp){
        URL url = null;
        InputStreamReader input = null;
        HttpURLConnection conn;
        JSONObject jsonObj = null;
        try {
            url = new URL(urlTemp);
            conn = (HttpURLConnection) url.openConnection();
            //授权
            conn.setRequestProperty("Authorization", "Basic YWRtaW46cGFzc3dvcmQ=");
            //租户身份认证
            conn.setRequestProperty("X-SiteWhere-Tenant", "sitewhere1234567890");
            input = new InputStreamReader(conn.getInputStream(),"utf-8");
            Scanner inputStream = new Scanner(input);
            StringBuffer sb = new StringBuffer();
            while (inputStream.hasNext()) {
                sb.append(inputStream.nextLine());
            }
            jsonObj = JSONObject.parseObject(sb.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonObj;
    }
}
