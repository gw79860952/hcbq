package com.gw.dev.hcbq.controller;


import com.gw.dev.hcbq.entity.DictionaryValue;
import com.gw.dev.hcbq.entity.ResultVO;
import com.gw.dev.hcbq.entity.Search;
import com.gw.dev.hcbq.entity.SearchDto;
import com.gw.dev.hcbq.service.DicService;
import com.gw.dev.hcbq.service.SearchService;
import com.gw.dev.hcbq.util.DateUtil;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("hc/search/")
public class SearchController {
    @Resource
    SearchService searchService;

    @Resource
    DicService dicService;

    @ResponseBody
    @RequestMapping("search")
    public Object search(Model model, HttpServletRequest request, SearchDto dto){
        String f = request.getParameter("first");
        System.out.println("first:"+f);
        System.out.println(dto.toString());
        dto.setFormType("bq");
        dto.setAsh("1");
        dto.setBsh("1");
        dto.setCsh("1");
        if("4".equals(f)){
            dto.setFormType("bq");
            dto.setAsh("1");
            dto.setBsh("1");
            dto.setCsh("1");
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DATE,180);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
            dto.setQljsks(new Date());
            dto.setQljsjs(calendar.getTime());
        }else{
            if("1".equals(f) || (dto.getQljsks() == null && dto.getQljsjs() == null)){
                //3是仅供首次访问首页时初始化使用的特殊查询项，按要求，结束时间和权利类型不填的话 也默认给这些条件
                dto.setYj("3");
                //dto.setHaveType("xy");
                dto.setQljsks(new Date());
                dto.setQljsjs(DateUtil.parseDate("2050-12-20","yyyy-MM-dd"));
            }
            if(dto.getHaveType() == null || "".equals(dto.getHaveType())){
                dto.setHaveType("xy");
            }
            if(dto.getHaveType() != null && "all".equals(dto.getHaveType())){
                dto.setHaveType(null);
            }
        }

        Page<Search> res =  this.searchService.findPageByCondition(dto);
        return new ResultVO(res.getContent(),res.getTotalElements());
    }

    @ResponseBody
    @RequestMapping("searchSh")
    public Object searchSh(Model model, HttpServletRequest request, SearchDto dto){
        dto.setFormType("bq");
        dto.setAsh("sh");
        Page<Search> res =  this.searchService.findPageByCondition(dto);
        return new ResultVO(res.getContent(),res.getTotalElements());
    }

    @ResponseBody
    @RequestMapping("getDq")
    public Object getDq(Model model, HttpServletRequest request, SearchDto dto){
        dto.setFormType("bq");
        dto.setAsh("1");
        dto.setBsh("1");
        dto.setCsh("1");
        dto.setPage(1);
        dto.setLimit(5);
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DATE,180);
        System.out.println(new SimpleDateFormat("yyyy-MM-dd").format(calendar.getTime()));
        dto.setQljsks(new Date());
        dto.setQljsjs(calendar.getTime());
        Page<Search> res =  this.searchService.findPageByCondition(dto);
        return res.getTotalElements();
    }

    @RequestMapping("index")
    public String index(Model model, HttpServletRequest request){

        List<DictionaryValue> lxs = this.dicService.findByDicCode("lx");
        model.addAttribute("lxs",lxs);

        List<DictionaryValue> qlmcs = this.dicService.findByDicCode("qlmc");
        model.addAttribute("qlmcs",qlmcs);

        List<DictionaryValue> qlfxyjs = this.dicService.findByDicCode("qlfxyj");
        model.addAttribute("qlfxyjs",qlfxyjs);

        List<DictionaryValue> qlfxejs = this.dicService.findByDicCode("qlfxej");
        model.addAttribute("qlfxejs",qlfxejs);


        List<DictionaryValue> dys = this.dicService.findByDicCode("dy");
        model.addAttribute("dys",dys);

        return "hc/search";
    }

    @RequestMapping("indexSh")
    public String indexSh(Model model, HttpServletRequest request){

        List<DictionaryValue> lxs = this.dicService.findByDicCode("lx");
        model.addAttribute("lxs",lxs);

        List<DictionaryValue> qlmcs = this.dicService.findByDicCode("qlmc");
        model.addAttribute("qlmcs",qlmcs);

        List<DictionaryValue> qlfxyjs = this.dicService.findByDicCode("qlfxyj");
        model.addAttribute("qlfxyjs",qlfxyjs);

        List<DictionaryValue> qlfxejs = this.dicService.findByDicCode("qlfxej");
        model.addAttribute("qlfxejs",qlfxejs);

        List<DictionaryValue> dys = this.dicService.findByDicCode("dy");
        model.addAttribute("dys",dys);

        return "hc/searchSh";
    }
}
