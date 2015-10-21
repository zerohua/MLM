package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/4/17.
 */
public class Produce
{
    private int _id;
    private String pname;
    private String pv;
    private String price;
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

    public String getPname ()
    {
        return pname;
    }

    public void setPname (String pname)
    {
        this.pname = pname;
    }

    public String getPv ()
    {
        return pv;
    }

    public void setPv (String pv)
    {
        this.pv = pv;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
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
        return "Produce{" +
                "_id=" + _id +
                ", pname='" + pname + '\'' +
                ", pv='" + pv + '\'' +
                ", price='" + price + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_date='" + update_date + '\'' +
                ", create_name='" + create_name + '\'' +
                ", update_name='" + update_name + '\'' +
                '}';
    }
}
