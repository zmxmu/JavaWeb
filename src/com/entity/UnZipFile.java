package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class UnZipFile {

    public String getSuffix() {
        return suffix;
    }

    public float getTotalSize() {
        return totalSize;
    }

    public String  suffix;
    @JSONField(name="total-size")
    public float  totalSize;

    public int buildNumber;
}

