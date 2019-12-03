package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.*;
import com.gw.dev.hcbq.repository.GlProRepository;
import com.gw.dev.hcbq.repository.ProjectRepository;
import com.gw.dev.hcbq.repository.ProjectRightDetailRepository;
import com.gw.dev.hcbq.repository.ProjectRightRepository;
import com.gw.dev.hcbq.service.GlProService;
import com.gw.dev.hcbq.service.ProKfztService;
import com.gw.dev.hcbq.service.ProjectService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GlProServiceImpl implements GlProService {

    @Resource
    GlProRepository glProRepository;

    @Override
    public GlPro save(GlPro glPro) {
        return glProRepository.save(glPro);
    }

    @Override
    public GlPro getOne(String id) {
        return null;
    }

    @Override
    public List<GlPro> findAll() {
        return null;
    }

    @Override
    public void delete(String proId_1, String proId_2) {
        this.glProRepository.deleteGl(proId_1, proId_2);
    }

    @Override
    public GlPro findOne(String id1, String id2) {
        List<GlPro> res = this.glProRepository.selectOne(id1,id2);
        if(res != null && res.size() > 0){
            return res.get(0);
        }
        return null;
    }
}
