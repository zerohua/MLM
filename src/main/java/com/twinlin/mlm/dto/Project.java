package com.twinlin.mlm.dto;


import java.sql.Date;

/**
 * Created by zero on 15/5/8.
 */
public class Project
{
    private int _id;
    private String project_no;
    private String member_no;
    private String member_name;
    private String pname;
    private String price;
    private String pv;
    private String pay;
    private String pay_method;
    private String installment;
    private String stage;
    private String stage_star;
    private String stage_end;
    private String last_pv;
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

    public String getProject_no ()
    {
        return project_no;
    }

    public void setProject_no (String project_no)
    {
        this.project_no = project_no;
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

    public String getPname ()
    {
        return pname;
    }

    public void setPname (String pname)
    {
        this.pname = pname;
    }

    public String getPrice ()
    {
        return price;
    }

    public void setPrice (String price)
    {
        this.price = price;
    }

    public String getPv ()
    {
        return pv;
    }

    public void setPv (String pv)
    {
        this.pv = pv;
    }

    public String getPay ()
    {
        return pay;
    }

    public void setPay (String pay)
    {
        this.pay = pay;
    }

    public String getPay_method ()
    {
        return pay_method;
    }

    public void setPay_method (String pay_method)
    {
        this.pay_method = pay_method;
    }

    public String getInstallment ()
    {
        return installment;
    }

    public void setInstallment (String installment)
    {
        this.installment = installment;
    }

    public String getStage ()
    {
        return stage;
    }

    public void setStage (String stage)
    {
        this.stage = stage;
    }

    public String getStage_star ()
    {
        return stage_star;
    }

    public void setStage_star (String stage_star)
    {
        this.stage_star = stage_star;
    }

    public String getStage_end ()
    {
        return stage_end;
    }

    public void setStage_end (String stage_end)
    {
        this.stage_end = stage_end;
    }

    public String getLast_pv ()
    {
        return last_pv;
    }

    public void setLast_pv (String last_pv)
    {
        this.last_pv = last_pv;
    }

    public void setCreate_date (Object create_date)
    {
        this.create_date = create_date;
    }

    public void setUpdate_date (Object update_date)
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
        return "Project{" +
                "_id=" + _id +
                ", project_no='" + project_no + '\'' +
                ", member_no='" + member_no + '\'' +
                ", member_name='" + member_name + '\'' +
                ", pname='" + pname + '\'' +
                ", price='" + price + '\'' +
                ", pv='" + pv + '\'' +
                ", pay='" + pay + '\'' +
                ", pay_method='" + pay_method + '\'' +
                ", installment='" + installment + '\'' +
                ", stage='" + stage + '\'' +
                ", stage_star='" + stage_star + '\'' +
                ", stage_end='" + stage_end + '\'' +
                ", last_pv='" + last_pv + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}
