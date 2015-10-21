package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/5/6.
 */
public class Slide
{
    private int _id;
    private String slide_cycle;
    private String cycle_start;
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

    public String getSlide_cycle ()
    {
        return slide_cycle;
    }

    public void setSlide_cycle (String slide_cycle)
    {
        this.slide_cycle = slide_cycle;
    }

    public String getCycle_start ()
    {
        return cycle_start;
    }

    public void setCycle_start (String cycle_start)
    {
        this.cycle_start = cycle_start;
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
        return "Slide{" +
                "_id=" + _id +
                ", slide_cycle='" + slide_cycle + '\'' +
                ", cycle_start='" + cycle_start + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date='" + create_date + '\'' +
                ", update_name='" + update_name + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }
}
