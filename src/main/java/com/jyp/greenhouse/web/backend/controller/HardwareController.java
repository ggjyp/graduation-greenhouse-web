package com.jyp.greenhouse.web.backend.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jyp.greenhouse.core.entity.Measurement;
import com.jyp.greenhouse.core.util.HttpUtil;
import com.jyp.greenhouse.core.util.ServletUtil;
import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.core.util.TimeUtil;
import com.jyp.greenhouse.web.constants.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Author   : jyp
 * Date     : 2017-05-04 13:44
 * Describe : 温室硬件控制器
 */
@Controller
@RequestMapping(value = "/admin/hardware")
public class HardwareController {

    @RequestMapping("/table/list")
    public ModelAndView list(ModelAndView mav,
                             @RequestParam(required = false, defaultValue = "1") String page,
                             @RequestParam(required = false, defaultValue = "10") String pageshow
    ) {
        String assignmentToken  = "83d43843-c4a7-403c-83ac-9dc0d1918aba";
        String parameters = "?page="+ page +"&pageSize="+pageshow;
        String uri = "http://localhost:8080/sitewhere/api/assignments/"
                + assignmentToken + "/measurements" + parameters;
        JSONObject jsonObject = HttpUtil.getHtmlJsonByUrl(uri );
        List<Measurement> records = new ArrayList<>();

        JSONArray results = jsonObject.getJSONArray("results");
        JSONObject result;
        JSONObject measurementObj;
        for (int i = 0; i < results.size(); i++){
            result = results.getJSONObject(i);
            measurementObj = result.getJSONObject("measurements");
            double temperature = Double.valueOf(measurementObj.get("temperature").toString());
            double humidity = Double.valueOf(measurementObj.get("humidity").toString());
//            double lightIntensity = Double.valueOf(measurementObj.get("lightIntensity").toString());
//            double soilMoisture = Double.valueOf(measurementObj.get("soilMoisture").toString());
            String eventDate = result.get("eventDate").toString();
            Measurement measurement = new Measurement();
            measurement.setTemperature(temperature);
            measurement.setHumidity(humidity);
//            measurement.setLightIntensity(lightIntensity);
//            measurement.setSoilMoisture(soilMoisture);
            measurement.setLightIntensity(10000.0);
            measurement.setSoilMoisture(15.0);
            measurement.setEventDate(TimeUtil.parseSiteWhereDate(eventDate));
            records.add(measurement);
        }
        int count = (Integer)jsonObject.get("numResults");
        try {
            int maxpage = (int) Math.ceil((double)count / Integer.parseInt(pageshow));
            if (maxpage == 0) maxpage = 1;

            mav.addObject("count", count);
            mav.addObject("records", records);
            mav.addObject("maxpage", maxpage);mav.addObject("page", page);
            HttpServletRequest request = ServletUtil.getRequest();
            Enumeration enu = request.getParameterNames();
            while (enu.hasMoreElements()) {
                String paraName = (String) enu.nextElement();
                mav.addObject(paraName, request.getParameter(paraName));
            }
            mav.setViewName("admin/hardware/table/list");
        } catch (Exception ex) {
            ex.printStackTrace();
            mav.addObject(Constants.MSG, Constants.SYSERROR);
            mav.addObject(Constants.MSG_DETAIL, Constants.SYSERROR_DETAIL);
            mav.setViewName("systemerror");
        }
        return mav;
    }


    @RequestMapping(value = "/chart/list")
    public ModelAndView chart(ModelAndView mav) {
        mav.setViewName("admin/hardware/chart/list");
        return mav;
    }

    @RequestMapping(value = "/control/list")
    public ModelAndView control(ModelAndView mav) {
        mav.setViewName("admin/hardware/control/list");
        return mav;
    }



}
