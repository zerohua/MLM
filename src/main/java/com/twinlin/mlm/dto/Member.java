package com.twinlin.mlm.dto;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by zero on 15/3/24.
 * 會員基本資料bean
 */
public class Member implements Serializable
{
    private int _id;
    private String member_no; // 會員編號
    private String member_pwd; // 會員密碼
    private String name;
    private String id;// 會員身份證號碼
    private String sex;
    private Object birthday;
    private String mobile;
    private String tel;
    private String email;
    private String bank_account;
    private String account_name;
    private String city;
    private String area;
    private String address;
    private String note;
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

    public String getMember_no ()
    {
        return member_no;
    }

    public void setMember_no (String member_no)
    {
        this.member_no = member_no;
    }

    public String getMember_pwd ()
    {
        return member_pwd;
    }

    public void setMember_pwd (String member_pwd)
    {
        this.member_pwd = member_pwd;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getSex ()
    {
        return sex;
    }

    public void setSex (String sex)
    {
        this.sex = sex;
    }

    public Date getBirthday ()
    {
        return (Date)birthday;
    }

    public void setBirthday (String birthday)
    {
        this.birthday = birthday;
    }

    public String getMobile ()
    {
        return mobile;
    }

    public void setMobile (String mobile)
    {
        this.mobile = mobile;
    }


    public String getTel ()
    {
        return tel;
    }

    public void setTel (String tel)
    {
        this.tel = tel;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getBank_account ()
    {
        return bank_account;
    }

    public void setBank_account (String bank_account)
    {
        this.bank_account = bank_account;
    }

    public String getAccount_name ()
    {
        return account_name;
    }

    public void setAccount_name (String account_name)
    {
        this.account_name = account_name;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getArea ()
    {
        return area;
    }

    public void setArea (String area)
    {
        this.area = area;
    }

    public String getAddress ()
    {
        return address;
    }

    public void setAddress (String address)
    {
        this.address = address;
    }

    public String getNote ()
    {
        return note;
    }

    public void setNote (String note)
    {
        this.note = note;
    }

    public Object getCreate_date ()
    {
        return  create_date;
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
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass () != o.getClass ()) return false;

        Member member = (Member) o;

        if (_id != member._id) return false;
        if (member_no != null ? !member_no.equals (member.member_no) : member.member_no != null) return false;
        if (member_pwd != null ? !member_pwd.equals (member.member_pwd) : member.member_pwd != null) return false;
        if (name != null ? !name.equals (member.name) : member.name != null) return false;
        if (id != null ? !id.equals (member.id) : member.id != null) return false;
        if (sex != null ? !sex.equals (member.sex) : member.sex != null) return false;
        if (birthday != null ? !birthday.equals (member.birthday) : member.birthday != null) return false;
        if (mobile != null ? !mobile.equals (member.mobile) : member.mobile != null) return false;
        if (tel != null ? !tel.equals (member.tel) : member.tel != null) return false;
        if (email != null ? !email.equals (member.email) : member.email != null) return false;
        if (bank_account != null ? !bank_account.equals (member.bank_account) : member.bank_account != null)
            return false;
        if (account_name != null ? !account_name.equals (member.account_name) : member.account_name != null)
            return false;
        if (city != null ? !city.equals (member.city) : member.city != null) return false;
        if (area != null ? !area.equals (member.area) : member.area != null) return false;
        if (address != null ? !address.equals (member.address) : member.address != null) return false;
        if (note != null ? !note.equals (member.note) : member.note != null) return false;
        if (create_date != null ? !create_date.equals (member.create_date) : member.create_date != null) return false;
        if (update_date != null ? !update_date.equals (member.update_date) : member.update_date != null) return false;
        if (create_name != null ? !create_name.equals (member.create_name) : member.create_name != null) return false;
        return !(update_name != null ? !update_name.equals (member.update_name) : member.update_name != null);

    }

    @Override
    public int hashCode ()
    {
        int result = _id;
        result = 31 * result + (member_no != null ? member_no.hashCode () : 0);
        result = 31 * result + (member_pwd != null ? member_pwd.hashCode () : 0);
        result = 31 * result + (name != null ? name.hashCode () : 0);
        result = 31 * result + (id != null ? id.hashCode () : 0);
        result = 31 * result + (sex != null ? sex.hashCode () : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode () : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode () : 0);
        result = 31 * result + (tel != null ? tel.hashCode () : 0);
        result = 31 * result + (email != null ? email.hashCode () : 0);
        result = 31 * result + (bank_account != null ? bank_account.hashCode () : 0);
        result = 31 * result + (account_name != null ? account_name.hashCode () : 0);
        result = 31 * result + (city != null ? city.hashCode () : 0);
        result = 31 * result + (area != null ? area.hashCode () : 0);
        result = 31 * result + (address != null ? address.hashCode () : 0);
        result = 31 * result + (note != null ? note.hashCode () : 0);
        result = 31 * result + (create_date != null ? create_date.hashCode () : 0);
        result = 31 * result + (update_date != null ? update_date.hashCode () : 0);
        result = 31 * result + (create_name != null ? create_name.hashCode () : 0);
        result = 31 * result + (update_name != null ? update_name.hashCode () : 0);
        return result;
    }
}
