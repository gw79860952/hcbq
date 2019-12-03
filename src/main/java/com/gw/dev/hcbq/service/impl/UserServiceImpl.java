package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.User;
import com.gw.dev.hcbq.repository.UserRepository;
import com.gw.dev.hcbq.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserRepository userRepository;

    @Override
    public List<User> findByAccountAndPassword(String account,String password) {
        return userRepository.findByAccountAndPassword(account,password);
    }
}
