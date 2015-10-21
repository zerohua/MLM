package com.twinlin.mlm.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by zero on 15/3/24.
 * 會員階級bean
 */
public class Member_Level implements Serializable
{
    private int _id;
    private String mem_level;
    private Object create_date;
    private Object update_date;
    private String create_name; // 建檔人
    private String update_name; //修改人

    public int get_id ()
    {
        return _id;
    }

    public void set_id (int _id)
    {
        this._id = _id;
    }

    public String getMem_level ()
    {
        return mem_level;
    }

    public void setMem_level (String mem_level)
    {
        this.mem_level = mem_level;
    }

    public Date getCreate_date ()
    {
        return (Date) create_date;
    }

    public void setCreate_date (String create_date)
    {
        this.create_date = create_date;
    }

    public Date getUpdate_date ()
    {
        return (Date) update_date;
    }

    public void setUpdate_date (String update_date)
    {
        this.update_date = update_date;
    }

    public String getCreate_name ()
    {
        return create_name;
    }

    public void setCreate_name (String create_name)
    {
        this.create_name = create_name;
    }

    public String getUpdate_name ()
    {
        return update_name;
    }

    public void setUpdate_name (String update_name)
    {
        this.update_name = update_name;
    }

    @Override
    public String toString ()
    {
        return "Member_Level{" +
                "_id=" + _id +
                ", mem_level='" + mem_level + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                ", create_name='" + create_name + '\'' +
                ", update_name='" + update_name + '\'' +
                '}';
    }
}
