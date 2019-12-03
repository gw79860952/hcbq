package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.Project;
import com.gw.dev.hcbq.entity.ProjectRight;
import com.gw.dev.hcbq.entity.ProjectRightDetail;
import com.gw.dev.hcbq.repository.ProjectRightDetailRepository;
import com.gw.dev.hcbq.repository.ProjectRightRepository;
import com.gw.dev.hcbq.service.ProjectRightService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectRightServiceImpl implements ProjectRightService {

    @Resource
    ProjectRightRepository projectRightRepository;

    @Resource
    ProjectRightDetailRepository projectRightDetailRepository;

    @Override
    public ProjectRight save(ProjectRight projectRight) {
        return this.projectRightRepository.save(projectRight);
    }

    @Override
    public ProjectRight getOne(String id) {
        return this.projectRightRepository.getOne(id);
    }

    @Override
    public List<ProjectRight> findByProjectId(String projectId) {
        return this.projectRightRepository.findByProjectId(projectId);
    }

    @Override
    public List<ProjectRight> findByProjectIdAndHaveType(String projectId, String haveType) {
        return this.projectRightRepository.findByProjectIdAndHaveType(projectId,haveType);
    }

    @Override
    public void delete(String id) {
        this.projectRightRepository.deleteById(id);
        List<ProjectRightDetail> prdl = this.projectRightDetailRepository.findByProjectRightId(id);
        for(ProjectRightDetail prd : prdl){
            this.projectRightDetailRepository.delete(prd);
        }
    }
}
