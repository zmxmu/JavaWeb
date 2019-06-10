package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DuplicateFile {
    public String  md5;
    public int  size;

    public String getMd5() {
        return md5;
    }

    public int getSize() {
        return size;
    }

    public List<String> getFiles() {
        return files;
    }

    public String getFileSet() {
        return fileSet;
    }

    public int getBuildNumber() {
        return buildNumber;
    }

    public List<String> files;
    public String fileSet;
    public int buildNumber;
}

