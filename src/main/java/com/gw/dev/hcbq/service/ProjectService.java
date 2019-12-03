package com.gw.dev.hcbq.service;


import com.gw.dev.hcbq.entity.Project;

import java.util.List;

public interface ProjectService {
    public Project save(Project project);

    public Project getOne(String id);

    public List<Project> findAll();

    public void delete(String id);

    public List<Project> findByGlProId(String proId);

    public List<Project> findByFormType(String formType);
}
