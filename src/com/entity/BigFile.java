package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class BigFile {
    public String getEntryName() {
        return entryName;
    }

    @JSONField(name="entry-name")
    public String  entryName;

    public int getEntrySize() {
        return entrySize;
    }

    @JSONField(name="entry-size")
    public int  entrySize;

    public int buildNumber;
}

