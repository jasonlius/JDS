package com.xiang.study.JDS.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UniformData {
    private boolean flag;
    private Object data;
    public UniformData(boolean flag){
        this.flag = flag;
    }
}
