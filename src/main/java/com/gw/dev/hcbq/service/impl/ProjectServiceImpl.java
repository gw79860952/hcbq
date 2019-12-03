package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.ProKfzt;
import com.gw.dev.hcbq.entity.Project;
import com.gw.dev.hcbq.entity.ProjectRight;
import com.gw.dev.hcbq.entity.ProjectRightDetail;
import com.gw.dev.hcbq.repository.ProjectRepository;
import com.gw.dev.hcbq.repository.ProjectRightDetailRepository;
import com.gw.dev.hcbq.repository.ProjectRightRepository;
import com.gw.dev.hcbq.service.ProKfztService;
import com.gw.dev.hcbq.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectRepository projectRepository;

    @Resource
    ProjectRightRepository projectRightRepository;

    @Resource
    ProjectRightDetailRepository projectRightDetailRepository;

    @Resource
    ProKfztService proKfztService;

    @Override
    public Project save(Project project) {
        return this.projectRepository.save(project);
    }

    @Override
    public Project getOne(String id) {
        return this.projectRepository.getOne(id);
    }

    @Override
    public List<Project> findAll() {
        return this.projectRepository.findAll();
    }

    @Override
    public void delete(String id) {
        this.projectRepository.deleteById(id);
        List<ProjectRight> prl = this.projectRightRepository.findByProjectId(id);
        for(ProjectRight pr : prl){
            this.projectRightRepository.delete(pr);
        }
        List<ProjectRightDetail> prdl = this.projectRightDetailRepository.findByProjectId(id);
        for(ProjectRightDetail prd : prdl){
            this.projectRightDetailRepository.delete(prd);
        }
        Project p = this.projectRepository.getOne(id);
        List<ProKfzt> kfztl = this.proKfztService.findByProName(p.getName());
        for(ProKfzt pk : kfztl){
            this.proKfztService.delete(pk.getId());
        }
    }

    @Override
    public List<Project> findByGlProId(String proId) {
        return this.projectRepository.findByGl(proId);
    }

    @Override
    public List<Project> findByFormType(String formType) {
        return this.projectRepository.findByFormType(formType);
    }
}
