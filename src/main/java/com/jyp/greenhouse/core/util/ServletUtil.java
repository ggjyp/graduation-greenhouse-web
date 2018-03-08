package com.jyp.greenhouse.core.util;


import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

/**
 * Created by oplus on 16/12/18.
 */
public class ServletUtil {


    //得到参数
    //得到IP地址
    public static String getIp() {
        HttpServletRequest request = getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }
        }
        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        if (ipAddress.contains(":")) ipAddress = ipAddress.substring(0, ipAddress.lastIndexOf(":"));
        return ipAddress;
    }


    //得到request
    public static HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }

    //得到response
    public static HttpServletResponse getResponse() {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        return response;
    }

    //设置Session
    public static void setSession(String name, Object obj) {
        HttpServletRequest request = getRequest();
        request.getSession().setAttribute(name, obj);
    }

    //获取Session
    public static Object getSession(String name) {
        HttpServletRequest request = getRequest();
        return request.getSession().getAttribute(name);
    }

    //得到参数
    public static Object getParam(String name) {
        HttpServletRequest request = getRequest();
        return request.getParameter(name);
    }


    //设置Attribute参数
    public static void setRequest(String name, String value) {
        HttpServletRequest request = getRequest();
        request.setAttribute(name, value);
    }

    //得到Attribute参数
    public static Object getAttribute(String name) {
        HttpServletRequest request = getRequest();
        return request.getAttribute(name);
    }

    public static void sendText(String text) {
        Writer writer = null;
        try {
            HttpServletResponse response = getResponse();
            writer = response.getWriter();
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isMultipartContent(HttpServletRequest request) {
        if (!"post".equals(request.getMethod().toLowerCase())) {
            return false;
        }

        String contentType = request.getContentType();
        return contentType != null && contentType.toLowerCase().startsWith("multipart/");
    }


    /**
     * 是否为ajax请求
     *
     * @param request
     * @return
     */
    public static boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    public static String getDomain(HttpServletRequest request) {
        String result = request.getScheme() + "://" + request.getServerName();
        if (request.getServerPort() != 80) {
            result += ":" + request.getServerPort();
        }
        result += request.getContextPath();

        return result;
    }

    public static String getRealPath(HttpServletRequest request, String path) {
        return request.getSession(true).getServletContext().getRealPath(path);
    }

    public static void sendRedirect(HttpServletResponse response, String url) {
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //得到请求头中携带的参数
    public static String getHeader(String name) {
        HttpServletRequest request = getRequest();
        return request.getHeader(name);
    }



    private ServletUtil() {
        throw new AssertionError();
    }

    /**
     * post请求
     *
     * @param urlstr
     * @param postdata
     * @return
     */
    public static String post(String urlstr, String postdata) {

        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            //POST请求
            DataOutputStream out = new DataOutputStream(
                    connection.getOutputStream());
            out.write(postdata.getBytes("UTF-8"));
            out.flush();
            out.close();


            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String lines;
            StringBuffer sb = new StringBuffer("");
            while ((lines = reader.readLine()) != null) {
                lines = new String(lines.getBytes(), "utf-8");
                sb.append(lines);
            }

            reader.close();
            // 断开连接
            connection.disconnect();
            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * GET 请求
     *
     * @param urlstr
     * @return
     */
    public static String get(String urlstr) {
        BufferedReader in = null;
        StringBuilder result = new StringBuilder();
        try {
            URL url = new URL(urlstr);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

            connection.connect();

            // 取得输入流，并使用Reader读取
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            // 断开连接
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                in.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
        return result.toString();
    }


}
