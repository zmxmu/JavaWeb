package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class PNGFile {
    @JSONField(name="entry-name")
    public String  entryName;

    public String getEntryName() {
        return entryName;
    }

    public float getEntrySize() {
        return entrySize;
    }

    @JSONField(name="entry-size")
    public float  entrySize;

    public int buildNumber;
}

