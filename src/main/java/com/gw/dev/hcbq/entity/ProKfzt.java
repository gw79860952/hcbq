package com.gw.dev.hcbq.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "T_Project_Kfzt")
public class ProKfzt {
    @Id
    private String id ;
    private String proName;
    private String qlfl;
    private String lx;
    private String kfzt;
    private String ssgs;
    private String beginDate;
    private String endDate;
    private Date beginDateS;
    private Date endDateS;
    private String bz;
    private String fzr;
    private String baxx;
    private String sxgd;

    @Override
    public String toString() {
        return "ProKfzt{" +
                "id='" + id + '\'' +
                ", proName='" + proName + '\'' +
                ", qlfl='" + qlfl + '\'' +
                ", lx='" + lx + '\'' +
                ", kfzt='" + kfzt + '\'' +
                ", ssgs='" + ssgs + '\'' +
                ", beginDate='" + beginDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", beginDateS=" + beginDateS +
                ", endDateS=" + endDateS +
                ", bz='" + bz + '\'' +
                ", fzr='" + fzr + '\'' +
                ", baxx='" + baxx + '\'' +
                ", sxgd='" + sxgd + '\'' +
                '}';
    }

    public String getSxgd() {
        return sxgd;
    }

    public void setSxgd(String sxgd) {
        this.sxgd = sxgd;
    }

    public String getFzr() {
        return fzr;
    }

    public void setFzr(String fzr) {
        this.fzr = fzr;
    }

    public String getBaxx() {
        return baxx;
    }

    public void setBaxx(String baxx) {
        this.baxx = baxx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getQlfl() {
        return qlfl;
    }

    public void setQlfl(String qlfl) {
        this.qlfl = qlfl;
    }

    public String getLx() {
        return lx;
    }

    public void setLx(String lx) {
        this.lx = lx;
    }

    public String getKfzt() {
        return kfzt;
    }

    public void setKfzt(String kfzt) {
        this.kfzt = kfzt;
    }

    public String getSsgs() {
        return ssgs;
    }

    public void setSsgs(String ssgs) {
        this.ssgs = ssgs;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getBeginDateS() {
        return beginDateS;
    }

    public void setBeginDateS(Date beginDateS) {
        this.beginDateS = beginDateS;
    }

    public Date getEndDateS() {
        return endDateS;
    }

    public void setEndDateS(Date endDateS) {
        this.endDateS = endDateS;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }
}
