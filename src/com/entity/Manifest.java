package com.entity;


import com.alibaba.fastjson.annotation.JSONField;

public class Manifest {
    @JSONField(name="package")
    public String  packageName;
    @JSONField(name="android:minSdkVersion")
    public String  minSdkVersion;
    @JSONField(name="android:targetSdkVersion")
    public String  targetSdkVersion;
    @JSONField(name="android:versionCode")
    public String  versionCode;
    @JSONField(name="android:versionName")
    public String  versionName;



    public int buildNumber;


    public float size;

    public long buildTime;

    public float getSize() {
        return size;
    }


    public int getBuildNumber() {
        return buildNumber;
    }
}

