package com.twinlin.mlm.service;

import com.twinlin.mlm.dao.Dao;
import com.twinlin.mlm.dto.*;
import com.twinlin.mlm.execption.DataAccessException;
import com.twinlin.mlm.execption.ServiceException;
import com.twinlin.mlm.util.AccessUtil;
import com.twinlin.mlm.util.MD5Encrypt;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zero on 15/3/25.
 */
public class GenericService implements Service
{
    @Override
    public int getCount (Class<?> clazz) throws DataAccessException
    {
        Dao dao = new Dao ();
        int count;
        try
        {
            count = dao.getCount (clazz);
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("getCount failure," + ex);
        }
        finally
        {
            dao.close ();
        }
        return count;
    }

    @Override
    public int getCount (Class<?> clazz, String[] columnName, Object[] rowValue) throws DataAccessException
    {
        Dao dao = new Dao ();
        int count;
        try
        {
            count = dao.getCount (clazz, columnName, rowValue);
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("getCount failure," + ex);
        }
        finally
        {
            dao.close ();
        }
        return count;
    }

    @Override
    public <T> T getRow (T t, int number) throws DataAccessException
    {
        Dao dao = new Dao ();
        try
        {
            t = dao.getRow (t, number);
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("getRow failure," + ex);
        }
        finally
        {
            dao.close ();
        }

        return t;
    }

