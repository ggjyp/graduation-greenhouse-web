package com.jyp.greenhouse.core.entity;

public class AutoCtrlParam {
    private String id;

    private String paramName;

    private String temperatureMin;

    private String temperatureMax;

    private String humidityMin;

    private String humidityMax;

    private String lightIntensityMin;

    private String lightIntensityMax;

    private String soilMoistureMin;

    private String soilMoistureMax;

    private String createDate;

    private String deleteDate;

    private String modifyDate;

    private String isUsing;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public String getHumidityMin() {
        return humidityMin;
    }

    public void setHumidityMin(String humidityMin) {
        this.humidityMin = humidityMin;
    }

    public String getHumidityMax() {
        return humidityMax;
    }

    public void setHumidityMax(String humidityMax) {
        this.humidityMax = humidityMax;
    }

    public String getLightIntensityMin() {
        return lightIntensityMin;
    }

    public void setLightIntensityMin(String lightIntensityMin) {
        this.lightIntensityMin = lightIntensityMin;
    }

    public String getLightIntensityMax() {
        return lightIntensityMax;
    }

    public void setLightIntensityMax(String lightIntensityMax) {
        this.lightIntensityMax = lightIntensityMax;
    }

    public String getSoilMoistureMin() {
        return soilMoistureMin;
    }

    public void setSoilMoistureMin(String soilMoistureMin) {
        this.soilMoistureMin = soilMoistureMin;
    }

    public String getSoilMoistureMax() {
        return soilMoistureMax;
    }

    public void setSoilMoistureMax(String soilMoistureMax) {
        this.soilMoistureMax = soilMoistureMax;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(String deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getIsUsing() {
        return isUsing;
    }

    public void setIsUsing(String isUsing) {
        this.isUsing = isUsing;
    }
}