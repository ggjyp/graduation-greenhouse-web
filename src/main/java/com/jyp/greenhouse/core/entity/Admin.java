package com.jyp.greenhouse.core.entity;

public class Admin {
    private String adminId;

    private String adminName;

    private String adminPassword;

    private String adminState;

    private String adminImage;

    private String adminPrelogintime;

    private String adminPreloginip;

    private String adminLastlogintime;

    private String adminLastloginip;

    private String roles;

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId == null ? null : adminId.trim();
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword == null ? null : adminPassword.trim();
    }

    public String getAdminState() {
        return adminState;
    }

    public void setAdminState(String adminState) {
        this.adminState = adminState;
    }

    public String getAdminImage() {
        return adminImage;
    }

    public void setAdminImage(String adminImage) {
        this.adminImage = adminImage == null ? null : adminImage.trim();
    }


    public String getAdminPrelogintime() {
        return adminPrelogintime;
    }

    public void setAdminPrelogintime(String adminPrelogintime) {
        this.adminPrelogintime = adminPrelogintime;
    }

    public String getAdminPreloginip() {
        return adminPreloginip;
    }

    public void setAdminPreloginip(String adminPreloginip) {
        this.adminPreloginip = adminPreloginip == null ? null : adminPreloginip.trim();
    }

    public String getAdminLastlogintime() {
        return adminLastlogintime;
    }

    public void setAdminLastlogintime(String adminLastlogintime) {
        this.adminLastlogintime = adminLastlogintime;
    }

    public String getAdminLastloginip() {
        return adminLastloginip;
    }

    public void setAdminLastloginip(String adminLastloginip) {
        this.adminLastloginip = adminLastloginip == null ? null : adminLastloginip.trim();
    }
}