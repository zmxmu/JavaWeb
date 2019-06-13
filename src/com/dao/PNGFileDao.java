package com.dao;

import com.entity.PNGFile;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PNGFileDao implements ApkDao {
    @Override
    public List<PNGFile> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from PNGFile where buildNumber='"+buildNumber+"' order by entrySize desc ");
    }

    @Override
    public List<PNGFile> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from PNGFile where buildNumber='"+buildNumber+"' order by entrySize desc limit 10");
    }

    private List<PNGFile> querySql(String sql) throws SQLException{
        List<PNGFile> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            PNGFile item = new PNGFile();
            item.buildNumber = rs.getInt("buildNumber");
            item.entryName = rs.getString("entryName");
            item.entrySize = rs.getFloat("entrySize");
            result.add(item);
        }
        return result;
    }
}
