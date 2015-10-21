package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/5/6.
 */
public class Amortization
{
    private int _id;
    private String produce;
    private String stage;
    private String stage_money;
    private String stage_pv;
    private String total_pv;
    private String remain_pv;
    private String complement;
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

    public String getProduce ()
    {
        return produce;
    }

    public void setProduce (String produce)
    {
        this.produce = produce;
    }

    public String getStage ()
    {
        return stage;
    }

    public void setStage (String stage)
    {
        this.stage = stage;
    }

    public String getStage_money ()
    {
        return stage_money;
    }

    public void setStage_money (String stage_money)
    {
        this.stage_money = stage_money;
    }

    public String getStage_pv ()
    {
        return stage_pv;
    }

    public void setStage_pv (String stage_pv)
    {
        this.stage_pv = stage_pv;
    }

    public String getTotal_pv ()
    {
        return total_pv;
    }

    public void setTotal_pv (String total_pv)
    {
        this.total_pv = total_pv;
    }

    public String getRemain_pv ()
    {
        return remain_pv;
    }

    public void setRemain_pv (String remain_pv)
    {
        this.remain_pv = remain_pv;
    }

    public String getComplement ()
    {
        return complement;
    }

    public void setComplement (String complement)
    {
        this.complement = complement;
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

    public void setCreate_data (String create_data)
    {
        this.create_date = create_data;
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
        return (Date)update_date;
    }

    public void setUpdate_date (String update_date)
    {
        this.update_date = update_date;
    }

    @Override
    public String toString ()
    {
        return "amortization{" +
                "_id=" + _id +
                ", produce='" + produce + '\'' +
                ", stage='" + stage + '\'' +
                ", stage_money='" + stage_money + '\'' +
                ", stage_pv='" + stage_pv + '\'' +
                ", total_pv='" + total_pv + '\'' +
                ", remain_pv='" + remain_pv + '\'' +
                ", complement='" + complement + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_data='" + create_date + '\'' +
                ", update_name='" + update_name + '\'' +
                ", update_date='" + update_date + '\'' +
                '}';
    }
}
