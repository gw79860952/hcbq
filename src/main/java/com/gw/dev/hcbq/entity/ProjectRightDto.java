package com.gw.dev.hcbq.entity;

import java.util.ArrayList;
import java.util.List;

public class ProjectRightDto {
    private ProjectRight projectRight;
    private List<ProjectRightDetail> projectRightDetailList = new ArrayList<ProjectRightDetail>();

    public ProjectRight getProjectRight() {
        return projectRight;
    }

    public void setProjectRight(ProjectRight projectRight) {
        this.projectRight = projectRight;
    }

    public List<ProjectRightDetail> getProjectRightDetailList() {
        return projectRightDetailList;
    }

    public void setProjectRightDetailList(List<ProjectRightDetail> projectRightDetailList) {
        this.projectRightDetailList = projectRightDetailList;
    }
}
