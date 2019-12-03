package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.ProjectRightDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProjectRightDetailRepository extends JpaRepository<ProjectRightDetail,String>, JpaSpecificationExecutor<ProjectRightDetail> {
    List<ProjectRightDetail> findByProjectRightId(String projectRightId);

    List<ProjectRightDetail> findByProjectId(String projectId);
}
