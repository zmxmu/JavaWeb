package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class MethodGroup {

    public String  name;

    @JSONField(name="method-count")
    public int  methodCount;

    public int buildNumber;
}

