package com.twinlin.mlm.dto;

/**
 * Created by zero on 15/6/28.
 */
public class Bump_Limit
{
    private int _id;
    private String week_limit;
    private String month_limit;
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

    public String getWeek_limit ()
    {
        return week_limit;
    }

    public void setWeek_limit (String week_limit)
    {
        this.week_limit = week_limit;
    }

    public String getMonth_limit ()
    {
        return month_limit;
    }

    public void setMonth_limit (String month_limit)
    {
        this.month_limit = month_limit;
    }

    public String getCreate_name ()
    {
        return create_name;
    }

    public void setCreate_name (String create_name)
    {
        this.create_name = create_name;
    }

    public Object getCreate_date ()
    {
        return create_date;
    }

    public void setCreate_date (Object create_date)
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

    public Object getUpdate_date ()
    {
        return update_date;
    }

    public void setUpdate_date (Object update_date)
    {
        this.update_date = update_date;
    }

    @Override
    public String toString ()
    {
        return "BumpLimit{" +
                "_id=" + _id +
                ", week_limit='" + week_limit + '\'' +
                ", month_limit='" + month_limit + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}
