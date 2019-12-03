package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.ProjectRightDetail;
import com.gw.dev.hcbq.repository.ProjectRightDetailRepository;
import com.gw.dev.hcbq.service.ProjectRightDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectRightDetailServiceImpl implements ProjectRightDetailService {

    @Resource
    ProjectRightDetailRepository projectRightDetailRepository;

    @Override
    public ProjectRightDetail save(ProjectRightDetail projectRightDetail) {
        return this.projectRightDetailRepository.save(projectRightDetail);
    }

    @Override
    public ProjectRightDetail getOne(String id) {
        return this.projectRightDetailRepository.getOne(id);
    }

    @Override
    public List<ProjectRightDetail> findByProjectRightId(String projectRightId) {
        return this.projectRightDetailRepository.findByProjectRightId(projectRightId);
    }

    @Override
    public void delete(String id) {
        this.projectRightDetailRepository.deleteById(id);
    }

    @Override
    public List<ProjectRightDetail> findByProjectId(String projectId) {
        return this.projectRightDetailRepository.findByProjectId(projectId);
    }
}
