package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class BigFile {
    public String getEntryName() {
        return entryName;
    }

    @JSONField(name="entry-name")
    public String  entryName;

    public float getEntrySize() {
        return entrySize;
    }

    @JSONField(name="entry-size")
    public float  entrySize;

    public int buildNumber;
}

