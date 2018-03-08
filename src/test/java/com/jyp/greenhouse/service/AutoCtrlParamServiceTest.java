package com.jyp.greenhouse.service;

import com.jyp.greenhouse.core.entity.AutoCtrlParam;
import com.jyp.greenhouse.core.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Author   : jyp
 * Date     : 2017-05-03 15:29
 * Describe :
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config/spring-base.xml")
public class AutoCtrlParamServiceTest {
    @Autowired
    AutoCtrlParamService autoCtrlParamService;

    @Test
    public void insert() {
        AutoCtrlParam autoCtrlParam = new AutoCtrlParam();
        autoCtrlParam.setParamName("蝴蝶兰test期");
        autoCtrlParam.setTemperatureMax("25.0");
        autoCtrlParam.setTemperatureMin("20.0");
        autoCtrlParam.setHumidityMax("25.0");
        autoCtrlParam.setHumidityMin("20.0");
        autoCtrlParam.setLightIntensityMax("10000.0");
        autoCtrlParam.setLightIntensityMin("8000.0");
        autoCtrlParam.setSoilMoistureMax("25.0");
        autoCtrlParam.setSoilMoistureMin("20.0");
        autoCtrlParam.setCreateDate(TimeUtil.getNowTimestamp()+"");
        if (autoCtrlParamService.insert(autoCtrlParam) == 1)
            System.out.println("OK");
        else
            System.out.println("NO");
    }
}
