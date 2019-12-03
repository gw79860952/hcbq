package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.ProKfzt;
import com.gw.dev.hcbq.repository.ProKfztRepository;
import com.gw.dev.hcbq.repository.ProjectRepository;
import com.gw.dev.hcbq.service.ProKfztService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ProKfztServiceImpl implements ProKfztService {

    @Resource
    ProKfztRepository proKfztRepository;

    @Override
    public ProKfzt save(ProKfzt proKfzt) {
        return this.proKfztRepository.save(proKfzt);
    }

    @Override
    public ProKfzt getOne(String id) {
        return this.proKfztRepository.getOne(id);
    }

    @Override
    public List<ProKfzt> findAll() {
        return this.proKfztRepository.findAll();
    }

    @Override
    public List<ProKfzt> findByProName(String proName) {
        return this.proKfztRepository.findByProName(proName);
    }

    @Override
    public void delete(String id) {
        this.proKfztRepository.deleteById(id);
    }
}
