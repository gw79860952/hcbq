package com.gw.dev.hcbq.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "T_Project")
//一级
public class Project {
    @Id
    private String id;
    private String name;
    private String oldName;
    //表单类型，版权录入/发行录入
    private String formType;
    //项目类型：小说/漫画/剧本等   作废
    private String projectType;
    private String glProId;
    private String glProName;

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", oldName='" + oldName + '\'' +
                ", formType='" + formType + '\'' +
                ", projectType='" + projectType + '\'' +
                ", glProId='" + glProId + '\'' +
                ", glProName='" + glProName + '\'' +
                '}';
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

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getFormType() {
        return formType;
    }

    public void setFormType(String formType) {
        this.formType = formType;
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

    public String getOldName() {
        return oldName;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }
}
