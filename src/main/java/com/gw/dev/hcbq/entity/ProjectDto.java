package com.gw.dev.hcbq.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectDto {
    private Project project;
    private List<ProjectRightDto> xyprojectRightDtoList = new ArrayList<ProjectRightDto>();

    private List<ProjectRightDto> ysprojectRightDtoList = new ArrayList<ProjectRightDto>();

    private List<ProjectRightDto> zsqprojectRightDtoList = new ArrayList<ProjectRightDto>();

    private List<ProjectRightDto> qlywzrprojectRightDtoList = new ArrayList<ProjectRightDto>();

    private List<ProjectRightDto> sqdlfxprojectRightDtoList = new ArrayList<ProjectRightDto>();

    private List<ProjectRightDto> fxptprojectRightDtoList = new ArrayList<ProjectRightDto>();

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }


    public List<ProjectRightDto> getXyprojectRightDtoList() {
        return xyprojectRightDtoList;
    }

    public void setXyprojectRightDtoList(List<ProjectRightDto> xyprojectRightDtoList) {
        this.xyprojectRightDtoList = xyprojectRightDtoList;
    }

    public List<ProjectRightDto> getYsprojectRightDtoList() {
        return ysprojectRightDtoList;
    }

    public void setYsprojectRightDtoList(List<ProjectRightDto> ysprojectRightDtoList) {
        this.ysprojectRightDtoList = ysprojectRightDtoList;
    }

    public List<ProjectRightDto> getZsqprojectRightDtoList() {
        return zsqprojectRightDtoList;
    }

    public void setZsqprojectRightDtoList(List<ProjectRightDto> zsqprojectRightDtoList) {
        this.zsqprojectRightDtoList = zsqprojectRightDtoList;
    }

    public List<ProjectRightDto> getQlywzrprojectRightDtoList() {
        return qlywzrprojectRightDtoList;
    }

    public void setQlywzrprojectRightDtoList(List<ProjectRightDto> qlywzrprojectRightDtoList) {
        this.qlywzrprojectRightDtoList = qlywzrprojectRightDtoList;
    }

    public List<ProjectRightDto> getSqdlfxprojectRightDtoList() {
        return sqdlfxprojectRightDtoList;
    }

    public void setSqdlfxprojectRightDtoList(List<ProjectRightDto> sqdlfxprojectRightDtoList) {
        this.sqdlfxprojectRightDtoList = sqdlfxprojectRightDtoList;
    }

    public List<ProjectRightDto> getFxptprojectRightDtoList() {
        return fxptprojectRightDtoList;
    }

    public void setFxptprojectRightDtoList(List<ProjectRightDto> fxptprojectRightDtoList) {
        this.fxptprojectRightDtoList = fxptprojectRightDtoList;
    }
}
