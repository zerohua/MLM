package com.twinlin.mlm.dao;

import com.twinlin.mlm.execption.DataAccessException;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zero on 15/3/24.
 * 數據存儲對象
 */
public class Dao extends JDBCTemplate
{
    public int getCount (Class<?> clazz)
    {
        String tableName = clazz.getSimpleName ();
        final String sql = "select count(*) from " + tableName;
        return this.execute (new Callback<Integer> ()
        {

            @Override
            public Integer doInCallback (Connection conn,
                                         PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql);
                rs = pstmt.executeQuery ();
                rs.next ();
                return rs.getInt (1);
            }
        });
    }


    public int getCount (Class<?> clazz, String[] columnName, final Object[] rowValue)
    {
        String tableName = clazz.getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        sql.append ("select count(*) from " + tableName);
        sql.append (" where ");
        sql.append (columnName[0] + " like ?");
        for (int i = 1; i < rowValue.length; i++)
            sql.append (" and " + columnName[i]).append (" like ?");
        System.out.println (sql.toString ());
        return this.execute (new Callback<Integer> ()
        {

            @Override
            public Integer doInCallback (Connection conn,
                                         PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                // ? 設值
                for (int i = 1; i <= rowValue.length; i++)
                    pstmt.setObject (i, rowValue[i - 1]);
                rs = pstmt.executeQuery ();
                rs.next ();
                return rs.getInt (1);
            }
        });
    }

    public <T> T getRow (final T t, final int number)
    {
        String tableName = t.getClass ().getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        Field[] fields = t.getClass ().getDeclaredFields ();
        sql.append (" select ").append (fields[0].getName ());
        for (int i = 1; i < fields.length; i++)
            sql.append (",").append (fields[i].getName ());
        sql.append (" from ").append (tableName);
        sql.append (" limit ?");

        System.out.println (sql.toString ());
        return this.execute (new Callback<T> ()
        {
            @Override
            public T doInCallback (Connection conn, PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                // ? 設值
                pstmt.setInt (1, number);
                rs = pstmt.executeQuery ();
                // 得到對象字段所有信息
                if (rs.next ())
                {
                    ResultSetMetaData rsmd = rs.getMetaData ();
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得表列名
                        String columnName = rsmd.getColumnName (i);
                        // 拿到字段名稱
                        Field field = t.getClass ().getDeclaredField (columnName);
                        if (rs.getObject (columnName) != null)
                        {
                            if (!field.isAccessible ())
                                field.setAccessible (true);
                            field.set (t, rs.getObject (columnName));
                        }
                    }
                    return t;
                }
                return null;
            }
        });
    }

    /**
     * @param t     T 類型的對象實例 t，為數據傳輸對象
     * @param saved 要存入數據的字段數組
     * @param <T>   T 類型的 java bean
     * @return 插入記錄的自增id
     */
    public <T> int save (final T t, final String[] saved)
    {
        // 獲得表名
        String tableName = t.getClass ().getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        sql.append (" insert into " + tableName).append ("(");
        // 反射獲取 t 對象所有屬性
        final Field[] fields = t.getClass ().getDeclaredFields ();
        //判斷要存入數據的字段
        for (Field field : fields)
        {
            for (int i = 0; i < saved.length; i++)
            {
                if (saved[i].equalsIgnoreCase (field.getName ()))
                    sql.append (field.getName ()).append (",");
            }
        }
        // 去掉最後一個逗號
        sql.deleteCharAt (sql.length () - 1);
        sql.append (") values(?");
        for (int i = 1; i < saved.length; i++)
            sql.append (",?");
        sql.append (")");
        System.out.println (sql.toString ());
        return this.execute (new Callback<Integer> ()
        {
            @Override
            public Integer doInCallback (Connection conn, PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString (), Statement.RETURN_GENERATED_KEYS);
                int index = 1;
                //
                for (Field field : fields)
                {
                    for (int i = 0; i < saved.length; i++)
                    {
                        if (saved[i].equalsIgnoreCase (field.getName ()))
                        {// 判斷字段是否可以訪問
                            if (!field.isAccessible ())
                                field.setAccessible (true);
                            pstmt.setObject (index, field.get (t));
                            index++;
                        }
                    }
                }
                // 執行sql語句,返回插入記錄的自增id
                pstmt.executeUpdate ();
                rs = pstmt.getGeneratedKeys ();
                if (rs.next ())
                    return rs.getInt (1);
                return null;
            }
        });
    }

    /**
     * @param t          尋找的對象
     * @param columnName 尋找條件的表列名稱
     * @param rowValue   搜尋條件的值
     * @param <T>        尋找對象的類型
     * @return T
     */
    public <T> T search (final T t, String[] columnName, final Object[] rowValue)
    {
        String tableName = t.getClass ().getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        Field[] fields = t.getClass ().getDeclaredFields ();
        sql.append (" select ").append (fields[0].getName ());
        for (int i = 1; i < fields.length; i++)
            sql.append (",").append (fields[i].getName ());
        sql.append (" from ").append (tableName);
        sql.append (" where ");
        sql.append (columnName[0] + " like ?");
        for (int i = 1; i < rowValue.length; i++)
            sql.append (" and " + columnName[i]).append (" like ?");
        System.out.println (sql.toString ());
        return this.execute (new Callback<T> ()
        {
            @Override
            public T doInCallback (Connection conn, PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                // ? 設值
                for (int i = 1; i <= rowValue.length; i++)
                    pstmt.setObject (i, rowValue[i - 1]);
                rs = pstmt.executeQuery ();
                // 得到對象字段所有信息
                if (rs.next ())
                {
                    ResultSetMetaData rsmd = rs.getMetaData ();
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得表列名
                        String columnName = rsmd.getColumnName (i);
                        // 拿到字段名稱
                        Field field = t.getClass ().getDeclaredField (columnName);
                        if (rs.getObject (columnName) != null)
                        {
                            if (!field.isAccessible ())
                                field.setAccessible (true);
                            field.set (t, rs.getObject (columnName));
                        }
                    }
                    return t;
                }
                return null;
            }
        });
    }

    /**
     * @param t          要更新的對象名
     * @param column     要更改的列名
     * @param value      要更改的值
     * @param columnName 尋找的條件列名
     * @param rowValue   尋找的條件值
     * @param <T>        對象類型
     * @return boolean 是否更新成功
     */
    public <T> boolean update (final T t, String[] column, final Object[] value,
                               String columnName, final Object rowValue)
    {
        String tableName = t.getClass ().getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        sql.append (" update " + tableName).append (" set ");

        for (int i = 0; i < column.length; i++)
            sql.append (column[i] + " = ?").append (",");

        sql.deleteCharAt (sql.length () - 1);
        sql.append (" where ").append (columnName + " = ?");
        System.out.println (sql.toString ());
        return this.execute (new Callback<Boolean> ()
        {
            @Override
            public Boolean doInCallback (Connection conn, PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                int index = 1;

                for (int i = 0; i < value.length; i++)
                {
                    pstmt.setObject (index, value[i]);
                    index++;
                }
                pstmt.setObject (index, rowValue);
                // 執行sql語句,返回所影響的行數
                int row = pstmt.executeUpdate ();
                return row > 0;
            }
        });
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     * @throws DataAccessException
     */
    public <T> List<T> getAll (final Class<?> clazz) throws DataAccessException
    {
        String tableName = clazz.getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        Field[] fields = clazz.getDeclaredFields ();
        sql.append (" select ").append (fields[0].getName ());
        for (int i = 1; i < fields.length; i++)
            sql.append (",").append (fields[i].getName ());
        sql.append (" from ").append (tableName);
        System.out.println (sql.toString ());
        return this.execute (new Callback<List<T>> ()
        {

            @SuppressWarnings("unchecked")
            @Override
            public List<T> doInCallback (Connection conn,
                                         PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                rs = pstmt.executeQuery ();
                List<T> list = new ArrayList<T> ();
                ResultSetMetaData rsmd = rs.getMetaData ();
                while (rs.next ())
                {
                    Object obj = clazz.newInstance ();
                    // 得到對象字段所有信息
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得列名
                        String columnName = rsmd.getColumnName (i);
                        // 拿到字段名稱
                        Field field = clazz.getDeclaredField (columnName);
                        if (rs.getObject (columnName) != null)
                        {
                            if (!field.isAccessible ())
                                field.setAccessible (true);
                            field.set (obj, rs.getObject (columnName));

                        }
                    }
                    list.add ((T) obj);
                }
                return list;
            }
        });
    }

    public List<Map<String, Object>> getMemberInfo () throws DataAccessException
    {
        final StringBuffer sql = new StringBuffer ();
        sql.append (" select member._id,member.member_no,name,id,sex,birthday,mobile,tel,email,bank_account," +
                "account_name,city" +
                ",area,address,note,member.create_date,member.create_name,status,slide,recommend,upper,left_lower" +
                ",right_lower,level,project_no,pname");
        sql.append (" from ").append ("member left join member_ext on member._id=member_ext.member");
        sql.append (" left join project on member.member_no=project.member_no ");
        System.out.println (sql.toString ());
        return this.execute (new Callback<List<Map<String, Object>>> ()
        {

            @SuppressWarnings("unchecked")
            @Override
            public List<Map<String, Object>> doInCallback (Connection conn,
                                                           PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                rs = pstmt.executeQuery ();
                List<Map<String, Object>> list = new ArrayList<> ();
                ResultSetMetaData rsmd = rs.getMetaData ();
                while (rs.next ())
                {
                    Map<String, Object> map = new HashMap<> ();
                    // 得到對象字段所有信息
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得列名
                        String columnName = rsmd.getColumnName (i);
                        map.put (columnName, rs.getObject (columnName));
                    }
                    list.add (map);
                }
                return list;
            }
        });
    }

    public <T> List<T> getAll (final Class<?> clazz, String[] columnName, final Object[] rowValue)
    {
        String tableName = clazz.getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        Field[] fields = clazz.getDeclaredFields ();
        sql.append (" select ").append (fields[0].getName ());
        for (int i = 1; i < fields.length; i++)
            sql.append (",").append (fields[i].getName ());
        sql.append (" from ").append (tableName);
        sql.append (" where ");
        sql.append (columnName[0] + " like ?");
        for (int i = 1; i < rowValue.length; i++)
            sql.append (" and " + columnName[i]).append (" like ?");
        System.out.println (sql.toString ());
        return this.execute (new Callback<List<T>> ()
        {
            @SuppressWarnings("unchecked")
            @Override
            public List<T> doInCallback (Connection conn,
                                         PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                // ? 設值
                for (int i = 1; i <= rowValue.length; i++)
                    pstmt.setObject (i, rowValue[i - 1]);
                rs = pstmt.executeQuery ();
                List<T> list = new ArrayList<T> ();
                // 得到對象字段所有信息
                ResultSetMetaData rsmd = rs.getMetaData ();
                while (rs.next ())
                {
                    Object obj = clazz.newInstance ();
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得表列名
                        String columnName = rsmd.getColumnName (i);
                        // 拿到字段名稱
                        Field field = clazz.getDeclaredField (columnName);
                        if (rs.getObject (columnName) != null)
                        {
                            if (!field.isAccessible ())
                                field.setAccessible (true);
                            field.set (obj, rs.getObject (columnName));
                        }
                    }
                    list.add ((T) obj);
                }
                return list;
            }
        });
    }

    public <T> List<T> getAllByDate (final Class<?> clazz, String columnName, final String startDate,
                                     final String endDate)
    {
        String tableName = clazz.getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        Field[] fields = clazz.getDeclaredFields ();
        sql.append (" select ").append (fields[0].getName ());
        for (int i = 1; i < fields.length; i++)
            sql.append (",").append (fields[i].getName ());
        sql.append (" from ").append (tableName);
        sql.append (" where " + columnName + " between ? and ?");

        System.out.println (sql.toString ());
        return this.execute (new Callback<List<T>> ()
        {
            @SuppressWarnings("unchecked")
            @Override
            public List<T> doInCallback (Connection conn,
                                         PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                // ? 設值
                pstmt.setString (1, startDate);
                pstmt.setString (2, endDate);
                rs = pstmt.executeQuery ();
                List<T> list = new ArrayList<> ();
                // 得到對象字段所有信息
                ResultSetMetaData rsmd = rs.getMetaData ();
                while (rs.next ())
                {
                    Object obj = clazz.newInstance ();
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得表列名
                        String columnName = rsmd.getColumnName (i);
                        // 拿到字段名稱
                        Field field = clazz.getDeclaredField (columnName);
                        if (rs.getObject (columnName) != null)
                        {
                            if (!field.isAccessible ())
                                field.setAccessible (true);
                            field.set (obj, rs.getObject (columnName));
                        }
                    }
                    list.add ((T) obj);
                }
                return list;
            }
        });
    }


    /**
     * @param t          要更新的對象名
     * @param column     要更改的列名
     * @param value      要更改的值
     * @param columnName 尋找的條件列名
     * @param rowValue   尋找的條件值
     * @param <T>        T 對象
     * @return boolean 是否更新成功
     */
    public <T> boolean update (final T t, String[] column, final Object[] value,
                               String columnName, final String rowValue)
    {
        String tableName = t.getClass ().getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        sql.append (" update " + tableName).append (" set ");

        for (int i = 0; i < column.length; i++)
            sql.append (column[i] + " = ?").append (",");

        sql.deleteCharAt (sql.length () - 1);
        sql.append (" where ").append (columnName + " = ?");
        System.out.println (sql.toString ());

        return this.execute (new Callback<Boolean> ()
        {
            @Override
            public Boolean doInCallback (Connection conn,
                                         PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                int index = 1;

                for (int i = 0; i < value.length; i++)
                {
                    pstmt.setObject (index, value[i]);
                    index++;
                }
                pstmt.setString (index, rowValue);
                // 執行sql語句,返回所影響的行數
                int row = pstmt.executeUpdate ();
                return row > 0 ? true : false;
            }
        });
    }

    public List<Map<String, Object>> getMember (Class<?> clazz1, Class<?> clazz2, String value1, String value2,
                                                String column, String columnValue)
            throws DataAccessException
    {
        String table1 = clazz1.getSimpleName ();
        String table2 = clazz2.getSimpleName ();
        final StringBuffer sql = new StringBuffer ();
        sql.append (" select *");
        sql.append (" from ").append (table1);
        sql.append (" right join ").append (table2);
        sql.append (" on ").append (value1).append ("=").append (value2);
        sql.append (" where ").append (column).append ("=").append (columnValue);
        System.out.println (sql.toString ());
        return this.execute (new Callback<List<Map<String, Object>>> ()
        {

            @SuppressWarnings("unchecked")
            @Override
            public List<Map<String, Object>> doInCallback (Connection conn,
                                                           PreparedStatement pstmt, ResultSet rs) throws Throwable
            {
                pstmt = conn.prepareStatement (sql.toString ());
                rs = pstmt.executeQuery ();
                List<Map<String, Object>> list = new ArrayList<> ();
                ResultSetMetaData rsmd = rs.getMetaData ();
                while (rs.next ())
                {
                    Map<String, Object> map = new HashMap<> ();
                    // 得到對象字段所有信息
                    for (int i = 1; i <= rsmd.getColumnCount (); i++)
                    { // 取得列名
                        String columnName = rsmd.getColumnName (i);
                        map.put (columnName, rs.getObject (columnName));
                    }
                    list.add (map);
                }
                return list;
            }
        });
    }


}
