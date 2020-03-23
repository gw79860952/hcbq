package com.gw.dev.hcbq.controller;

import com.alibaba.fastjson.support.jaxrs.FastJsonProvider;
import com.gw.dev.hcbq.entity.*;
import com.gw.dev.hcbq.service.*;
import com.gw.dev.hcbq.util.DateUtil;
import com.gw.dev.hcbq.util.FastJsonUtils;
import com.gw.dev.hcbq.util.StringUtil;
import com.gw.dev.hcbq.util.UUIDUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("hc/form/")
public class FormController {
    @Resource
    DicService dicService;

    @Resource
    ProjectService projectService;

    @Resource
    ProjectRightService projectRightService;

    @Resource
    ProjectRightDetailService projectRightDetailService;

    @Resource
    GlProService glProService;

    @RequestMapping("show/{id}/{type}")
    public String toForm(Model model, HttpServletRequest request, @PathVariable("id") String id,@PathVariable("type") String type){
        Project pro = new Project();
        List<ProjectRight> rightsys = new ArrayList<ProjectRight>();
        List<ProjectRight> rightsxy = new ArrayList<ProjectRight>();
        List<ProjectRight> rightszsq = new ArrayList<ProjectRight>();
        List<ProjectRight> rightsqlywzr = new ArrayList<ProjectRight>();
        List<ProjectRight> rightsqdlfx = new ArrayList<ProjectRight>();
        List<ProjectRight> rightfxpt = new ArrayList<ProjectRight>();
        if("new".equals(id)){
            pro.setId(UUIDUtil.getUUID());
            pro.setFormType(type);
        }else{
            pro = this.projectService.getOne(id);
            rightsys = this.projectRightService.findByProjectIdAndHaveType(id,"ys");
            rightsxy = this.projectRightService.findByProjectIdAndHaveType(id,"xy");
            rightszsq = this.projectRightService.findByProjectIdAndHaveType(id,"zsq");
            rightsqlywzr = this.projectRightService.findByProjectIdAndHaveType(id,"qlywzr");
            rightsqdlfx = this.projectRightService.findByProjectIdAndHaveType(id,"sqdlfx");
            rightfxpt = this.projectRightService.findByProjectIdAndHaveType(id,"fxpt");
        }
        List<DictionaryValue> lxs = this.dicService.findByDicCode("lx");
        model.addAttribute("lxs",lxs);
        List<DictionaryValue> jllx = this.dicService.findByDicCode("jllx");
        model.addAttribute("jllx",jllx);
        List<DictionaryValue> qlmc = this.dicService.findByDicCode("qlmc");
        model.addAttribute("qlmc",qlmc);
        model.addAttribute("pro", pro);
        model.addAttribute("rightsys",rightsys);
        model.addAttribute("rightsxy",rightsxy);
        model.addAttribute("rightszsq",rightszsq);
        model.addAttribute("rightsqlywzr",rightsqlywzr);
        model.addAttribute("rightsqdlfx",rightsqdlfx);
        model.addAttribute("rightfxpt",rightfxpt);
        List<Project> polist = new ArrayList<>();
        polist.add(new Project());

        polist.addAll(this.projectService.findByFormType("bq"));

        model.addAttribute("proList",polist);
        List<Project> glPros = this.projectService.findByGlProId(pro.getId());
        model.addAttribute("glpros",glPros);
        if("fx".equals(type)){
            return "hc/showfx";
        }else{
            return "hc/showbq";
        }
    }

    @RequestMapping("temSaveQlxf/{id}/{type}/{haveType}")
    @ResponseBody
    public String temSaveQlxf(Model model, HttpServletRequest request, @PathVariable("id") String id,@PathVariable("type") String type,@PathVariable("haveType") String haveType){
        ProjectRight pr = new ProjectRight();
        if(id.contains("new")) {
            String projectId = id.split("-")[1];
            pr.setId(UUIDUtil.getUUID());
            pr.setProjectId(projectId);
            if (haveType.equals("ys")) {
                pr.setHaveType("原始权利");
            } else if (haveType.equals("xy")) {
                pr.setHaveType("现有权利");
            } else if (haveType.equals("zsq")) {
                pr.setHaveType("转授权");
            } else if (haveType.equals("qlywzr")) {
                pr.setHaveType("权利义务转让");
            }else  if(haveType.equals("sqdlfx")){
                pr.setHaveType("授权代理发行");
            }else if(haveType.equals("fxpt")){
                pr.setHaveType("发行平台");
            }
        }
        pr.setName("请添加详细信息");
        this.projectRightService.save(pr);
        return "success";
    }

