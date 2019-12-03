package com.gw.dev.hcbq.service;


import com.gw.dev.hcbq.entity.ProjectRightDetail;

import java.util.List;

public interface ProjectRightDetailService {
    public ProjectRightDetail save(ProjectRightDetail projectRightDetail);

    public ProjectRightDetail getOne(String id);

    public List<ProjectRightDetail> findByProjectRightId(String projectRightId);

    public List<ProjectRightDetail> findByProjectId(String projectId);

    public void delete(String id);
}
