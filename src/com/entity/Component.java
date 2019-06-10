package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class Component {

    public String getSuffix() {
        return suffix;
    }

    public String  suffix;

    public float getTotalSize() {
        return totalSize;
    }

    @JSONField(name="total-size")
    public float  totalSize;

    public int buildNumber;
}

