package com.jyp.greenhouse.core.entity;

public class Log {
    private String id;

    private String operateFrom;

    private String operateTo;

    private String operateTime;

    private String behavior;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperateFrom() {
        return operateFrom;
    }

    public void setOperateFrom(String operateFrom) {
        this.operateFrom = operateFrom == null ? null : operateFrom.trim();
    }

    public String getOperateTo() {
        return operateTo;
    }

    public void setOperateTo(String operateTo) {
        this.operateTo = operateTo == null ? null : operateTo.trim();
    }

    public String getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(String operateTime) {
        this.operateTime = operateTime;
    }

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior == null ? null : behavior.trim();
    }
}