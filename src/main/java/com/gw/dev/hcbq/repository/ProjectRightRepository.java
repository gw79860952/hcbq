package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.ProjectRight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProjectRightRepository extends JpaRepository<ProjectRight,String>, JpaSpecificationExecutor<ProjectRight> {
    List<ProjectRight> findByProjectId(String projectId);

    List<ProjectRight> findByProjectIdAndHaveType(String projectId,String haveType);

    void deleteByProjectId(String projectId);
}
