package com.twinlin.mlm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.concurrent.Future;

import javax.naming.Context;
import javax.naming.InitialContext;

import org.apache.tomcat.jdbc.pool.DataSource;

public class ConnectionPoolFactory
{
    private static DataSource dataSource;

    static
    {
        try
        {
            Context context = new InitialContext ();
            dataSource = (DataSource) context.lookup ("java:comp/env/mlm");
        }
        catch (Exception e)
        {
            System.out.println ("%%%%%%JNDI獲取連接池出錯%%%%%%");
            e.printStackTrace ();
        }
    }

    public static Connection getConnection ()
    {
        try
        {
            Future<Connection> future = dataSource.getConnectionAsync ();
            return future.get ();
        }
        catch (Exception e)
        {
            System.out.println ("%%%%%%通過dataSource獲取Connection出錯%%%%%%");
            e.printStackTrace ();
            return null;
        }
    }

    public static void close (PreparedStatement pstmt, ResultSet rs)
    {
        try
        {
            if (rs != null) rs.close ();
            if (pstmt != null) pstmt.close ();
        }
        catch (Exception ex)
        {
            System.out.println ("***********關閉數據庫出錯***********");
            ex.printStackTrace ();
        }
    }
}
