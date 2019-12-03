package com.gw.dev.hcbq.controller;

import com.gw.dev.hcbq.entity.*;
import com.gw.dev.hcbq.service.*;
import com.gw.dev.hcbq.util.DateUtil;
import com.gw.dev.hcbq.util.FastJsonUtils;
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
@RequestMapping("hc/kfzt/")
public class kfztController {
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

    @RequestMapping("show/{proId}")
    public String toForm(Model model, HttpServletRequest request, @PathVariable("proId") String proId){
        Project pro = this.projectService.getOne(proId);
        List<ProKfzt> kfztList = this.proKfztService.findByProName(pro.getName());
        if(kfztList == null || kfztList.size() == 0){
            kfztList = null;
        }
        model.addAttribute("pro",pro);
        model.addAttribute("kzList",kfztList);

        List<DictionaryValue> lxs = this.dicService.findByDicCode("lx");
        model.addAttribute("lxs",lxs);

        List<DictionaryValue> kfzts = this.dicService.findByDicCode("fxzt");
        model.addAttribute("kfzts",kfzts);

        List<DictionaryValue> qlfls = this.dicService.findByDicCode("qlfl");
        model.addAttribute("qlfls",qlfls);

        return "hc/showKfzt";
    }

    @RequestMapping("deleteKz")
    @ResponseBody
    public String deleteKz(String id){
        this.proKfztService.delete(id);
        return "success";
    }

    @RequestMapping("saveKz")
    @ResponseBody
    public String saveKz(String jsonStr){

        List<ProKfzt> list = FastJsonUtils.getJsonToList(jsonStr,ProKfzt.class);
        for (ProKfzt kz : list){
            System.out.println(kz.toString());
            if(kz.getBeginDate()== null || "".equals(kz.getBeginDate())){
                continue;
            }
            if(kz.getLx()== null || "".equals(kz.getLx())){
                continue;
            }
            if(kz.getId() == null || "".equals(kz.getId())){
                kz.setId(UUIDUtil.getUUID());
            }
            if(kz.getBeginDate() != null && !"".equals(kz.getBeginDate())){
                int count = kz.getBeginDate().split("-").length;
                if(count == 1){
                    kz.setBeginDateS(DateUtil.parseDate(kz.getBeginDate(),"yyyy"));
                }
                if(count == 2){
                    kz.setBeginDateS(DateUtil.parseDate(kz.getBeginDate(),"yyyy-MM"));
                }
                if(count == 3){
                    kz.setBeginDateS(DateUtil.parseDate(kz.getBeginDate(),"yyyy-MM-dd"));
                }
            }
            if(kz.getEndDate() != null && !"".equals(kz.getEndDate())){
                int count = kz.getEndDate().split("-").length;
                if(count == 1){
                    kz.setEndDateS(DateUtil.parseDate(kz.getEndDate(),"yyyy"));
                }
                if(count == 2){
                    kz.setEndDateS(DateUtil.parseDate(kz.getEndDate(),"yyyy-MM"));
                }
                if(count == 3){
                    kz.setEndDateS(DateUtil.parseDate(kz.getEndDate(),"yyyy-MM-dd"));
                }
            }
            this.proKfztService.save(kz);
        }
        return "success";
    }
}
