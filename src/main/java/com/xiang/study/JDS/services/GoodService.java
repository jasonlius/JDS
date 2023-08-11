package com.xiang.study.JDS.services;

import com.xiang.study.JDS.entity.Goods;
import com.xiang.study.JDS.entity.UniformData;
import org.springframework.data.domain.Page;

public interface GoodService {
    UniformData save(Goods goods);
    UniformData findByName(String name);

    UniformData deleteByName(String name);

    UniformData getPagedByName(String name, int pageNumber, int pageSize);


}
