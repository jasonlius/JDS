package com.xiang.study.JDS.services;


import com.xiang.study.JDS.dao.GoodsRepository;
import com.xiang.study.JDS.entity.Goods;
import com.xiang.study.JDS.entity.UniformData;
import com.xiang.study.JDS.services.GoodsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class GoodsServiceImplTest {
    @Autowired
    private GoodsServiceImpl goodsService;
    @Test
    void TestFindByname(){
        UniformData goodsList =  goodsService.findByName("苹果");
        List<Goods> goodsItems = (List<Goods>)goodsList.getData();
        for (Goods goods : goodsItems){
            System.out.println(goods);
        }
    }

    @Test
    void TestGetPageByName(){
        UniformData GoodPage = goodsService.getPagedByName("手机",1,5);
        System.out.println(GoodPage);
    }
}
