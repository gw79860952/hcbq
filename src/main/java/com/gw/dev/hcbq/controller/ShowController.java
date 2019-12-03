package com.gw.dev.hcbq.controller;

import com.gw.dev.hcbq.entity.*;
import com.gw.dev.hcbq.service.*;
import com.gw.dev.hcbq.util.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hc/show/")
public class ShowController {

    @Resource
    DicService dicService;

    @Resource
    ProjectService projectService;

    @Resource
    ProjectRightService projectRightService;

    @Resource
    ProjectRightDetailService projectRightDetailService;

    @Resource
    ProKfztService proKfztService;

    @RequestMapping("showOne/{type}/{id}")
    public String showOne(Model model, HttpServletRequest request, @PathVariable("id") String id, @PathVariable("type") String type){
        //第一级
        Project pro = this.projectService.getOne(id);

        //第二级
        List<ProjectRight> projectRights = this.projectRightService.findByProjectId(pro.getId());
        //第三级，不每次连数据库查，直接全部拿出来内存里循环快一点
        List<ProjectRightDetail> projectRightDetails = this.projectRightDetailService.findByProjectId(pro.getId());

        //分别是 现有 原始  转授权 权利义务转让
        //发行也是这四种 对应为现有，原始，授权代理发行，发行平台，这里list名字就不改了
        List<ProjectRightDto> ysprojectRightDtoList = new ArrayList<>();
        List<ProjectRightDto> xyprojectRightDtoList = new ArrayList<>();
        List<ProjectRightDto> zsqprojectRightDtoList = new ArrayList<>();
        List<ProjectRightDto> qlywzrprojectRightDtoList = new ArrayList<>();


        for(ProjectRight projectRight : projectRights){
            ProjectRightDto projectRightDto = new ProjectRightDto();
            projectRightDto.setProjectRight(projectRight);
            List<ProjectRightDetail> projectRightDetails1 = new ArrayList<>();
            for (ProjectRightDetail projectRightDetail : projectRightDetails){
                if(projectRightDetail.getProjectRightId().equals(projectRight.getId())){
                    projectRightDetails1.add(projectRightDetail);
                }
                projectRightDto.setProjectRightDetailList(projectRightDetails1);
            }
            //不同类型扔到不同list里去
            if(projectRight.getHaveType().equals("ys")){
                ysprojectRightDtoList.add(projectRightDto);
            }else if(projectRight.getHaveType().equals("xy")){
                xyprojectRightDtoList.add(projectRightDto);
                //这里包含了版权和发行两种
            }else if(projectRight.getHaveType().equals("zsq") || projectRight.getHaveType().equals("sqdlfx")){
                zsqprojectRightDtoList.add(projectRightDto);
                //这里包含了版权和发行两种
            }else if(projectRight.getHaveType().equals("qlywzr") || projectRight.getHaveType().equals("fxpt")){
                qlywzrprojectRightDtoList.add(projectRightDto);
            }
        }

        ProjectDto projectDto = new ProjectDto();
        projectDto.setProject(pro);
        projectDto.setYsprojectRightDtoList(ysprojectRightDtoList);
        projectDto.setXyprojectRightDtoList(xyprojectRightDtoList);
        projectDto.setZsqprojectRightDtoList(zsqprojectRightDtoList);
        projectDto.setQlywzrprojectRightDtoList(qlywzrprojectRightDtoList);
        model.addAttribute("pro",projectDto);

        if("bq".equals(type)){
             List<ProKfzt>  kfztList = this.proKfztService.findByProName(pro.getName());
             model.addAttribute("kfztList",kfztList);
        }
        List<Project> glPros = this.projectService.findByGlProId(pro.getId());
        model.addAttribute("glpros",glPros);
        if("fx".equals(type)){
            return "hc/showOnefx";
        }else{
            return "hc/showOnebq";
        }
    }

    @RequestMapping("showKfzt/{id}")
    public String showKfzt(Model model, HttpServletRequest request, @PathVariable("id") String id){
        //第一级
        Project pro = this.projectService.getOne(id);
        return "hc/showKfzt";
    }
}
