package com.xiang.study.JDS.dao;

import com.xiang.study.JDS.entity.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;


public interface GoodsRepository extends ElasticsearchRepository<Goods,String> {
    List<Goods> findByTitle(String name);

    Page<Goods> findByTitle(String name, Pageable pageable);


}
