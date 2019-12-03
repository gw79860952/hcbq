package com.gw.dev.hcbq.entity;

import javax.persistence.*;

@Entity
@Table(name = "T_Project_Right")
//四级
public class ProjectRight {
    @Id
    private String id;
    private String name;
    private String yjName;
    private String ejName;
    private String xzms;
    private String zrxzms;
    private String projectId;
    //项目类型：小说/漫画/剧本等
    private String projectType;
    //原始、卖出、现有标志
    private String haveType;


    public String getHaveType() {
        return haveType;
    }

    public void setHaveType(String haveType) {
        this.haveType = haveType;
    }

    public String getYjName() {
        return yjName;
    }

    public void setYjName(String yjName) {
        this.yjName = yjName;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public String toString() {
        return "ProjectRight{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", yjName='" + yjName + '\'' +
                ", ejName='" + ejName + '\'' +
                ", xzms='" + xzms + '\'' +
                ", zrxzms='" + zrxzms + '\'' +
                ", projectId='" + projectId + '\'' +
                ", projectType='" + projectType + '\'' +
                ", haveType='" + haveType + '\'' +
                '}';
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    @Transient
    public String getYjqlqt(){
        if(yjName != null && !"".equals(yjName)){
            if(yjName.contains("[其他]:")){
                if(yjName.split(":").length > 1){
                    return yjName.split(":")[1];
                }
            }
        }
        return "";
    }

    @Transient
    public String getEjqlqt(){
        if(ejName != null && !"".equals(ejName)){
            if(ejName.contains("[其他]:")){
                if(ejName.split(":").length > 1){
                    return ejName.split(":")[1];
                }
            }
        }
        return "";
    }
}
