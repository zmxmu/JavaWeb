package com.dao;

import com.entity.Manifest;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManifestDao implements ApkDao {
    @Override
    public List<Manifest> queryAll() throws SQLException{
        List<Manifest> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql("select * from Manifest");
        while(rs!=null && rs.next()){
            Manifest item = new Manifest();
            item.buildNumber = rs.getInt("buildNumber");
            item.packageName = rs.getString("packageName");
            item.minSdkVersion = rs.getString("minSdkVersion");
            item.targetSdkVersion = rs.getString("targetSdkVersion");
            item.versionCode = rs.getString("versionCode");
            item.versionName = rs.getString("versionName");
            item.buildTime = rs.getLong("buildTime");
            item.size = rs.getInt("size");
            result.add(item);
        }
        return result;
    }
}
