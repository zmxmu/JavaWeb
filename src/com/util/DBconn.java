package com.util;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DBconn {
	static String url = "jdbc:mysql://localhost:3306/apk?useunicuee=true&characterEncoding=utf8";
	static String username = "root"; 
	static String password = "zhengmin";
	static Connection  conn = null;
	static ResultSet rs = null;
	static PreparedStatement ps =null;
	private DBconn(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println("init [SQL驱动程序初始化失败！]");
			e.printStackTrace();
		}
	}
	private static DBconn instance = new DBconn();
	public static DBconn getInstance(){
		return instance;
	}
	public int addUpdDel(String tableName, Map<String,Object> map){
		return addUpdDel(tableName,map,null);
	}
	public int addUpdDel(String tableName, Map<String,Object> map,String excludedColumn){
		StringBuffer sb = new StringBuffer();
		sb.append("insert into "+tableName+"(");
		StringBuffer keys = new StringBuffer();
		StringBuffer values = new StringBuffer();
		int i = 0;
		int size = excludedColumn==null?map.size():map.size()-1;

		for (HashMap.Entry<String, Object> entry : map.entrySet()) {
			if(excludedColumn!=null && excludedColumn.equals(entry.getKey())){
				continue;
			}
			keys.append(entry.getKey());
			values.append("'"+entry.getValue()+"'");
			if(i!=size-1){
				keys.append(",");
				values.append(",");
			}
			i++;
		}
		sb.append(keys);
		sb.append(") values(");
		sb.append(values);
		sb.append(")");
		try {
			PreparedStatement ps =  conn.prepareStatement(sb.toString());
			i =  ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("sql数据库增删改异常");
			e.printStackTrace();
		}
		return i;
	}
	public ResultSet selectSql(String tableName){
		String sql = "select * from "+tableName;
		try {
			ps =  conn.prepareStatement(sql);
			rs =  ps.executeQuery(sql);
		} catch (SQLException e) {
			System.out.println("sql数据库查询异常");
			e.printStackTrace();
		}
		return rs;
	}
	public void closeConn(){
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("sql数据库关闭异常");
			e.printStackTrace();
		}
	}
}