    /**
     * @param t   T 類型的對象實例 t，為數據傳輸對象
     * @param map 欲新增到資料庫的數據，key 字段名：value 字段值
     * @param <T> T 類型的 java bean
     * @return
     * @throws DataAccessException
     */
    @Override
    public <T> int add (T t, Map<String, String[]> map, String flag) throws DataAccessException
    {

        Dao dao = new Dao ();
        // 得到傳入所有參數
        Set<Map.Entry<String, String[]>> set = map.entrySet ();
        // 用來存操作對象所有成員名
        StringBuilder saved = new StringBuilder ();
        // Iterator all key and value
        for (Iterator<Map.Entry<String, String[]>> it = set.iterator (); it.hasNext (); )
        {
            Map.Entry<String, String[]> entry = it.next ();
            for (String value : entry.getValue ())
            {
                // 排除空值和業務控制參數的值
                if (!"".equals (value) && !value.equals (flag))
                {
                    if (flag.equals ("managerservice") && entry.getKey ().equals ("manager_pwd"))
                    {
                        value = MD5Encrypt.encode (value);
                    }
                    // 將filed name 存到 StirngBuffer內
                    saved.append (entry.getKey ()).append (",");
                    // 將filed name 和 value 存到操作對象內
                    AccessUtil.setValue (t, entry.getKey ().trim (), value);
                }
            }
        }

        try
        { // 去掉StringBuffer(saved)最後一個逗號
            saved.deleteCharAt (saved.length () - 1);

            // 新增到資料庫并返回主鍵ID
            return dao.save (t, saved.toString ().split (","));
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("資料寫入錯誤，請檢查填入格式是否正確</br>" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
    }

    @Override
    public <T> void add (Class<T> clazz, List<Map<String, String[]>> list) throws DataAccessException
    {
        Dao dao = new Dao ();
        dao.setAutoCommit (false);
        try
        {
            // 得到傳入所有參數
            for (Map<String, String[]> map : list)
            {
                T t = clazz.newInstance ();
                Set<Map.Entry<String, String[]>> set = map.entrySet ();
                // 用來存操作對象所有成員名
                StringBuilder saved = new StringBuilder ();
                // Iterator all key and value
                for (Iterator<Map.Entry<String, String[]>> it = set.iterator (); it.hasNext (); )
                {
                    Map.Entry<String, String[]> entry = it.next ();
                    for (String value : entry.getValue ())
                    {
                        // 排除空值
                        if (!"".equals (value))
                        {
                            if (entry.getKey ().equals ("last_pv"))
                            {
                                Member member = dao.search (new Member (),new String[]{"member_no"}
                                        ,new Object[]{map.get("member_no")[0]});
                                dao.update (new Member_Ext (), new String[]{"last_pv"}, new Object[]{value},
                                        "member", member.get_id ());
                            }
                            // 將filed name 存到 StirngBuffer內
                            saved.append (entry.getKey ()).append (",");
                            // 將filed name 和 value 存到操作對象內
                            AccessUtil.setValue (t, entry.getKey ().trim (), value);

                        }
                    }
                }
                saved.deleteCharAt (saved.length () - 1);
                dao.save (t, saved.toString ().split (","));
            }
//            dao.commit ();
        }
        catch (Exception ex)
        {
            dao.roolback ();
            throw new DataAccessException ("資料寫入錯誤，請檢查填入格式是否正確</br>" + ex.getMessage ());
        }
        finally
        {
//            dao.setAutoCommit (true);
            dao.close ();
        }
    }

    /**
     * @param m   M 類型的對象實例 m，為數據傳輸對象
     * @param e   E 類型的對象實例 e，為數據傳輸對象
     * @param p   P 類型的對象實例 p，為數據傳輸對象
     * @param map 欲新增到資料庫的數據，key 字段名：value 字段值
     * @param <M> M 類型的 java bean
     * @param <E> E 類型的 java bean
     * @param <P> P 類型的 java bean
     * @return boolean
     * @throws DataAccessException
     */
    @Override
    public <M, E, P> boolean memberAdd (M m, E e, P p, Map<String, String[]> map) throws DataAccessException
    {
        int upperId = 0;
        String upperPos = null;
        // 取得 BasicDAO 對象 --> JDBC 操作 CRUD 方法
        Dao dao = new Dao ();
        // 得到傳入所有參數
        Set<Map.Entry<String, String[]>> set = map.entrySet ();
        // 用來存 Member 所有成員名
        StringBuilder main = new StringBuilder ();
        // 用來存 Member_Ext 所有成員名
        StringBuilder ext = new StringBuilder ();
        // 用來存 Project 所有成員名
        StringBuilder proj = new StringBuilder ();
        // 用來存 Installment 所有成員名
        StringBuilder inst = null;
        Installment i = null;
        dao.setAutoCommit (false);
        try
        {
            // Iterator all key and value
            for (Iterator<Map.Entry<String, String[]>> it = set.iterator (); it.hasNext (); )
            {
                Map.Entry<String, String[]> entry = it.next ();
                for (String value : entry.getValue ())
                { // 排除空值
                    if (!"".equals (value))
                    {//篩選 Member_Ext bean
                        String key = entry.getKey ();
                        if (key.equals ("status") || key.equals ("slide") || key.equals ("recommend")
                                || key.equals ("upper") || key.equals ("position") || key.equals ("level"))
                        {
                            Member member;
                            switch (key)
                            {
                                case "slide":
                                case "status":
                                    ext.append (key).append (",");
                                    AccessUtil.setValue (e, entry.getKey ().trim (), value);
                                    break;
                                case "recommend":
                                    member = dao.search (new Member (), new String[]{"member_no"}
                                            , new Object[]{value});
                                    if (member != null)
                                    {
                                        int recommendId = member.get_id ();
                                        ext.append (key).append (",");
                                        AccessUtil.setValue (e, key.trim (), recommendId);
                                    }
                                    break;
                                case "upper":
                                    member = dao.search (new Member (), new String[]{"member_no"}
                                            , new Object[]{value});

                                    if (member != null)
                                    {
                                        upperId = member.get_id ();
                                        ext.append (key).append (",");
                                        AccessUtil.setValue (e, key.trim (), upperId);
                                    }
                                    break;
                                case "position":
                                    if (upperId != 0)
                                    {
                                        upperPos = value;
                                    }
                                    break;
                                case "level":
                                    break;
                            }

                        }
                        else if (key.equals ("installment") || key.equals ("pay") || key.equals ("pay_method") ||
                                key.equals ("project_no") || key.equals ("pname") || key.equals ("price") ||
                                key.equals ("pv") || key.equals ("stage") || key.equals ("stage_star") ||
                                key.equals ("stage_end") || key.equals ("last_pv"))
                        {
                            if (key.equals ("installment") && value.equals ("yes"))
                            {
                                inst = new StringBuilder ();
                                i = new Installment ();
                            }
                            proj.append (key).append (",");
                            AccessUtil.setValue (p, key.trim (), value);
                        }
                        else
                        {
                            if (key.equals ("member_pwd"))
                            {
                                value = MD5Encrypt.encode (value);
                            }
                            // 將 Member filed name 存到 StirngBuffer內
                            main.append (entry.getKey ()).append (",");
                            // 將Member filed name 和 value 存到操作對象內
                            AccessUtil.setValue (m, entry.getKey ().trim (), value);
                        }
                    }
                }
            }

            // 去掉 Member StringBuffer(saved)最後一個逗號
            main.deleteCharAt (main.length () - 1);

            // 新增 Member 到資料庫并返回主鍵ID
            int id = dao.save (m, main.toString ().split (","));
            // 把新增成功的 Member 主鍵ID 加入到上線的左或右位置
            if (upperId != 0)
            {
                dao.update (new Member_Ext (), new String[]{upperPos}, new Object[]{id}, "member", upperId);
            }

            //加入 member table 主鍵id 到 member_ext
            ext.append ("member").append (",");
            AccessUtil.setValue (e, "member", id);
            //加入 member_level 主鍵id 到 member_ext
            Member_Level member_level = dao.search (new Member_Level (), new String[]{"mem_level"},
                    new Object[]{"會員"});
            if (member_level != null)
            {
                ext.append ("level").append (",");
                AccessUtil.setValue (e, "level", member_level.get_id ());
            }
            ext.deleteCharAt (ext.length () - 1);
            dao.save (e, ext.toString ().split (","));

            Member member = (Member) m;
            proj.append ("member_no").append (",");
            AccessUtil.setValue (p, "member_no", member.getMember_no ());
            proj.append ("member_name").append (",");
            AccessUtil.setValue (p, "member_name", member.getName ());
            proj.append ("create_name").append (",");
            AccessUtil.setValue (p, "create_name", member.getCreate_name ());
            proj.append ("create_date").append (",");
            AccessUtil.setValue (p, "create_date", member.getCreate_date ());
            proj.deleteCharAt (proj.length () - 1);
            dao.save (p, proj.toString ().split (","));

            int instId = 0;
            if (i != null)
            {
                Project project = (Project) p;
                inst.append ("project_no").append (",");
                AccessUtil.setValue (i, "project_no", project.getProject_no ());
                inst.append ("member_no").append (",");
                AccessUtil.setValue (i, "member_no", member.getMember_no ());
                inst.append ("stage1").append (",");
                AccessUtil.setValue (i, "stage1", member.getCreate_date ());
                inst.append ("stage1_sum").append (",");
                AccessUtil.setValue (i, "stage1_sum", project.getPrice ());
                inst.append ("create_name").append (",");
                AccessUtil.setValue (i, "create_name", member.getCreate_name ());
                inst.append ("create_date").append (",");
                AccessUtil.setValue (i, "create_date", member.getCreate_date ());

                inst.deleteCharAt (inst.length () - 1);
                instId = dao.save (i, inst.toString ().split (","));
            }
            dao.commit ();
            return (instId > 0);
        }
        catch (Exception ex)
        {
            dao.roolback ();
            throw new DataAccessException ("資料寫入錯誤，請檢查填入格式是否正確 /n" + ex.getMessage ());
        }
        finally
        {
            dao.setAutoCommit (true);
            dao.close ();
        }
    }


    /**
     * @param t    T 類型的對象實例 t
     * @param map  欲查詢的字段數據，key 字段名：value 字段值
     * @param flag 查詢控制參數
     * @param <T>  T 類型的對象
     * @return boolean
     * @throws DataAccessException
     */
    @Override
    public <T> T search (T t, Map<String, String[]> map, String flag) throws DataAccessException
    {
        // 取得 BasicDAO 對象 --> JDBC 操作 CRUD 方法
        Dao dao = new Dao ();
        // 得到 傳入所有參數
        Set<Map.Entry<String, String[]>> set = map.entrySet ();

        String verifyName = null;
        String verifyValue = null;
        for (Iterator<Map.Entry<String, String[]>> it = set.iterator (); it
                .hasNext (); )
        {
            Map.Entry<String, String[]> entry = it.next ();

            for (String value : entry.getValue ())
            {
                if (!value.equals ("") && !value.equals (flag))
                {
                    verifyName = entry.getKey ().trim ();
                    verifyValue = value;
                }
            }
        }
        try
        {
            t = dao.search (t, new String[]{verifyName}, new Object[]{verifyValue});
            return t;
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("資料讀取錯誤，請檢查填入格式是否正確");
        }
        finally
        {
            dao.close ();
        }
    }

    /**
     * @param t     T 類型的對象實例 t,找查類型
     * @param name  search 條件名
     * @param value search 條件值
     * @param <T>   T 類型的對象
     * @return 找到的值，返回為一對象 T
     * @throws DataAccessException
     */
    @Override
    public <T> T search (T t, String name, Object value) throws DataAccessException
    {
        Dao dao = new Dao ();
        try
        {
            // search t table 取得 條件相等的row，將值不為空的column返回為一對象
            t = dao.search (t, new String[]{name}, new Object[]{value});
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("search failure," + ex);
        }
        finally
        {
            dao.close ();
        }
        return t;
    }

    @Override
    public <T> T search (T t, String[] names, Object[] values) throws DataAccessException
    {
        Dao dao = new Dao ();
        try
        {
            // search t table 取得 條件相等的row，將值不為空的column返回為一對象
            t = dao.search (t, names, values);
        }
        catch (Exception ex)
        {
            throw new DataAccessException ("search failure," + ex);
        }
        finally
        {
            dao.close ();
        }
        return t;
    }

    /**
     * @param clazz
     * @param <T>
     * @return
     * @throws ServiceException
     */
    @Override
    public <T> List<T> getAll (Class<?> clazz) throws ServiceException
    {
        Dao dao = new Dao ();
        List<T> list = null;
        try
        {
            list = dao.getAll (clazz);
        }
        catch (DataAccessException ex)
        {
            throw new ServiceException ("資料取得失敗-->" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> getMemberInfo ()
            throws ServiceException
    {
        Dao dao = new Dao ();
        List<Map<String, Object>> list = null;
        try
        {
            list = dao.getMemberInfo ();
        }
        catch (DataAccessException ex)
        {
            throw new ServiceException ("資料取得失敗-->" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
        return list;
    }

    @Override
    public <T> List<T> getAll (Class<?> clazz, String[] columnName, Object[] rowValue) throws ServiceException
    {
        Dao dao = new Dao ();
        List<T> list = null;
        try
        {
            list = dao.getAll (clazz, columnName, rowValue);
        }
        catch (DataAccessException ex)
        {
            throw new ServiceException ("資料取得失敗-->" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
        return list;
    }

    @Override
    public <T> List<T> getAllByDate (Class<?> clazz, String columnName, String startDate, String endDate) throws
            ServiceException
    {

        Dao dao = new Dao ();
        List<T> list = null;
        try
        {
            list = dao.getAllByDate (clazz, columnName, startDate, endDate);
        }
        catch (DataAccessException ex)
        {
            throw new ServiceException ("資料取得失敗-->" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
        return list;
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
    @Override
    public <T> boolean update (T t, String[] column, Object[] value, String columnName, String rowValue)
            throws DataAccessException
    {
        Dao dao = new Dao ();
        try
        {
            dao.update (t, column, value, columnName, rowValue);
        }
        catch (DataAccessException ex)
        {
            throw new DataAccessException ("資料更新失敗-->" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
        return false;
    }

    @Override
    public <T> void update (T t, Map<String, String[]> map, String column, String columnValue, String flag) throws
            DataAccessException
    {
        // 取得 BasicDAO 對象 --> JDBC 操作 CRUD 方法
        Dao dao = new Dao ();
        Set<Map.Entry<String, String[]>> set = map.entrySet ();
        // 用來存不為空的ColumnName
        StringBuilder columnName = new StringBuilder ();
        StringBuilder rowValue = new StringBuilder ();
        // 迭代Map 數據
        for (Iterator<Map.Entry<String, String[]>> it = set.iterator (); it
                .hasNext (); )
        {
            Map.Entry<String, String[]> entry = it.next ();

            for (String value : entry.getValue ())
            {
                if (!value.equals ("") && !entry.getKey ().equals (column)
                        && !value.equals (flag))
                {
                    columnName.append (entry.getKey ().trim ()).append (",");
                    rowValue.append (value.trim ()).append (",");

                }
            }
        }
        if (columnName.length () != 0 && rowValue.length () != 0)
        {
            String[] columnNames = columnName.toString ().split (",");
            String[] rowValues = rowValue.toString ().split (",");

            try
            {
                dao.update (t, columnNames, rowValues, column, columnValue);

            }
            catch (Exception ex)
            {
                throw new DataAccessException ("資料更新失敗");
            }
            finally
            {
                dao.close ();
            }

        }
        else
        {
            throw new DataAccessException ("無更新資料，請確認");
        }

    }

    @Override
    public <T> void update (Class<T> clazz, List<Map<String, String[]>> list) throws DataAccessException
    {
        Dao dao = new Dao ();
        try
        {
            for (Map<String, String[]> map : list)
            {
                T t = clazz.newInstance ();
                Set<Map.Entry<String, String[]>> set = map.entrySet ();
                // 用來存不為空的ColumnName
                StringBuilder columnName = new StringBuilder ();
                StringBuilder rowValue = new StringBuilder ();
                int id = 0;
                // 迭代Map 數據
                for (Iterator<Map.Entry<String, String[]>> it = set.iterator (); it.hasNext (); )
                {
                    Map.Entry<String, String[]> entry = it.next ();
                    for (String value : entry.getValue ())
                    {
                        if (!"".equals (value))
                        {
                            if(entry.getKey ().equals ("id"))
                            {
                                id = Integer.parseInt (value);
                            }
                            else
                            {
                                columnName.append (entry.getKey ().trim ()).append (",");
                                rowValue.append (value.trim ()).append (",");
                            }
                        }
                    }
                }

                String[] columnNames = columnName.toString ().split (",");
                String[] rowValues = rowValue.toString ().split (",");

                dao.update (t, columnNames, rowValues, "_id", id);
            }
            dao.commit ();
        }
        catch (Exception ex)
        {
            dao.roolback ();
            throw new DataAccessException ("資料更新失敗");
        }
        finally
        {
            dao.setAutoCommit (true);
            dao.close ();
        }

    }


    @Override
    public void updateMemberInfo (Map<String, String[]> map, String column, String columnValue, String flag) throws
            DataAccessException
    {
        // 取得 BasicDAO 對象 --> JDBC 操作 CRUD 方法
        Dao dao = new Dao ();
//        Set<Map.Entry<String, String[]>> set = map.entrySet ();
        // 用來存不為空的ColumnName
        StringBuilder memberColumn = new StringBuilder ();
        StringBuilder memberValue = new StringBuilder ();
        StringBuilder extColumn = new StringBuilder ();
        StringBuilder extValue = new StringBuilder ();
        String upperPos = null;
        int currentUpperId = 0;
        String currentPosition = null;
        int upperId = 0;

        // 迭代Map 數據
        for (Map.Entry<String, String[]> entry : map.entrySet ())
        {
            for (String value : entry.getValue ())
            {
                String key = entry.getKey ();
                if (!value.equals ("") && !key.equals (column) && !value.equals (flag))
                {
                    if (key.equals ("recommend") || key.equals ("currentUpper") || key.equals ("upper")
                            || key.equals ("position") || key.equals ("currentPosition") || key.equals ("level"))
                    {
                        Member member;
                        switch (key)
                        {
                            case "level":
                                extColumn.append (key.trim ()).append (",");
                                extValue.append (value).append (",");
                                break;
                            case "recommend":
                                member = dao.search (new Member (), new String[]{"member_no"}, new Object[]{value});

                                if (member != null)
                                {
                                    int recommendId = member.get_id ();
                                    extColumn.append (key.trim ()).append (",");
                                    extValue.append (recommendId).append (",");
                                }

                            case "upper":
                                member = dao.search (new Member (), new String[]{"member_no"}, new Object[]{value});
                                if (member != null)
                                {
                                    upperId = member.get_id ();
                                    extColumn.append (key.trim ()).append (",");
                                    extValue.append (upperId).append (",");
                                }
                                break;
                            case "position":
                                upperPos = value;
                                break;
                            case "currentPosition":
                                currentPosition = (value.equals ("右線")) ? "right_lower" : "left_lower";
                                break;
                            case "currentUpper":
                                member = dao.search (new Member (), new String[]{"member_no"}, new Object[]{value});
                                if (member != null)
                                {
                                    currentUpperId = member.get_id ();
                                }
                                break;
                        }
                    }
                    else
                    {
                        if (key.equals ("member_pwd"))
                        {
                            value = MD5Encrypt.encode (value);
                        }
                        memberColumn.append (key.trim ()).append (",");
                        memberValue.append (value).append (",");
                    }

                }
            }
        }
        try
        {

            if (memberColumn.length () != 0 && memberValue.length () != 0)
            {
                String[] memberColumns = memberColumn.toString ().split (",");
                String[] memberValues = memberValue.toString ().split (",");
                dao.update (new Member (), memberColumns, memberValues, column, columnValue);
            }
            dao.setAutoCommit (false);
            if (extColumn.length () != 0 && extValue.length () != 0)
            {
                String[] extColumns = extColumn.toString ().split (",");
                String[] extValues = extValue.toString ().split (",");
                Member member = dao.search (new Member (), new String[]{column}, new Object[]{columnValue});
                int id = member.get_id ();
                dao.update (new Member_Ext (), extColumns, extValues, "member", id);
                System.out.println (upperPos + " : " + currentUpperId);
                if (upperId != 0)
                {
                    if (currentUpperId != 0 && upperPos != null)
                    {
                        dao.update (new Member_Ext (), new String[]{currentPosition}, new Object[]{null},
                                "member", currentUpperId);
                        dao.update (new Member_Ext (), new String[]{upperPos}, new Object[]{member.get_id ()},
                                "member", upperId);

                        dao.commit ();
                    }
                    else
                    {
                        dao.roolback ();
                        throw new DataAccessException ("資料更新失敗");
                    }
                }
            }

        }
        catch (Exception ex)
        {
            dao.roolback ();
            throw new DataAccessException ("資料更新失敗");
        }
        finally
        {
            dao.setAutoCommit (true);
            dao.close ();
        }


    }


    @Override
    public List<Map<String, Object>> getMember (Class<?> clazz1, Class<?> clazz2, String value1, String value2,
                                                String column, String columnValue)
            throws ServiceException
    {
        Dao dao = new Dao ();
        List<Map<String, Object>> list = null;
        try
        {
            list = dao.getMember (clazz1, clazz2, value1, value2, column, columnValue);
        }
        catch (DataAccessException ex)
        {
            throw new ServiceException ("資料取得失敗-->" + ex.getMessage ());
        }
        finally
        {
            dao.close ();
        }
        return list;
    }
}
