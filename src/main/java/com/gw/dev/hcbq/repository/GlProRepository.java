package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.GlPro;
import com.gw.dev.hcbq.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GlProRepository extends JpaRepository<GlPro,String>, JpaSpecificationExecutor<GlPro> {

    @Modifying
    @Query(value="delete from t_glpro where (proId_1=:id1 and proId_2=:id2) or (proId_1=:id2 and proId_2=:id1)",nativeQuery = true)
    @Transactional
    void deleteGl(@Param("id1") String id1,@Param("id2") String id2);

    @Query(value = "select * from t_glpro where (proId_1 = :id1 and proId_2 = :id2) or (proId_1 = :id2 and proId_2 = :id1)" ,nativeQuery = true )
    List<GlPro> selectOne(@Param("id1") String id1,@Param("id2") String id2);
}
