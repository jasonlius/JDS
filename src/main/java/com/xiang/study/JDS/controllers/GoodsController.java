package com.xiang.study.JDS.controllers;
import com.xiang.study.JDS.entity.UniformData;
import com.xiang.study.JDS.services.GoodsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsServiceImpl goodsService;


    @DeleteMapping("/{name}")
    public UniformData deleteGoodsByName(@PathVariable String name){
        return goodsService.deleteByName(name);
    }

    @GetMapping("/{name}")
    public UniformData getGoods(@PathVariable String name){
        return goodsService.findByName(name);
    }

    @GetMapping("/{name}/{currentPage}") public UniformData getPage(@PathVariable("name") String name,
                                                                    @PathVariable("currentPage") int currentPage){
        return goodsService.getPagedByName(name,currentPage,25);
    }
}
