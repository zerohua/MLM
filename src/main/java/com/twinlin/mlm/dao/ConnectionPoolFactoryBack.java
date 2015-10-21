package com.twinlin.mlm.dao;

import org.apache.tomcat.jdbc.pool.DataSource;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ConnectionPoolFactoryBack
{
	private static DataSource dataSource;
	
	static
	{
		try
		{
			Context context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/mlm");
		}
		catch (Exception e)
		{
			System.out.println("%%%%%%JNDI獲取連接池出錯%%%%%%");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		try
		{
			return dataSource.getConnection();
		}
		catch (Exception e)
		{
			System.out.println("%%%%%%通過dataSource獲取Connection出錯%%%%%%");
			e.printStackTrace();
			return null;
		}
	}
	
	public static void close(PreparedStatement pstmt,ResultSet rs)
	{
		try
		{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
		}
		catch(Exception ex)
		{
			System.out.println("***********關閉數據庫出錯***********");
			ex.printStackTrace();
		}
	}
}
