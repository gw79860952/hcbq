package com.gw.dev.hcbq.service;

import com.gw.dev.hcbq.entity.DictionaryValue;

import java.util.List;

public interface DicService {
    public List<DictionaryValue> findByDicCode(String dicCode);
}