    @RequestMapping(value="saveGl/{id1}/{id2}",produces = "application/json; charset=utf-8")
    @ResponseBody
    public String saveGl(Model model, HttpServletRequest request, @PathVariable("id1") String id1,@PathVariable("id2") String id2){
        if(this.glProService.findOne(id1, id2) == null){
            GlPro gl = new GlPro();
            gl.setId(UUIDUtil.getUUID());
            gl.setProId_1(id1);
            gl.setProId_2(id2);
            this.glProService.save(gl);
            return this.projectService.getOne(id1).getName();
        }
        return "success";
    }

    @RequestMapping("qlxf/{id}/{type}/{haveType}")
    public String toQlxf(Model model, HttpServletRequest request, @PathVariable("id") String id,@PathVariable("type") String type,@PathVariable("haveType") String haveType){
        ProjectRight pr = new ProjectRight();
        if(id.contains("new")){
            String projectId = id.split("-")[1];
            pr.setId(UUIDUtil.getUUID());
            pr.setProjectId(projectId);
            pr.setHaveType(haveType);
//            if (haveType.equals("ys")) {
//                pr.setHaveType("原始权利");
//            } else if (haveType.equals("xy")) {
//                pr.setHaveType("现有权利");
//            } else if (haveType.equals("zsq")) {
//                pr.setHaveType("转授权");
//            } else if (haveType.equals("qlywzr")) {
//                pr.setHaveType("权利义务转让");
//            }else  if(haveType.equals("sqdlfx")){
//                pr.setHaveType("授权代理发行");
//            }else if(haveType.equals("fxpt")){
//                pr.setHaveType("发行平台");
//            }else{
//
//            }
        }else{
            pr = this.projectRightService.getOne(id);
            List<ProjectRightDetail> dylist = this.projectRightDetailService.findByProjectRightId(id);
            model.addAttribute("dylist",dylist);
        }
        Project pro = this.projectService.getOne(pr.getProjectId());

        String sq ="no";
        String bsq="no";
        String qlzrxz = "yes";
        String htmc = "yes";
        String htbc = "yes";
        if("bq".equals(type)){
            if(haveType.equals("xy")){
                sq = "授权方";
                bsq = "转出权利接受方";
                qlzrxz = "no";
                htmc = "no";
                htbc = "no";
            }
            if(haveType.equals("ys")){
                sq = "授权方";
            }
            if(haveType.equals("zsq")){
                bsq = "被授权方";
            }
            if(haveType.equals("qlywzr")){
                bsq = "受让方";
            }
        }
        if("fx".equals(type)){
            if(haveType.equals("xy")){
                sq = "授权方";
                bsq = "转出权利接受方";
                qlzrxz = "no";
                htmc = "no";
                htbc = "no";
            }
            if(haveType.equals("ys")){
                sq = "授权方";

            }
            if(haveType.equals("sqdlfx")){
                bsq = "代理公司";
            }
            if(haveType.equals("fxpt")){
                bsq = "播出平台";
            }
        }
        model.addAttribute("qlzrxz",qlzrxz);
        model.addAttribute("htmc",htmc);
        model.addAttribute("htbc",htbc);

        model.addAttribute("sq",sq);
        model.addAttribute("bsq",bsq);
        List<DictionaryValue> fxzt = this.dicService.findByDicCode("fxzt");
        List<DictionaryValue> dlfx = this.dicService.findByDicCode("dlfx");
        List<DictionaryValue> dj = this.dicService.findByDicCode("dj");
        List<DictionaryValue> dy = this.dicService.findByDicCode("dy");
        model.addAttribute("fxzt",fxzt);
        model.addAttribute("dlfx",dlfx);
        model.addAttribute("dj",dj);
        model.addAttribute("dy",dy);
        model.addAttribute("projectId", pro.getId());
        model.addAttribute("protype",pro.getProjectType());
        model.addAttribute("pr",pr);
        model.addAttribute("formType",type);
        List<DictionaryValue> lxs = this.dicService.findByDicCode("lx");
        model.addAttribute("lxs",lxs);
        if("fx".equals(type)){
            return "hc/qlxffx";
        }else{
            return "hc/qlxfbq";
        }

    }

