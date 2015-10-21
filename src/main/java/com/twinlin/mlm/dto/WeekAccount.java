package com.twinlin.mlm.dto;

/**
 * Created by zero on 15/7/17.
 */
public class WeekAccount
{
    private int _id;
    private String year;
    private String week;
    private String date_cycle;
    private String member_no;
    private String member_name;
    private String left_pv;
    private String right_pv;
    private String bump_pv;
    private String last_pv;
    private String recommend_bonus;
    private String org_bonus;
    private String equal_bonus;
    private String upgrade_bonus;
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

    public String getYear ()
    {
        return year;
    }

    public void setYear (String year)
    {
        this.year = year;
    }

    public String getWeek ()
    {
        return week;
    }

    public void setWeek (String week)
    {
        this.week = week;
    }

    public String getDate_cycle ()
    {
        return date_cycle;
    }

    public void setDate_cycle (String date_cycle)
    {
        this.date_cycle = date_cycle;
    }

    public String getMember_no ()
    {
        return member_no;
    }

    public void setMember_no (String member_no)
    {
        this.member_no = member_no;
    }

    public String getMember_name ()
    {
        return member_name;
    }

    public void setMember_name (String member_name)
    {
        this.member_name = member_name;
    }

    public String getLeft_pv ()
    {
        return left_pv;
    }

    public void setLeft_pv (String left_pv)
    {
        this.left_pv = left_pv;
    }

    public String getRight_pv ()
    {
        return right_pv;
    }

    public void setRight_pv (String right_pv)
    {
        this.right_pv = right_pv;
    }


    public String getBump_pv ()
    {
        return bump_pv;
    }

    public void setBump_pv (String bump_pv)
    {
        this.bump_pv = bump_pv;
    }

    public String getLast_pv ()
    {
        return last_pv;
    }

    public void setLast_pv (String last_pv)
    {
        this.last_pv = last_pv;
    }

    public String getRecommend_bonus ()
    {
        return recommend_bonus;
    }

    public void setRecommend_bonus (String recommend_bonus)
    {
        this.recommend_bonus = recommend_bonus;
    }

    public String getOrg_bonus ()
    {
        return org_bonus;
    }

    public void setOrg_bonus (String org_bonus)
    {
        this.org_bonus = org_bonus;
    }

    public String getEqual_bonus ()
    {
        return equal_bonus;
    }

    public void setEqual_bonus (String equal_bonus)
    {
        this.equal_bonus = equal_bonus;
    }

    public String getUpgrade_bonus ()
    {
        return upgrade_bonus;
    }

    public void setUpgrade_bonus (String upgrade_bonus)
    {
        this.upgrade_bonus = upgrade_bonus;
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
        return "WeekAccount{" +
                "_id=" + _id +
                ", year='" + year + '\'' +
                ", week='" + week + '\'' +
                ", date_cycle='" + date_cycle + '\'' +
                ", member_no='" + member_no + '\'' +
                ", member_name='" + member_name + '\'' +
                ", left_pv='" + left_pv + '\'' +
                ", right_pv='" + right_pv + '\'' +
                ", bump_pv='" + bump_pv + '\'' +
                ", subtract_pv='" + last_pv + '\'' +
                ", recommend_bonus='" + recommend_bonus + '\'' +
                ", org_bonus='" + org_bonus + '\'' +
                ", equal_bonus='" + equal_bonus + '\'' +
                ", upgrade_bonus='" + upgrade_bonus + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}
