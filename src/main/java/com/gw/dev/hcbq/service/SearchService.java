package com.gw.dev.hcbq.service;



import com.gw.dev.hcbq.entity.Search;
import com.gw.dev.hcbq.entity.SearchDto;
import org.springframework.data.domain.Page;



public interface SearchService {

    public Page<Search> findPageByCondition(SearchDto dto);
}
