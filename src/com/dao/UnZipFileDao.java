package com.dao;

import com.entity.UnZipFile;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnZipFileDao implements ApkDao {
    @Override
    public List<UnZipFile> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from UnZipFile where buildNumber='"+buildNumber+"' order by totalSize desc");
    }

    @Override
    public List<UnZipFile> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from UnZipFile where buildNumber='"+buildNumber+"' order by totalSize desc");
    }

    private List<UnZipFile> querySql(String sql) throws SQLException{
        List<UnZipFile> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            UnZipFile item = new UnZipFile();
            item.buildNumber = rs.getInt("buildNumber");
            item.totalSize = rs.getInt("totalSize");
            item.suffix = rs.getString("suffix");
            result.add(item);
        }
        return result;
    }
}
