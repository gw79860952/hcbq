package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.DictionaryValue;
import com.gw.dev.hcbq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface DicRepository extends JpaRepository<DictionaryValue,String>, JpaSpecificationExecutor<DictionaryValue> {

     List<DictionaryValue> findByDicCode(String dicCode);

}
