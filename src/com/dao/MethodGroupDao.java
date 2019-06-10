package com.dao;

import com.entity.MethodGroup;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MethodGroupDao implements ApkDao {
    @Override
    public List<MethodGroup> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from MethodGroup where buildNumber='"+buildNumber+"' order by methodCount desc");
    }

    @Override
    public List<MethodGroup> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from MethodGroup where buildNumber='"+buildNumber+"' order by methodCount desc limit 10");
    }

    private List<MethodGroup> querySql(String sql) throws SQLException{
        List<MethodGroup> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            MethodGroup item = new MethodGroup();
            item.buildNumber = rs.getInt("buildNumber");
            item.name = rs.getString("name");
            item.methodCount = rs.getInt("methodCount");
            result.add(item);
        }
        return result;
    }
}
