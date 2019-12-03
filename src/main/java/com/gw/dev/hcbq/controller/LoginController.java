package com.gw.dev.hcbq.controller;

import com.gw.dev.hcbq.entity.ResultVO;
import com.gw.dev.hcbq.entity.User;
import com.gw.dev.hcbq.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("hc/login/")
public class LoginController {

    @Resource
    UserService userService;

    @RequestMapping("tologin")
    public String login(){

        return "hc/login";
    }

    @RequestMapping("login")
    @ResponseBody
    public Object login(String account,String password){
        List<User> uList = this.userService.findByAccountAndPassword(account,password);
        if(uList.size() > 0){

            return new ResultVO("success",1L);
        }else{
            return new ResultVO("false",1L);
        }

    }
}
