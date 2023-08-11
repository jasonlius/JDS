package com.xiang.study.JDS.services;
import com.xiang.study.JDS.dao.GoodsRepository;
import com.xiang.study.JDS.entity.Goods;
import com.xiang.study.JDS.entity.UniformData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Slf4j
@Service
public class GoodsServiceImpl  implements GoodService {

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public UniformData save(Goods goods) {
        try {
            goodsRepository.save(goods);
            return new UniformData(true);
        }catch (Exception e){
            log.error("保存错误"+e.getMessage());
            return new UniformData(false);
        }
    }

    @Override
    public UniformData findByName(String name) {
        try {
           List<Goods> goodsList =  goodsRepository.findByTitle(name);
           if(goodsList != null){
               return new UniformData(true,goodsList);
           }else {
               return new UniformData(false);
           }
        }catch (Exception e){
            log.error("查找异常:"+name+"错误信息为："+e.getMessage());
            return new UniformData(false);
        }
    }

    @Override
    public UniformData deleteByName(String name) {

        try {
                goodsRepository.deleteById(name);
                return new UniformData(true);

        }catch (Exception e){
            log.error("删除异常:"+e.getMessage());
            return new UniformData(false);
        }
    }

    @Override
    public UniformData getPagedByName(String name, int pageNumber, int pageSize) {
        try {
            Pageable pageable = PageRequest.of(pageNumber, pageSize);
            Page<Goods> goodsPage = goodsRepository.findByTitle(name, pageable);
            return new UniformData(true,goodsPage);
        }catch (Exception e){
            log.error("错误！查询"+name+"的分页信息错误,错误为："+e.getMessage());
            return new UniformData(false);
        }


    }

}
