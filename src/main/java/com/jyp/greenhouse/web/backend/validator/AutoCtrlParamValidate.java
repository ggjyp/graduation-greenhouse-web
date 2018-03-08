package com.jyp.greenhouse.web.backend.validator;

import com.jyp.greenhouse.core.util.StringUtil;
import com.jyp.greenhouse.web.constants.Constants;

/**
 * Author   : jyp
 * Date     : 2017-05-03 16:16
 * Describe : 自动控制参数验证
 */
public class AutoCtrlParamValidate {
    private static final String NAME_EMPTY = "自动控制参数名不能为空";
    private static final String TEMPERATURE_MIN_EMPTY = "温度下限不能为空";
    private static final String TEMPERATURE_MAX_EMPTY = "温度上限不能为空";
    private static final String HUMIDITY_MIN_EMPTY = "湿度下限不能为空";
    private static final String HUMIDITY_MAX_EMPTY = "湿度上限不能为空";
    private static final String LIGHT_MIN_EMPTY = "光照强度下限不能为空";
    private static final String LIGHT_MAX_EMPTY = "光照强度上限不能为空";
    private static final String SOIL_MIN_EMPTY = "土壤湿度下限不能为空";
    private static final String SOIL_MAX_EMPTY = "土壤湿度上限不能为空";

    public static String validate (
            String paramName,
            String temperatureMin,
            String temperatureMax,
            String humidityMin,
            String humidityMax,
            String lightIntensityMin,
            String lightIntensityMax,
            String soilMoistureMin,
            String soilMoistureMax
    ) {
        if (StringUtil.isBlank(paramName))
            return NAME_EMPTY;
        if (StringUtil.isBlank(temperatureMin))
            return TEMPERATURE_MIN_EMPTY;
        if (StringUtil.isBlank(temperatureMax))
            return TEMPERATURE_MAX_EMPTY;
        if (StringUtil.isBlank(humidityMin))
            return HUMIDITY_MIN_EMPTY;
        if (StringUtil.isBlank(humidityMax))
            return HUMIDITY_MAX_EMPTY;
        if (StringUtil.isBlank(lightIntensityMin))
            return LIGHT_MIN_EMPTY;
        if (StringUtil.isBlank(lightIntensityMax))
            return LIGHT_MAX_EMPTY;
        if (StringUtil.isBlank(soilMoistureMin))
            return SOIL_MIN_EMPTY;
        if (StringUtil.isBlank(soilMoistureMax))
            return SOIL_MAX_EMPTY;
        return null;
    }

}
