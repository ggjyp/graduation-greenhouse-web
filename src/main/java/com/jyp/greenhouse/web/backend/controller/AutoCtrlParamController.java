package com.jyp.greenhouse.web.backend.controller;

import com.jyp.greenhouse.core.entity.AutoCtrlParam;
import com.jyp.greenhouse.core.entity.Log;
import com.jyp.greenhouse.core.util.ServletUtil;
import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.core.util.TimeUtil;
import com.jyp.greenhouse.service.AutoCtrlParamService;
import com.jyp.greenhouse.web.backend.validator.AutoCtrlParamValidate;
import com.jyp.greenhouse.web.constants.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author   : jyp
 * Date     : 2017-05-03 15:41
 * Describe : 自动控制参数管理控制器
 */
@Controller
@RequestMapping(value = "/admin/autoCtrlParam")
public class AutoCtrlParamController {
    @Autowired
    AutoCtrlParamService autoCtrlParamService;

    @RequestMapping("/list")
    public ModelAndView list(ModelAndView mav,
                             @RequestParam(required = false) String paramName,
                             @RequestParam(required = false, defaultValue = "1") String page,
                             @RequestParam(required = false, defaultValue = "10") String pageshow
    ) {
        try {
            if (StringUtil.isBlank(paramName)) paramName = null;
            List<AutoCtrlParam> records = autoCtrlParamService.list(paramName, Integer.parseInt(page), Integer.parseInt(pageshow));
            int count = autoCtrlParamService.count(paramName);
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
            mav.setViewName("admin/autoCtrlParam/list");
        } catch (Exception ex) {
            ex.printStackTrace();
            mav.addObject(Constants.MSG, Constants.SYSERROR);
            mav.addObject(Constants.MSG_DETAIL, Constants.SYSERROR_DETAIL);
            mav.setViewName("systemerror");
        }
        return mav;
    }

    @RequestMapping("/detail")
    public ModelAndView detail(ModelAndView mav,
                               @RequestParam String id
    ) {
        try {
            AutoCtrlParam record = autoCtrlParamService.findById(id);
            mav.addObject("record", record);
            mav.setViewName("admin/autoCtrlParam/detail");
        } catch (Exception ex) {
            ex.printStackTrace();
            mav.addObject(Constants.MSG, Constants.SYSERROR);
            mav.addObject(Constants.MSG_DETAIL, Constants.SYSERROR_DETAIL);
            mav.setViewName("systemerror");
        }
        return mav;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public ModelAndView detail(ModelAndView mav) {
        mav.setViewName("admin/autoCtrlParam/add");
        return mav;
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Map add(AutoCtrlParam autoCtrlParam) {
        Map mp = new HashMap();
        String msg = AutoCtrlParamValidate.validate(
                autoCtrlParam.getParamName(),
                autoCtrlParam.getTemperatureMin(),
                autoCtrlParam.getTemperatureMax(),
                autoCtrlParam.getHumidityMin(),
                autoCtrlParam.getHumidityMax(),
                autoCtrlParam.getLightIntensityMin(),
                autoCtrlParam.getLightIntensityMax(),
                autoCtrlParam.getSoilMoistureMax(),
                autoCtrlParam.getSoilMoistureMax()
                );
        if (null != msg) {
            mp.put(Constants.MSG, msg);
        }
        try {
            autoCtrlParam.setCreateDate(TimeUtil.getNowTimestamp() + "");
            if (autoCtrlParamService.insert(autoCtrlParam) == 1) {
                mp.put(Constants.STATE, Constants.SUCC);
            } else {
                mp.put(Constants.MSG, Constants.ADDERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mp.put(Constants.MSG, Constants.SYSERROR);
        }
        return mp;
    }

    @ResponseBody
    @RequestMapping("/update")
    public Map update(AutoCtrlParam autoCtrlParam) {
        Map mp = new HashMap();
        String msg = AutoCtrlParamValidate.validate(
                autoCtrlParam.getParamName(),
                autoCtrlParam.getTemperatureMin(),
                autoCtrlParam.getTemperatureMax(),
                autoCtrlParam.getHumidityMin(),
                autoCtrlParam.getHumidityMax(),
                autoCtrlParam.getLightIntensityMin(),
                autoCtrlParam.getLightIntensityMax(),
                autoCtrlParam.getSoilMoistureMax(),
                autoCtrlParam.getSoilMoistureMax()
        );
        if (null != msg) {
            mp.put(Constants.MSG, msg);
        }
        try {
            autoCtrlParam.setModifyDate(TimeUtil.getNowTimestamp() + "");
            if (autoCtrlParamService.update(autoCtrlParam) == 1) {
                mp.put(Constants.STATE, Constants.SUCC);
            } else {
                mp.put(Constants.MSG, Constants.UPDATEERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mp.put(Constants.MSG, Constants.SYSERROR);
        }
        return mp;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Map delete(@RequestParam String id) {
        Map mp = new HashMap();
        try {
            if (autoCtrlParamService.delete(id) == 1) {
                mp.put(Constants.STATE, Constants.SUCC);
            } else {
                mp.put(Constants.MSG, Constants.DELERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mp.put(Constants.MSG, Constants.SYSERROR);
        }
        return mp;
    }

    @ResponseBody
    @RequestMapping("/use")
    public Map use(@RequestParam String id) {
        Map mp = new HashMap();
        try {
            if (autoCtrlParamService.toUsing(TimeUtil.getNowTimestamp() + "", id) == 1) {
                mp.put(Constants.STATE, Constants.SUCC);
            } else {
                mp.put(Constants.MSG, Constants.SETERROR);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            mp.put(Constants.MSG, Constants.SYSERROR);
        }
        return mp;
    }
}
