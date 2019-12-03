package com.gw.dev.hcbq.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "T_Project_Right_Detail")
//五级
public class ProjectRightDetail {
    @Id
    private String id;
    private String status;
    private String fx;
    private String dj;
    private String dy;
    private String dygj;
    private String dyxf;
    private String startDate;
    private String endDate;

    @JSONField(format = "yyyy-MM-dd")
    private Date startDateS;
    @JSONField(format = "yyyy-MM-dd")
    private Date endDateS;

    private String ksdd;
    private String jsdd;
    private String qlms;
    private String ssgs;
    private String sqf;
    private String bsqf;
    private String bz;
    private String htmc;
    private String htbh;
    private String xkz;
    private String projectRightId;
    private String projectId;

    @Override
    public String toString() {
        return "ProjectRightDetail{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", fx='" + fx + '\'' +
                ", dj='" + dj + '\'' +
                ", dy='" + dy + '\'' +
                ", dygj='" + dygj + '\'' +
                ", dyxf='" + dyxf + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", startDateS=" + startDateS +
                ", endDateS=" + endDateS +
                ", ksdd='" + ksdd + '\'' +
                ", jsdd='" + jsdd + '\'' +
                ", qlms='" + qlms + '\'' +
                ", ssgs='" + ssgs + '\'' +
                ", sqf='" + sqf + '\'' +
                ", bsqf='" + bsqf + '\'' +
                ", bz='" + bz + '\'' +
                ", htmc='" + htmc + '\'' +
                ", htbh='" + htbh + '\'' +
                ", xkz='" + xkz + '\'' +
                ", projectRightId='" + projectRightId + '\'' +
                ", projectId='" + projectId + '\'' +
                '}';
    }



    public String getKsdd() {
        return ksdd;
    }

    public void setKsdd(String ksdd) {
        this.ksdd = ksdd;
    }

    public String getJsdd() {
        return jsdd;
    }

    public void setJsdd(String jsdd) {
        this.jsdd = jsdd;
    }

    public String getDygj() {
        return dygj;
    }

    public void setDygj(String dygj) {
        this.dygj = dygj;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectRightId() {
        return projectRightId;
    }

    public void setProjectRightId(String projectRightId) {
        this.projectRightId = projectRightId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getDj() {
        return dj;
    }

    public void setDj(String dj) {
        this.dj = dj;
    }

    public String getDy() {
        return dy;
    }

    public void setDy(String dy) {
        this.dy = dy;
    }

    public String getDyxf() {
        return dyxf;
    }

    public void setDyxf(String dyxf) {
        this.dyxf = dyxf;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getStartDateS() {
        return startDateS;
    }

    public void setStartDateS(Date startDateS) {
        this.startDateS = startDateS;
    }

    public Date getEndDateS() {
        return endDateS;
    }

    public void setEndDateS(Date endDateS) {
        this.endDateS = endDateS;
    }

    public String getQlms() {
        return qlms;
    }

    public void setQlms(String qlms) {
        this.qlms = qlms;
    }

    public String getSsgs() {
        return ssgs;
    }

    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }

    public String getSqf() {
        return sqf;
    }

    public void setSqf(String sqf) {
        this.sqf = sqf;
    }

    public String getBsqf() {
        return bsqf;
    }

    public void setBsqf(String bsqf) {
        this.bsqf = bsqf;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getHtmc() {
        return htmc;
    }

    public void setHtmc(String htmc) {
        this.htmc = htmc;
    }

    public String getHtbh() {
        return htbh;
    }

    public void setHtbh(String htbh) {
        this.htbh = htbh;
    }

    public String getXkz() {
        return xkz;
    }

    public void setXkz(String xkz) {
        this.xkz = xkz;
    }
}
