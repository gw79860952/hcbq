package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.Search;
import com.gw.dev.hcbq.entity.SearchDto;
import com.gw.dev.hcbq.repository.SearchRepository;
import com.gw.dev.hcbq.service.SearchService;
import com.gw.dev.hcbq.util.StringUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    @Resource
    SearchRepository searchRepository;

    @Override
    public Page<Search> findPageByCondition(SearchDto dto) {
        //封装查询对象Specification

        Specification<Search> condition = new Specification<Search>() {

            @Override
            public Predicate toPredicate(Root<Search> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                //定义集合来确定Predicate[] 的长度，因为CriteriaBuilder的or方法需要传入的是断言数组
                List<Predicate> predicates = new ArrayList<>();
                //对客户端查询条件进行判断,并封装Predicate断言对象
                //root.get("company")获取字段名
                //company客户端请求的字段值
                //as(String.class)指定该字段的类型
                if (StringUtil.isNotBlank(dto.getProName())) {
                    Predicate predicate = cb.or(cb.like(root.get("proName").as(String.class), "%" + dto.getProName() + "%")
                            , cb.like(root.get("oldName").as(String.class),"%" + dto.getProName() + "%"));
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getFormType())) {
                    Predicate predicate = cb.equal(root.get("formType").as(String.class), dto.getFormType());
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getGlProId())) {
                    Predicate predicate = cb.equal(root.get("glProId").as(String.class), dto.getGlProId());
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getGlProName())) {
                    Predicate predicate = cb.like(root.get("glProName").as(String.class),"%" + dto.getGlProName() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getHaveType())) {
                    Predicate predicate = cb.equal(root.get("haveType").as(String.class), dto.getHaveType());
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getProjectType())) {
                    Predicate predicate = cb.equal(root.get("projectType").as(String.class), dto.getProjectType());
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getQlname())) {
                    Predicate predicate = cb.equal(root.get("qlname").as(String.class), dto.getQlname());
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getYjName())) {
                    for(String yjn : dto.getYjName().split(",")){
                        Predicate predicate = cb.like(root.get("yjName").as(String.class), "%" + yjn + "%");
                        predicates.add(predicate);
                    }
                }
                if (StringUtil.isNotBlank(dto.getEjName())) {
                    for(String ejn : dto.getEjName().split(",")){
                        Predicate predicate = cb.like(root.get("ejName").as(String.class), "%" + ejn + "%");
                        predicates.add(predicate);
                    }
                }
                if (StringUtil.isNotBlank(dto.getDy())) {
                    Predicate predicate = cb.like(root.get("dy").as(String.class),"%" + dto.getDy() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getDygj())) {
                    Predicate predicate = cb.like(root.get("dygj").as(String.class),"%" + dto.getDygj() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getDyxf())) {
                    Predicate predicate = cb.like(root.get("dyxf").as(String.class),"%" + dto.getDyxf() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getSsgs())) {
                    Predicate predicate = cb.like(root.get("ssgs").as(String.class),"%" + dto.getSsgs() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getSqf())) {
                    Predicate predicate = cb.like(root.get("sqf").as(String.class),"%" + dto.getSqf() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getBsqf())) {
                    Predicate predicate = cb.like(root.get("bsqf").as(String.class),"%" + dto.getBsqf() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getHtmc())) {
                    Predicate predicate = cb.like(root.get("htmc").as(String.class),"%" + dto.getHtmc() + "%");
                    predicates.add(predicate);
                }
                if (StringUtil.isNotBlank(dto.getHtbh())) {
                    Predicate predicate = cb.like(root.get("htbh").as(String.class),"%" + dto.getHtbh() + "%");
                    predicates.add(predicate);
                }
                if ("1".equals(dto.getJsdd())) {
                    Predicate predicate = cb.equal(root.get("jsdd").as(String.class), "1");
                    predicates.add(predicate);
                }else{
                    if ("1".equals(dto.getYj())) {
                        Predicate predicate = cb.isNull(root.get("endDateS"));
                        predicates.add(predicate);
                    }if("3".equals(dto.getYj())){
                        Predicate predicate = cb.or(cb.between(root.get("endDateS"),dto.getQljsks(),dto.getQljsjs()),cb.isNull(root.get("endDateS"))
                                ,cb.equal(root.get("jsdd"),"1"));
                        predicates.add(predicate);
                    }else{
                        if(dto.getQlksks() != null && dto.getQlksjs() == null){
                            Predicate predicate = cb.greaterThanOrEqualTo(root.get("startDateS"),dto.getQlksks());
                            predicates.add(predicate);
                        }
                        if(dto.getQlksks() == null && dto.getQlksjs() != null){
                            Predicate predicate = cb.lessThanOrEqualTo(root.get("startDateS"),dto.getQlksjs());
                            predicates.add(predicate);
                        }
                        if(dto.getQlksks() != null && dto.getQlksjs() != null){
                            Predicate predicate = cb.between(root.get("startDateS"),dto.getQlksks(),dto.getQlksjs());
                            predicates.add(predicate);
                        }

                        if(dto.getQljsks() != null && dto.getQljsjs() == null){
                            Predicate predicate = cb.greaterThanOrEqualTo(root.get("endDateS"),dto.getQljsks());
                            predicates.add(predicate);
                        }
                        if(dto.getQljsks() == null && dto.getQljsjs() != null){
                            Predicate predicate = cb.lessThanOrEqualTo(root.get("endDateS"),dto.getQljsjs());
                            predicates.add(predicate);
                        }
                        if(dto.getQljsks() != null && dto.getQljsjs() != null){
                            Predicate predicate = cb.between(root.get("endDateS"),dto.getQljsks(),dto.getQljsjs());
                            predicates.add(predicate);
                        }
                    }
                }
                //判断结合中是否有数据
                if (predicates.size() == 0) {
                    return null;
                }

                //将集合转化为CriteriaBuilder所需要的Predicate[]
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);

                // 返回所有获取的条件： 条件 or 条件 or 条件 or 条件
                return cb.and(predicateArr);
            }
        };
        Sort sort = new Sort(Sort.Direction.ASC,"proName").and(new Sort(Sort.Direction.DESC,"endDateS"));
        int p  = dto.getPage();
        if(p >= 1){
            p = p-1;
        }
        System.out.println(p);
        Pageable pageable = PageRequest.of(p,dto.getLimit(),sort);
        System.out.println(condition.toString());
        return this.searchRepository.findAll(condition,pageable);
    }
}


