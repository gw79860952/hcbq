package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.Search;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SearchRepository extends JpaRepository<Search,String>, JpaSpecificationExecutor<Search> {

}
