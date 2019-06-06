package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class PNGFile {
    @JSONField(name="entry-name")
    public String  entryName;
    @JSONField(name="entry-size")
    public int  entrySize;

    public int buildNumber;
}

