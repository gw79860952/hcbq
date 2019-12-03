package com.gw.dev.hcbq.repository;

import com.gw.dev.hcbq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserRepository  extends JpaRepository<User,String>, JpaSpecificationExecutor<User> {
    List<User> findByAccountAndPassword(String account, String password);
}
