package com.yorma.wms.dao.memory.util;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

import javax.sql.DataSource;

public class SimpleDataSource implements DataSource {

	public PrintWriter getLogWriter() throws SQLException {
		return null;
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {

	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {

	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}

	@Override
	public Connection getConnection() throws SQLException {
		return getConnection(username, password);
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url, username, password);
	}

	public static String driver = "com.mysql.jdbc.Driver";
//	public static String url = "jdbc:mysql://10.10.10.171:3306/expresafe-wms?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&mysqlEncoding=utf8";
//	public static String username = "root";
//	public static String password = "Yorma228";
	public static String url = "jdbc:mysql://172.31.1.171:3306/expresafe-wms?autoReconnect=true&useUnicode=true&characterEncoding=utf-8&mysqlEncoding=utf8";
	public static String username = "root";
	public static String password = "yorma8425";
	
	
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

}
