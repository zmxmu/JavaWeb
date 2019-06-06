package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class UnZipFile {

    public String  suffix;
    @JSONField(name="total-size")
    public String  totalSize;

    public int buildNumber;
}

