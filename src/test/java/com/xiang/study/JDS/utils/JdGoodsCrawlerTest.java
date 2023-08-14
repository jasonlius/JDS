package com.xiang.study.JDS.utils;
import com.xiang.study.JDS.entity.Goods;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JdGoodsCrawlerTest {
    @Autowired
    private JdGoodsCrawler jdGoodsCrawler;
    @Test
    void testGetJdDate(){
       List<Goods> goodsList =  jdGoodsCrawler.GetJdDate("空调");
       for(Goods goods : goodsList){
           System.out.println(goods);
       }
    }
}
