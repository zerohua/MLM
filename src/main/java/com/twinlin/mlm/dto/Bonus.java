package com.twinlin.mlm.dto;

import java.sql.Date;

/**
 * Created by zero on 15/5/7.
 */
public class Bonus
{
    private int _id;
    private String recommend_bonus;
    private String bump_bonus;
    private String equal_bonus;
    private String director_pv;
    private String director_bonus;
    private String up_director_bonus;
    private String cdo_pv;
    private String cdo_bonus;
    private String up_cdo_bonus;
    private String minister_pv;
    private String minister_bonus;
    private String up_minister_bonus;
    private String coo_pv;
    private String coo_bonus;
    private String up_coo_bonus;
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

    public String getRecommend_bonus ()
    {
        return recommend_bonus;
    }

    public void setRecommend_bonus (String recommend_bonus)
    {
        this.recommend_bonus = recommend_bonus;
    }

    public String getBump_bonus ()
    {
        return bump_bonus;
    }

    public void setBump_bonus (String bump_bonus)
    {
        this.bump_bonus = bump_bonus;
    }

    public String getEqual_bonus ()
    {
        return equal_bonus;
    }

    public void setEqual_bonus (String equal_bonus)
    {
        this.equal_bonus = equal_bonus;
    }

    public String getDirector_pv ()
    {
        return director_pv;
    }

    public void setDirector_pv (String director_pv)
    {
        this.director_pv = director_pv;
    }

    public String getDirector_bonus ()
    {
        return director_bonus;
    }

    public void setDirector_bonus (String director_bonus)
    {
        this.director_bonus = director_bonus;
    }

    public String getUp_director_bonus ()
    {
        return up_director_bonus;
    }

    public void setUp_director_bonus (String up_director_bonus)
    {
        this.up_director_bonus = up_director_bonus;
    }

    public String getCdo_pv ()
    {
        return cdo_pv;
    }

    public void setCdo_pv (String cdo_pv)
    {
        this.cdo_pv = cdo_pv;
    }

    public String getCdo_bonus ()
    {
        return cdo_bonus;
    }

    public void setCdo_bonus (String cdo_bonus)
    {
        this.cdo_bonus = cdo_bonus;
    }

    public String getUp_cdo_bonus ()
    {
        return up_cdo_bonus;
    }

    public void setUp_cdo_bonus (String up_cdo_bonus)
    {
        this.up_cdo_bonus = up_cdo_bonus;
    }

    public String getMinister_pv ()
    {
        return minister_pv;
    }

    public void setMinister_pv (String minister_pv)
    {
        this.minister_pv = minister_pv;
    }

    public String getMinister_bonus ()
    {
        return minister_bonus;
    }

    public void setMinister_bonus (String minister_bonus)
    {
        this.minister_bonus = minister_bonus;
    }

    public String getUp_minister_bonus ()
    {
        return up_minister_bonus;
    }

    public void setUp_minister_bonus (String up_minister_bonus)
    {
        this.up_minister_bonus = up_minister_bonus;
    }

    public String getCoo_pv ()
    {
        return coo_pv;
    }

    public void setCoo_pv (String coo_pv)
    {
        this.coo_pv = coo_pv;
    }

    public String getCoo_bonus ()
    {
        return coo_bonus;
    }

    public void setCoo_bonus (String coo_bonus)
    {
        this.coo_bonus = coo_bonus;
    }

    public String getUp_coo_bonus ()
    {
        return up_coo_bonus;
    }

    public void setUp_coo_bonus (String up_coo_bonus)
    {
        this.up_coo_bonus = up_coo_bonus;
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
        return "Bonus{" +
                "_id=" + _id +
                ", recommend_bonus=" + recommend_bonus +
                ", bump_bonus=" + bump_bonus +
                ", equal_bonus=" + equal_bonus +
                ", director_pv=" + director_pv +
                ", director_bonus=" + director_bonus +
                ", up_director_bonus=" + up_director_bonus +
                ", cdo_pv=" + cdo_pv +
                ", cdo_bonus=" + cdo_bonus +
                ", up_cdo_bonus=" + up_cdo_bonus +
                ", minister_pv=" + minister_pv +
                ", minister_bonus=" + minister_bonus +
                ", up_minister_bonus=" + up_minister_bonus +
                ", coo_pv=" + coo_pv +
                ", coo_bonus=" + coo_bonus +
                ", up_coo_bonus=" + up_coo_bonus +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}
