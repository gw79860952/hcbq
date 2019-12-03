package com.gw.dev.hcbq.service;

import com.gw.dev.hcbq.entity.User;

import java.util.List;

public interface UserService {
    public List<User> findByAccountAndPassword(String account,String password);
}

