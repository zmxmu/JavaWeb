package com.dao;

import com.entity.Manifest;

import java.sql.SQLException;
import java.util.List;

public interface ApkDao<T> {
    List<T> queryAll(String buildNumber) throws SQLException;
    List<T> queryTop(String buildNumber) throws SQLException;
}
