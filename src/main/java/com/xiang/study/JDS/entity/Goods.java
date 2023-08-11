package com.xiang.study.JDS.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "goods",type = "goods",createIndex = false)
public class Goods {
    @Field(type = FieldType.Auto)
    private String id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private  String title;

    @Field(type = FieldType.Text)
    private String price;
    @Field(type = FieldType.Text)
    private String picPath;
    @Field(type = FieldType.Text)
    private String seller;

}
