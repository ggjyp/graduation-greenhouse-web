package com.jyp.greenhouse.web.interceptor;


import com.jyp.greenhouse.core.security.Base64Codec;
import com.jyp.greenhouse.core.security.Signaturer;
import com.jyp.greenhouse.core.util.ServletUtil;
import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.web.constants.Constants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by wjh on 2016/3/29.
 * <p>
 * 拦截器
 */


public class ACLInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 此处实现访问控制
        HttpSession session = request.getSession();
        String userid = (String) session.getAttribute(Constants.SESSION_USER);
        if (StringUtil.isBlank(userid)) {
            String API_KEY = ServletUtil.getHeader(Constants.API_KEY);
            if (!StringUtil.isBlank(API_KEY)) {
                try {
                    //base64解码获取api_key
                    String signstr = Base64Codec.decodeString(API_KEY);
                    //校验数字签名获取明文useid+###UUU+uuid
                    String plaintext = Signaturer.verify(signstr);
                    if (!StringUtil.isBlank(plaintext)) {
                        userid = plaintext.substring(0, 19);
                        session.setAttribute(Constants.SESSION_USER, userid);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        System.out.println("----在Action 方法执行完毕之后,执行(没有抛异常的话)----------");

    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
//        System.out.println("----在Action 方法执行完毕之后,无论是否抛出异常,通常用来进行异常处理----------");
    }
}
