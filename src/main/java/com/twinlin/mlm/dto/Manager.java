package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/4/25.
 */
public class Manager
{
    private int _id;
    private String manager_name;
    private String manager_pwd;
    private String nick_name;
    private String level;
    private String create_name;
    private Object create_date;
    private String update_name;
    private Object update_date;

    public int get_id ()
    {
        return _id;
    }

    public void set_id (int _id)
    {
        this._id = _id;
    }

    public String getManager_name ()
    {
        return manager_name;
    }

    public void setManager_name (String manager_name)
    {
        this.manager_name = manager_name;
    }

    public String getManager_pwd ()
    {
        return manager_pwd;
    }

    public void setManager_pwd (String manager_pwd)
    {
        this.manager_pwd = manager_pwd;
    }

    public String getNick_name ()
    {
        return nick_name;
    }

    public void setNick_name (String nick_name)
    {
        this.nick_name = nick_name;
    }

    public String getLevel ()
    {
        return level;
    }

    public void setLevel (String level)
    {
        this.level = level;
    }

    public String getCreate_name ()
    {
        return create_name;
    }

    public void setCreate_name (String create_name)
    {
        this.create_name = create_name;
    }

    public Date getCreate_date ()
    {
        return (Date) create_date;
    }

    public void setCreate_date (String create_date)
    {
        this.create_date = create_date;
    }

    public String getUpdate_name ()
    {
        return update_name;
    }

    public void setUpdate_name (String update_name)
    {
        this.update_name = update_name;
    }

    public Date getUpdate_date ()
    {
        return (Date) update_date;
    }

    public void setUpdate_date (String update_date)
    {
        this.update_date = update_date;
    }

    @Override
    public String toString ()
    {
        return "Manager{" +
                "_id=" + _id +
                ", manager_name='" + manager_name + '\'' +
                ", manager_pwd='" + manager_pwd + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}
