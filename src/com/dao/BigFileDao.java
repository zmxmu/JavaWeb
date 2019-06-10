package com.dao;

import com.entity.BigFile;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BigFileDao implements ApkDao {
    @Override
    public List<BigFile> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from BigFile where buildNumber='"+buildNumber+"' order by entrySize desc");
    }

    @Override
    public List<BigFile> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from BigFile where buildNumber='"+buildNumber+"' order by entrySize desc limit 10");
    }

    private List<BigFile> querySql(String sql) throws SQLException{
        List<BigFile> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            BigFile item = new BigFile();
            item.buildNumber = rs.getInt("buildNumber");
            item.entryName = rs.getString("entryName");
            item.entrySize = rs.getInt("entrySize");
            result.add(item);
        }
        return result;
    }
}
