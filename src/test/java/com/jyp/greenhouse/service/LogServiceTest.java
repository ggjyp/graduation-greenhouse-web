package com.jyp.greenhouse.service;

import com.jyp.greenhouse.core.entity.Log;
import com.jyp.greenhouse.core.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author   : jyp
 * Date     : 2017-05-03 14:54
 * Describe :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring-base.xml")
public class LogServiceTest {
    @Autowired
    LogService logService;

    @Test
    public void insert() {
        Log log = new Log();
        log.setOperateFrom("jyp");
        log.setOperateTo("LED");
        log.setOperateTime(TimeUtil.getNowTimestamp()+"");
        log.setBehavior("关闭");
        if (logService.insert(log) == 1) {
            System.out.println("OK");
        }
        else
            System.out.println("NO");
    }
}
