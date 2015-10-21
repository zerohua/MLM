package com.twinlin.mlm.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by zero on 15/3/24.
 * 會員額外設定bean
 */
public class Member_Ext implements Serializable
{
    private int _id;
    private String status;
    private int member; // 會員 _id
    private String slide; // yes:滑動 no:不滑動
    private int recommend; // 推薦人 _id
    private int upper; // 上線 _id
    private int left_lower; // 左下線 _id
    private int right_lower; // 右下線 _id
    private int level; // 階級 _id
    private String last_pv;
    private Object up_director_date;
    private Object up_cdo_date;
    private Object up_minister_date;
    private Object up_coo_date;

    public int get_id ()
    {
        return _id;
    }

    public void set_id (int e_id)
    {
        this._id = e_id;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public int getMember ()
    {
        return member;
    }

    public void setMember (int member)
    {
        this.member = member;
    }

    public String getSlide ()
    {
        return slide;
    }

    public void setSlide (String slide)
    {
        this.slide = slide;
    }

    public int getRecommend ()
    {
        return recommend;
    }

    public void setRecommend (int recommend)
    {
        this.recommend = recommend;
    }

    public int getUpper ()
    {
        return upper;
    }

    public void setUpper (int upper)
    {
        this.upper = upper;
    }

    public int getLeft_lower ()
    {
        return left_lower;
    }

    public void setLeft_lower (int left_lower)
    {
        this.left_lower = left_lower;
    }

    public int getRight_lower ()
    {
        return right_lower;
    }

    public void setRight_lower (int right_lower)
    {
        this.right_lower = right_lower;
    }

    public int getLevel ()
    {
        return level;
    }

    public void setLevel (int level)
    {
        this.level = level;
    }

    public String getLast_pv ()
    {
        return last_pv;
    }

    public void setLast_pv (String last_pv)
    {
        this.last_pv = last_pv;
    }

    public Date getUp_director_date ()
    {
        return (Date) up_director_date;
    }

    public void setUp_director_date (String up_director_date)
    {
        this.up_director_date = up_director_date;
    }

    public Date getUp_cdo_date ()
    {
        return (Date) up_cdo_date;
    }

    public void setUp_cdo_date (String up_cdo_date)
    {
        this.up_cdo_date = up_cdo_date;
    }

    public Date getUp_minister_date ()
    {
        return (Date) up_minister_date;
    }

    public void setUp_minister_date (String up_minister_date)
    {
        this.up_minister_date = up_minister_date;
    }

    public Date getUp_coo_date ()
    {
        return (Date) up_coo_date;
    }

    public void setUp_coo_date (String up_coo_date)
    {
        this.up_coo_date = up_coo_date;
    }

    @Override
    public String toString ()
    {
        return "Member_Ext{" +
                "_id=" + _id +
                ", status='" + status + '\'' +
                ", member=" + member +
                ", slide='" + slide + '\'' +
                ", recommend=" + recommend +
                ", upper=" + upper +
                ", left_lower=" + left_lower +
                ", right_lower=" + right_lower +
                ", level=" + level +
                ", last_pv='" + last_pv + '\'' +
                ", up_director_date='" + up_director_date + '\'' +
                ", up_cdo_date='" + up_cdo_date + '\'' +
                ", up_minister_date='" + up_minister_date + '\'' +
                ", up_coo_date='" + up_coo_date + '\'' +
                '}';
    }
}
