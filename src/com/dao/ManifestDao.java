package com.dao;

import com.entity.Manifest;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManifestDao implements ApkDao {
    @Override
    public List<Manifest> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from Manifest order by buildTime desc");
    }

    @Override
    public List<Manifest> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from Manifest order by buildTime asc limit 6");
    }

    private List<Manifest> querySql(String sql) throws SQLException{
        List<Manifest> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            Manifest item = new Manifest();
            item.buildNumber = rs.getInt("buildNumber");
            item.packageName = rs.getString("packageName");
            item.minSdkVersion = rs.getString("minSdkVersion");
            item.targetSdkVersion = rs.getString("targetSdkVersion");
            item.versionCode = rs.getString("versionCode");
            item.versionName = rs.getString("versionName");
            item.buildTime = rs.getLong("buildTime");
            item.size = rs.getFloat("size");
            result.add(item);
        }
        return result;
    }
}
