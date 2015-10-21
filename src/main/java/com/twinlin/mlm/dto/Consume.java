package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/5/4.
 */
public class Consume
{
    private int _id;
    private String mode;
    private String money;
    private String generation_pv;
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

    public String getMode ()
    {
        return mode;
    }

    public void setMode (String mode)
    {
        this.mode = mode;
    }

    public String getMoney ()
    {
        return money;
    }

    public void setMoney (String money)
    {
        this.money = money;
    }

    public String getGeneration_pv ()
    {
        return generation_pv;
    }

    public void setGeneration_pv (String generation_pv)
    {
        this.generation_pv = generation_pv;
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
        return "Consume{" +
                "_id=" + _id +
                ", mode='" + mode + '\'' +
                ", money='" + money + '\'' +
                ", generation_pv='" + generation_pv + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_name='" + update_name + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }
}
