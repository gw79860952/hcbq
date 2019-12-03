package com.gw.dev.hcbq.service.impl;

import com.gw.dev.hcbq.entity.DictionaryValue;
import com.gw.dev.hcbq.repository.DicRepository;
import com.gw.dev.hcbq.service.DicService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DicServiceImpl implements DicService {
    @Resource
    DicRepository dicRepository;

    @Override
    public List<DictionaryValue> findByDicCode(String dicCode) {
        return dicRepository.findByDicCode(dicCode);
    }
}
