package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class MethodGroup {

    public String getName() {
        return name;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public String  name;

    @JSONField(name="method-count")
    public int  methodCount;

    public int buildNumber;
}

