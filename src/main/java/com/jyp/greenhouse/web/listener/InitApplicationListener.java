package com.jyp.greenhouse.web.listener;




import com.jyp.greenhouse.web.constants.WebConstants;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        WebConstants.APPLICATION_PATH = sce.getServletContext().getRealPath("/");
        WebConstants.TOMCAT_VERSION = sce.getServletContext().getServerInfo();

    /* 给log4j设置环境变量，必须要在jvm加载log4j.properties前设置 */
        System.setProperty("log4jHome", WebConstants.APPLICATION_PATH);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }

}