    @RequestMapping("getQlmcList")
    @ResponseBody
    public List<DictionaryValue> getQlmcList(String projectType){
        String qlmcCode = "";
        if(projectType.equals("小说") || projectType.equals("漫画") ){
            qlmcCode = "qlmc_yz";
        }
        if(projectType.equals("有声作品")){
            qlmcCode = "qlmc_ys";
        }
        if(projectType.equals("剧本大纲/故事梗概")){
            qlmcCode = "qlmc_dg";
        }
        if(projectType.equals("剧本")){
            qlmcCode = "qlmc_jb";
        }
        if(projectType.equals("电视剧") ||projectType.equals("电影") || projectType.equals("网络剧")
        ||projectType.equals("动画-剧集") ||projectType.equals("动画-电影")  ||projectType.equals("纪录片-剧集")
                ||projectType.equals("纪录片-电影") ||projectType.equals("舞台剧") || projectType.equals("网络电影")){
            qlmcCode = "qlmc_jbjm";
        }
        if(projectType.equals("游戏")){
            qlmcCode = "qlmc_yx";
        }
        if(projectType.equals("综艺节目")){
            qlmcCode = "qlmc_zyjm";
        }
        List<DictionaryValue> qlmc = this.dicService.findByDicCode(qlmcCode);
        return qlmc;
    }

    @RequestMapping("getQlxf")
    @ResponseBody
    public List<DictionaryValue> getQlXf(String protype,String qlmc,String formType){
        List<DictionaryValue> search = this.dicService.findByDicCode(this.getYjqlDic(qlmc,protype));
        List<DictionaryValue> res  = new ArrayList<DictionaryValue>();
        if(formType.equals("fx")){
            for(DictionaryValue v : search){
                if(v.getValue().equals("发行权") || v.getValue().equals("广播权") || v.getValue().equals("信息网络传播权")
                        || v.getValue().equals("放映权")){
                    res.add(v);
                }
            }
        }else{
            for(DictionaryValue v : search){
                if(!v.getValue().equals("发行权") && !v.getValue().equals("广播权") && !v.getValue().equals("信息网络传播权")
                        && !v.getValue().equals("放映权")){
                    res.add(v);
                }
            }
        }
        return res;
    }

    @RequestMapping("getQlxfej")
    @ResponseBody
    public List<DictionaryValue> getQlXfej(String protype,String qlmc){
//        System.out.println(protype);
//        List<DictionaryValue> res = new ArrayList<>();
        List<DictionaryValue> li =  this.dicService.findByDicCode(this.getEjqlDic(qlmc,protype));
//        for(DictionaryValue dv : li){
//            if(!dv.getValue().contains(protype)){
//                res.add(dv);
//            }
//        }
        return li;
    }

    @RequestMapping("deleteDy")
    @ResponseBody
    public String deleteDy(String id){
        this.projectRightDetailService.delete(id);
        return "success";
    }


    @RequestMapping("saveShow")
    @ResponseBody
    public String saveShow(Project pro){
        if(StringUtil.isNotBlank(pro.getGlProId())){
            Project p = projectService.getOne(pro.getGlProId());
            if(p != null){
                pro.setGlProName(p.getName());
            }
        }
//        Project proj = null;
//        try {
//            proj = projectService.getOne(pro.getId());
//            if(proj != null){
//                if(!proj.getName().equals(pro.getName()) || !proj.getOldName().equals(pro.getOldName())){
//                    pro.setShStatus("0");
//                }
//            }
//        }catch (EntityNotFoundException e){
//            pro.setShStatus("0");
//        }

        this.projectService.save(pro);
        return "success";
    }

