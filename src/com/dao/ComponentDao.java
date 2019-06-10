package com.dao;

import com.entity.Component;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ComponentDao implements ApkDao {
    @Override
    public List<Component> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from Component where buildNumber='"+buildNumber+"' order by totalSize desc");
    }

    @Override
    public List<Component> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from Component where buildNumber='"+buildNumber+"' order by totalSize desc");
    }

    private List<Component> querySql(String sql) throws SQLException{
        List<Component> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            Component item = new Component();
            item.buildNumber = rs.getInt("buildNumber");
            item.totalSize = rs.getInt("totalSize");
            item.suffix = rs.getString("suffix");
            result.add(item);
        }
        return result;
    }
}
