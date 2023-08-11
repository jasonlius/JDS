package com.xiang.study.JDS.utils;
import com.xiang.study.JDS.dao.GoodsRepository;
import com.xiang.study.JDS.entity.Goods;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Slf4j
@Component
public class JdGoodsCrawler {
    private  String JDSerachUrl = "https://search.jd.com/Search?keyword=";
    @Autowired
    private GoodsRepository goodsRepository;

    public  List<Goods> GetJdDate(String goodsKeyword){
        try {
            log.info("开始爬取"+goodsKeyword+"的网站："+JDSerachUrl+goodsKeyword);
            List<Goods> goodsList  = new ArrayList<Goods>();
            // 发起 HTTP 请求获取 HTML 内容
            Document doc = Jsoup.connect(JDSerachUrl+goodsKeyword).get();
            //获取文档里面的商品列表元素
            Element goodsListDoc = doc.selectFirst("div#J_goodsList");
            Elements goodsDoc = goodsListDoc.select("li");

            // 遍历 li 元素并输出内容
            for (Element goodDOc : goodsDoc) {
                try {
                    String imgPath = goodDOc.selectFirst("img").attr("data-lazy-img");
                    String titleHtml =  goodDOc.selectFirst("div.p-name.p-name-type-2").selectFirst("em").html();
                    String title = Jsoup.parse(titleHtml).text();
                    String price = goodDOc.selectFirst("div.p-price").selectFirst("i").ownText();
                    String seller = goodDOc.selectFirst(".curr-shop.hd-shopname").ownText();
                    UUID uuid = UUID.randomUUID();
                    String id = uuid.toString();
                    Goods good = new Goods(id,title,price,imgPath,seller);
                    goodsList.add(good);
                    goodsRepository.save(good);
                }catch (Exception e){
                    continue;
                }
            }
            return goodsList;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("爬取"+goodsKeyword+"错误");
            return null;
        }
    }

}
