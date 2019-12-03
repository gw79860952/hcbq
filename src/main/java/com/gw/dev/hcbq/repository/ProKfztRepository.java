package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.ProKfzt;
import com.gw.dev.hcbq.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProKfztRepository extends JpaRepository<ProKfzt,String>, JpaSpecificationExecutor<ProKfzt> {
    List<ProKfzt> findByProName(String proName);
}
