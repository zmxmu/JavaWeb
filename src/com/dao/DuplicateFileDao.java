package com.dao;

import com.entity.DuplicateFile;
import com.entity.DuplicateFile;
import com.util.DBconn;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DuplicateFileDao implements ApkDao {
    @Override
    public List<DuplicateFile> queryAll(String buildNumber) throws SQLException{
        return querySql("select * from DuplicateFile where buildNumber='"+buildNumber+"' order by size desc");
    }

    @Override
    public List<DuplicateFile> queryTop(String buildNumber) throws SQLException {
        return querySql("select * from DuplicateFile where buildNumber='"+buildNumber+"' order by size desc limit 10");
    }

    private List<DuplicateFile> querySql(String sql) throws SQLException{
        List<DuplicateFile> result = new ArrayList<>();
        ResultSet rs = DBconn.getInstance().selectSql(sql);
        while(rs!=null && rs.next()){
            DuplicateFile item = new DuplicateFile();
            item.buildNumber = rs.getInt("buildNumber");
            item.md5 = rs.getString("md5");
            item.fileSet = rs.getString("fileSet");
            item.size = rs.getInt("size");
            result.add(item);
        }
        return result;
    }
}
