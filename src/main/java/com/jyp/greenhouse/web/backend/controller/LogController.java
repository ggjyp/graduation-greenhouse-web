package com.jyp.greenhouse.web.backend.controller;

import com.jyp.greenhouse.core.entity.Log;
import com.jyp.greenhouse.core.util.ServletUtil;
import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.core.util.TimeUtil;
import com.jyp.greenhouse.service.LogService;
import com.jyp.greenhouse.web.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

/**
 * Author   : jyp
 * Date     : 2017-05-03 14:33
 * Describe : 日志管理控制器
 */
@Controller
@RequestMapping(value = "/admin/log")
public class LogController {
    @Autowired
    LogService logService;

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mav,
                             @RequestParam(required = false) String operateFrom,
                             @RequestParam(required = false) String operateTo,
                             @RequestParam(required = false) String starttime,
                             @RequestParam(required = false) String endtime,
                             @RequestParam(required = false, defaultValue = "1") String page,
                             @RequestParam(required = false, defaultValue = "10") String pageshow
    ) {
        try {
            if (StringUtil.isBlank(operateFrom)) operateFrom = null;
            if (StringUtil.isBlank(operateTo)) operateTo = null;
            int starttime2 = 0;
            int endtime2 = 0;
            if (!StringUtil.isBlank(starttime)) {
                starttime2 = (int) TimeUtil.TimeStrToStamp("yyyy-MM-dd", starttime);
            }
            if (!StringUtil.isBlank(endtime)) {
                endtime2 = (int) TimeUtil.TimeStrToStamp("yyyy-MM-dd", endtime);
            }

            List<Log> records = logService.list(operateFrom,operateTo, starttime2, endtime2, Integer.parseInt(page), Integer.parseInt(pageshow));
            int count = logService.count(operateFrom, operateTo, starttime2, endtime2);
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
            mav.setViewName("admin/log/list");
        } catch (Exception ex) {
            ex.printStackTrace();
            mav.addObject(Constants.MSG, Constants.SYSERROR);
            mav.addObject(Constants.MSG_DETAIL, Constants.SYSERROR_DETAIL);
            mav.setViewName("systemerror");
        }
        return mav;
    }

    @ResponseBody
    @RequestMapping("/add")
    public HashMap add(
            String operateFrom,
            String operateTo,
            String behavior
    ) {
        HashMap hm = new HashMap<>();
        Log log = new Log();
        log.setOperateFrom(operateFrom);
        log.setOperateTo(operateTo);
        log.setOperateTime(TimeUtil.getNowTimestamp()+"");
        log.setBehavior(behavior);

        if (logService.insert(log) == 1){
            hm.put(Constants.STATE,Constants.SUCC);
        }
        else {
            hm.put(Constants.MSG,Constants.ADDERROR);
        }
        return  hm;
    }
}
