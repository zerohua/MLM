package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/5/4.
 */
public class PV
{
    private int _id;
    private String pv_value;
    private String pv_sum;
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

    public String getPv_value ()
    {
        return pv_value;
    }

    public void setPv_value (String pv_value)
    {
        this.pv_value = pv_value;
    }

    public String getPv_sum ()
    {
        return pv_sum;
    }

    public void setPv_sum (String pv_sum)
    {
        this.pv_sum = pv_sum;
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
        return "PV{" +
                "_id=" + _id +
                ", pv_value=" + pv_value +
                ", pv_sum=" + pv_sum +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}

