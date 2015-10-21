package com.twinlin.mlm.dao;


import com.twinlin.mlm.execption.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class JDBCTemplate
{
	private Connection conn = null;

	public JDBCTemplate ()
	{
		conn = ConnectionPoolFactory.getConnection ();
	}

	protected interface Callback<E>
	{
		E doInCallback (Connection conn, PreparedStatement pstmt, ResultSet rs)
				throws Throwable;
	}

	protected <E> E execute (Callback<E> callback) throws DataAccessException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try
		{
			return callback.doInCallback (conn, pstmt, rs);
		}
		catch (Throwable t)
		{
			throw new DataAccessException (t.getMessage ());
		}
		finally
		{
			ConnectionPoolFactory.close (pstmt, rs);
		}

	}

	public void close ()
	{
		try
		{
			if (conn != null)
				conn.close ();
		}
		catch (SQLException ex)
		{
			ex.printStackTrace ();
		}
	}

	public void commit ()
	{
		try
		{
			conn.commit ();
		}
		catch (SQLException e)
		{
			e.printStackTrace ();
		}
	}

	public void roolback ()
	{
		try
		{
			conn.rollback ();
		}
		catch (SQLException e)
		{
			e.printStackTrace ();
		}
	}

	public void setAutoCommit (boolean b)
	{
		try
		{
			conn.setAutoCommit (b);
		}
		catch (SQLException e)
		{
			e.printStackTrace ();
		}
	}
}
