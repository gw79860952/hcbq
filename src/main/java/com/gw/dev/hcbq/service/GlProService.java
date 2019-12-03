package com.gw.dev.hcbq.service;


import com.gw.dev.hcbq.entity.GlPro;

import java.util.List;

public interface GlProService {
    public GlPro save(GlPro glPro);

    public GlPro getOne(String id);

    public GlPro findOne(String id1,String id2);

    public List<GlPro> findAll();

    public void delete(String proId_1,String proId_2);
}
