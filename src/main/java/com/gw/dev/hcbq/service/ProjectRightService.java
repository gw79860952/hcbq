package com.gw.dev.hcbq.service;


import com.gw.dev.hcbq.entity.ProjectRight;

import java.util.List;

public interface ProjectRightService {
    public ProjectRight save(ProjectRight projectRight);

    public ProjectRight getOne(String id);

    public List<ProjectRight> findByProjectId(String projectId);

    public List<ProjectRight> findByProjectIdAndHaveType(String projectId,String haveType);

    public void delete(String id);
}
