package com.dao;

import com.entity.Manifest;

import java.sql.SQLException;
import java.util.List;

public interface ApkDao {
    List<Manifest> queryAll() throws SQLException;
    List<Manifest> queryTop() throws SQLException;
}
