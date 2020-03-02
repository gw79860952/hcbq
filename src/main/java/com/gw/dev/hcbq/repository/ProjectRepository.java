package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,String>, JpaSpecificationExecutor<Project> {

    @Query(value="select * from t_project where id in (select proId_1 from t_glpro where proId_2 = :proId union all  select proId_2 from t_glpro where proId_1 = :proId)",nativeQuery = true)
    List<Project> findByGl(@Param("proId") String proId);

    List<Project> findByFormType(String formType);


}
