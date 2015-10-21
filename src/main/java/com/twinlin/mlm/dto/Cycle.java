package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/4/24.
 */
public class Cycle
{
    private int _id;
    private String work_year;
    private String weekofmonth;
    private String closedayofweek;
    private String first_month;
    private String second_month;
    private String third_month;
    private String fourth_month;
    private String fifth_month;
    private String sixth_month;
    private String seventh_month;
    private String eighth_month;
    private String nineth_month;
    private String tenth_month;
    private String eleventh_month;
    private String twelfth_month;
    private String thirteenth_month;
    private String create_name;
    private Object create_date;

    public int get_id ()
    {
        return _id;
    }

    public void set_id (int _id)
    {
        this._id = _id;
    }

    public String getWork_year ()
    {
        return work_year;
    }

    public void setWork_year (String work_year)
    {
        this.work_year = work_year;
    }

    public String getWeekofmonth ()
    {
        return weekofmonth;
    }

    public void setWeekofmonth (String weekofmonth)
    {
        this.weekofmonth = weekofmonth;
    }

    public String getClosedayofweek ()
    {
        return closedayofweek;
    }

    public void setClosedayofweek (String closedayofweek)
    {
        this.closedayofweek = closedayofweek;
    }

    public String getFirst_month ()
    {
        return first_month;
    }

    public void setFirst_month (String first_month)
    {
        this.first_month = first_month;
    }

    public String getSecond_month ()
    {
        return second_month;
    }

    public void setSecond_month (String second_month)
    {
        this.second_month = second_month;
    }

    public String getThird_month ()
    {
        return third_month;
    }

    public void setThird_month (String third_month)
    {
        this.third_month = third_month;
    }

    public String getFourth_month ()
    {
        return fourth_month;
    }

    public void setFourth_month (String fourth_month)
    {
        this.fourth_month = fourth_month;
    }

    public String getFifth_month ()
    {
        return fifth_month;
    }

    public void setFifth_month (String fifth_month)
    {
        this.fifth_month = fifth_month;
    }

    public String getSixth_month ()
    {
        return sixth_month;
    }

    public void setSixth_month (String sixth_month)
    {
        this.sixth_month = sixth_month;
    }

    public String getSeventh_month ()
    {
        return seventh_month;
    }

    public void setSeventh_month (String seventh_month)
    {
        this.seventh_month = seventh_month;
    }

    public String getEighth_month ()
    {
        return eighth_month;
    }

    public void setEighth_month (String eighth_month)
    {
        this.eighth_month = eighth_month;
    }

    public String getNineth_month ()
    {
        return nineth_month;
    }

    public void setNineth_month (String nineth_month)
    {
        this.nineth_month = nineth_month;
    }

    public String getTenth_month ()
    {
        return tenth_month;
    }

    public void setTenth_month (String tenth_month)
    {
        this.tenth_month = tenth_month;
    }

    public String getEleventh_month ()
    {
        return eleventh_month;
    }

    public void setEleventh_month (String eleventh_month)
    {
        this.eleventh_month = eleventh_month;
    }

    public String getTwelfth_month ()
    {
        return twelfth_month;
    }

    public void setTwelfth_month (String twelfth_month)
    {
        this.twelfth_month = twelfth_month;
    }

    public String getThirteenth_month ()
    {
        return thirteenth_month;
    }

    public void setThirteenth_month (String thirteenth_month)
    {
        this.thirteenth_month = thirteenth_month;
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

    @Override
    public String toString ()
    {
        return "Cycle{" +
                "_id=" + _id +
                ", work_year='" + work_year + '\'' +
                ", weekofmonth='" + weekofmonth + '\'' +
                ", closedayofweek='" + closedayofweek + '\'' +
                ", first_month='" + first_month + '\'' +
                ", second_month='" + second_month + '\'' +
                ", third_month='" + third_month + '\'' +
                ", fourth_month='" + fourth_month + '\'' +
                ", fifth_month='" + fifth_month + '\'' +
                ", sixth_month='" + sixth_month + '\'' +
                ", seventh_month='" + seventh_month + '\'' +
                ", eighth_month='" + eighth_month + '\'' +
                ", nineth_month='" + nineth_month + '\'' +
                ", tenth_month='" + tenth_month + '\'' +
                ", eleventh_month='" + eleventh_month + '\'' +
                ", twelfth_month='" + twelfth_month + '\'' +
                ", thirteenth_month='" + thirteenth_month + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date='" + create_date + '\'' +
                '}';
    }
}
