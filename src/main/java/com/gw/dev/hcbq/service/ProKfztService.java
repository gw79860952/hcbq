package com.gw.dev.hcbq.service;


import com.gw.dev.hcbq.entity.ProKfzt;
import com.gw.dev.hcbq.entity.Project;

import java.util.List;

public interface ProKfztService {
    public ProKfzt save(ProKfzt proKfzt);

    public ProKfzt getOne(String id);

    public List<ProKfzt> findAll();

    public List<ProKfzt> findByProName(String proName);

    public void delete(String id);
}
