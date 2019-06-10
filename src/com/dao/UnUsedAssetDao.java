package com.dao;

import com.entity.UnUsedAsset;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnUsedAssetDao implements ApkDao {
    @Override
    public List<UnUsedAsset> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from UnUsedAsset where buildNumber='"+buildNumber+"'");
    }

    @Override
    public List<UnUsedAsset> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from UnUsedAsset where buildNumber='"+buildNumber+"'limit 10");
    }

    private List<UnUsedAsset> querySql(String sql) throws SQLException{
        List<UnUsedAsset> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            UnUsedAsset item = new UnUsedAsset();
            item.buildNumber = rs.getInt("buildNumber");
            item.name = rs.getString("name");
            result.add(item);
        }
        return result;
    }
}
