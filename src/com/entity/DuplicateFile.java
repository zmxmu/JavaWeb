package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class DuplicateFile {
    public String  md5;
    public int  size;
    public List<String> files;
    public String fileSet;
    public int buildNumber;
}

