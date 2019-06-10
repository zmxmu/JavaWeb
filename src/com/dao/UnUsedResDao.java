package com.dao;

import com.entity.UnUsedRes;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnUsedResDao implements ApkDao {
    @Override
    public List<UnUsedRes> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from UnUsedRes where buildNumber='"+buildNumber+"'");
    }

    @Override
    public List<UnUsedRes> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from UnUsedRes where buildNumber='"+buildNumber+"'limit 10");
    }

    private List<UnUsedRes> querySql(String sql) throws SQLException{
        List<UnUsedRes> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            UnUsedRes item = new UnUsedRes();
            item.buildNumber = rs.getInt("buildNumber");
            item.name = rs.getString("name");
            result.add(item);
        }
        return result;
    }
}
