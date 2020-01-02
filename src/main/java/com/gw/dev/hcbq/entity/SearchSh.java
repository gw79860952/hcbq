package com.gw.dev.hcbq.entity;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "searchview1")
public class Search {

    private String proId;
    private String proName;
    private String oldName;
    private String formType;
    private String glProId;
    private String glProName;

    private String rightId;
    private String qlname;
    private String yjName;
    private String ejName;
    private String xzms;
    private String zrxzms;
    private String projectType;
    private String haveType;

    @Id
    private String detailId;
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


    @Override
    public String toString() {
        return "Search{" +
                "proId='" + proId + '\'' +
                ", proName='" + proName + '\'' +
                ", oldName='" + oldName + '\'' +
                ", formType='" + formType + '\'' +
                ", glProId='" + glProId + '\'' +
                ", glProName='" + glProName + '\'' +
                ", rightId='" + rightId + '\'' +
                ", qlname='" + qlname + '\'' +
                ", yjName='" + yjName + '\'' +
                ", ejName='" + ejName + '\'' +
                ", xzms='" + xzms + '\'' +
                ", zrxzms='" + zrxzms + '\'' +
                ", projectType='" + projectType + '\'' +
                ", haveType='" + haveType + '\'' +
                ", detailId='" + detailId + '\'' +
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
                '}';
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
    }

    public String getGlProId() {
        return glProId;
    }

    public void setGlProId(String glProId) {
        this.glProId = glProId;
    }

    public String getGlProName() {
        return glProName;
    }

    public void setGlProName(String glProName) {
        this.glProName = glProName;
    }

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }

    public String getQlname() {
        return qlname;
    }

    public void setQlname(String qlname) {
        this.qlname = qlname;
    }

    public String getYjName() {
        String res = this.yjName;
        if(res != null){
            if(res.contains("发表权")){
                res = "发表权,"+res.replaceAll("发表权","");
            }
            if(res.contains("改编权")){
                res = "改编权,"+res.replaceAll("改编权","");
            }
            return res.replaceAll(",,","");
        }
        return yjName;
    }

    public void setYjName(String yjName) {
        this.yjName = yjName;
    }

    public String getEjName() {
        return ejName;
    }

    public void setEjName(String ejName) {
        this.ejName = ejName;
    }

    public String getXzms() {
        return xzms;
    }

    public void setXzms(String xzms) {
        this.xzms = xzms;
    }

    public String getZrxzms() {
        return zrxzms;
    }

    public void setZrxzms(String zrxzms) {
        this.zrxzms = zrxzms;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getHaveType() {
        return haveType;
    }

    public void setHaveType(String haveType) {
        this.haveType = haveType;
    }

    public String getDetailId() {
        return detailId;
    }

    public void setDetailId(String detailId) {
        this.detailId = detailId;
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

    public String getDygj() {
        return dygj;
    }

    public void setDygj(String dygj) {
        this.dygj = dygj;
    }

    public String getDyxf() {
        return dyxf;
    }

    public void setDyxf(String dyxf) {
        this.dyxf = dyxf;
    }

    public String getStartDate() {
        if("1".equals(this.ksdd)){
            return "待定";
        }
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        if("1".equals(this.getJsdd())){
            return "待定";
        }
        if("".equals(endDate) || endDate == null){
            return "永久";
        }
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