    @RequestMapping("getProQl")
    @ResponseBody
    public String getProQl(String projectRightId){
        ProjectRight pr;
        String res = "";
        try {
            pr = this.projectRightService.getOne(projectRightId);
            res = URLEncoder.encode(pr.getYjName()+";"+pr.getEjName(),"utf-8");
        }catch (EntityNotFoundException e){
            pr = null;
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping("saveQlxf")
    @ResponseBody
    public String saveQlxf(ProjectRight projectRight){
        System.out.println(projectRight);
        if(projectRight.getYjName() != null && projectRight.getYjName().endsWith(",")){
            projectRight.setYjName(projectRight.getYjName().substring(0,projectRight.getYjName().length()-1));
        }
        if(projectRight.getEjName() != null && projectRight.getEjName().endsWith(",")){
            projectRight.setEjName(projectRight.getEjName().substring(0,projectRight.getEjName().length()-1));
        }
        projectRight.setShStatus("0");
        ProjectRight pr = this.projectRightService.save(projectRight);

        //下级修改，陆续修改上级审核状态
        Project project = this.projectService.getOne(pr.getProjectId());
        project.setShStatus("0");
        this.projectService.save(project);
        return pr.getId();
    }

    @RequestMapping("getYJList")
    @ResponseBody
    public List<DictionaryValue> getYJList(String projectRightId){
        ProjectRight pr = this.projectRightService.getOne(projectRightId);
        Project p = this.projectService.getOne(pr.getProjectId());
        List<DictionaryValue> search = this.dicService.findByDicCode(this.getYjqlDic(pr.getName(),pr.getProjectType()));
        List<DictionaryValue> res  = new ArrayList<DictionaryValue>();
        if(p.getFormType().equals("fx")){
            for(DictionaryValue v : search){
                if(v.getValue().equals("发行权") || v.getValue().equals("广播权") || v.getValue().equals("信息网络传播权")
                || v.getValue().equals("放映权")){
                    res.add(v);
                }
            }
        }else{
            for(DictionaryValue v : search){
                if(!v.getValue().equals("发行权") && !v.getValue().equals("广播权") && !v.getValue().equals("信息网络传播权")
                        && !v.getValue().equals("放映权")){
                    res.add(v);
                }
            }
        }
        return res;
    }

    @RequestMapping("getEJList")
    @ResponseBody
    public List<DictionaryValue> getEJList(String projectRightId){
        ProjectRight pr = this.projectRightService.getOne(projectRightId);
        Project p = this.projectService.getOne(pr.getProjectId());
        String dicCode = "";
        if(pr.getYjName().contains("改编权")){
            if("有声作品权利".equals(pr.getName())){
                dicCode = "ysejql";
            }else{
                dicCode = "ejql";
            }
        }
        if(pr.getYjName().contains("广播权")){
            dicCode = "jmgbqej";
        }
        return this.dicService.findByDicCode(dicCode);
    }

    @RequestMapping("saveDy")
    @ResponseBody
    public String saveDy(String jsonStr){

        List<ProjectRightDetail> list = FastJsonUtils.getJsonToList(jsonStr,ProjectRightDetail.class);
        for (ProjectRightDetail pd : list){
            System.out.println(pd.toString());
            if((pd.getStartDate() == null || "".equals(pd.getStartDate())) && "0".equals(pd.getKsdd())){
                continue;
            }
            if(pd.getDy() == null || "".equals(pd.getDy())){
                continue;
            }
            if(pd.getId() == null || "".equals(pd.getId())){
                pd.setId(UUIDUtil.getUUID());
            }
            if(pd.getStartDate() != null && !"".equals(pd.getStartDate())){
                int count = pd.getStartDate().split("-").length;
                if(count == 1){
                    pd.setStartDateS(DateUtil.parseDate(pd.getStartDate(),"yyyy"));
                }
                if(count == 2){
                    pd.setStartDateS(DateUtil.parseDate(pd.getStartDate(),"yyyy-MM"));
                }
                if(count == 3){
                    pd.setStartDateS(DateUtil.parseDate(pd.getStartDate(),"yyyy-MM-dd"));
                }
            }
            if(pd.getEndDate() != null && !"".equals(pd.getEndDate())){
                int count = pd.getEndDate().split("-").length;
                if(count == 1){
                    pd.setEndDateS(DateUtil.parseDate(pd.getEndDate(),"yyyy"));
                }
                if(count == 2){
                    pd.setEndDateS(DateUtil.parseDate(pd.getEndDate(),"yyyy-MM"));
                }
                if(count == 3){
                    pd.setEndDateS(DateUtil.parseDate(pd.getEndDate(),"yyyy-MM-dd"));
                }
            }
            pd.setShStatus("0");
            this.projectRightDetailService.save(pd);

            //修改下级，更新上级为待审核
            ProjectRight projectRight = this.projectRightService.getOne(pd.getProjectRightId());
            projectRight.setShStatus("0");
            this.projectRightService.save(projectRight);

            Project project = this.projectService.getOne(pd.getProjectId());
            project.setShStatus("0");
            this.projectService.save(project);

        }
        return "success";
    }

    @RequestMapping("deletePro")
    @ResponseBody
    public String deletePro(String id){
        this.projectService.delete(id);
        return "success";
    }

    @RequestMapping("deleteGl/{id1}/{id2}")
    @ResponseBody
    public String deleteGl( @PathVariable("id1") String id1,@PathVariable("id2") String id2){
        this.glProService.delete(id1, id2);
        return "success";
    }

    @RequestMapping("deleteProRight")
    @ResponseBody
    public String deleteProRight(String id){
        this.projectRightService.delete(id);
        return "success";
    }

    public String getYjqlDic(String qlmc,String protype){
        String dicCode = "";
        //这些类型和电影一样，这里做简化处理
        if("动画-剧集".equals(protype) || "动画-电影".equals(protype) || "纪录片-剧集".equals(protype) || "纪录片-电影".equals(protype)
                || "舞台剧".equals(protype) || "综艺节目".equals(protype) || "游戏".equals(protype) || "网络电影".equals(protype)){
            protype = "电影";
        }
        if("脚本权利".equals(qlmc)){
            qlmc = "剧本权利";
        }
        if("剧目权利（节目）".equals(qlmc)){
            qlmc = "剧目权利";
        }
        if("节目权利".equals(qlmc)){
            qlmc = "剧目权利";
        }
        if("原著权利".equals(qlmc) && "小说".equals(protype)){
            dicCode = "xsyzql";
        }
        if("原著权利".equals(qlmc) && "漫画".equals(protype)){
            dicCode = "mhyzql";
        }
        if("有声作品权利".equals(qlmc) ){
            dicCode = "yszpql";
        }
        if("剧本大纲/故事梗概权".equals(qlmc) ){
            dicCode = "jbdgql";
        }
        if("剧本权利".equals(qlmc) && "剧本".equals(protype)){
            dicCode = "jbjbql";
        }
        if("剧本权利".equals(qlmc) && ("电视剧".equals(protype) || "网络剧".equals(protype) )){
            dicCode = "dsjjbql";
        }
        if("剧目权利".equals(qlmc) && ("电视剧".equals(protype) || "网络剧".equals(protype) )){
            dicCode = "dsjjmql";
        }
        if("剧本权利".equals(qlmc) && "电影".equals(protype)){
            dicCode = "dyjbql";
        }
        if("剧目权利".equals(qlmc) && "电影".equals(protype)){
            dicCode = "dyjjmql";
        }
        if("衍生品开发权".equals(qlmc) ){
            dicCode = "yspkfq";
        }
        return dicCode;
    }

    public String getEjqlDic(String qlmc , String protype){
        String dicCode = "";
        if("改编权".equals(protype)){
            if("有声作品权利".equals(qlmc)){
                dicCode = "ysejql";
            }else{
                dicCode = "ejql";
            }
        }
        if("广播权".equals(protype)){
            dicCode = "jmgbqej";
        }
        return dicCode;
    }
}
