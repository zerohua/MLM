package com.twinlin.mlm.dto;

/**
 * Created by zero on 15/6/30.
 */
public class Upgrade
{
    private int _id;
    private String director_slide_mini;
    private String director_slide_total;
    private String director_mini;
    private String director_total;
    private String cdo_rside_director;
    private String cdo_lside_director;
    private String minister_rside_cdo;
    private String minister_lside_cdo;
    private String coo_rside_minister;
    private String coo_lside_minister;
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

    public String getDirector_slide_mini ()
    {
        return director_slide_mini;
    }

    public void setDirector_slide_mini (String director_slide_mini)
    {
        this.director_slide_mini = director_slide_mini;
    }

    public String getDirector_slide_total ()
    {
        return director_slide_total;
    }

    public void setDirector_slide_total (String director_slide_total)
    {
        this.director_slide_total = director_slide_total;
    }

    public String getDirector_mimi ()
    {
        return director_mini;
    }

    public void setDirector_mimi (String director_mimi)
    {
        this.director_mini = director_mimi;
    }

    public String getDirector_total ()
    {
        return director_total;
    }

    public void setDirector_total (String director_total)
    {
        this.director_total = director_total;
    }

    public String getCdo_rside_director ()
    {
        return cdo_rside_director;
    }

    public void setCdo_rside_director (String cdo_rside_director)
    {
        this.cdo_rside_director = cdo_rside_director;
    }

    public String getCdo_lside_director ()
    {
        return cdo_lside_director;
    }

    public void setCdo_lside_director (String cdo_lside_director)
    {
        this.cdo_lside_director = cdo_lside_director;
    }

    public String getMinister_rside_cdo ()
    {
        return minister_rside_cdo;
    }

    public void setMinister_rside_cdo (String minister_rside_cdo)
    {
        this.minister_rside_cdo = minister_rside_cdo;
    }

    public String getMinister_lside_cdo ()
    {
        return minister_lside_cdo;
    }

    public void setMinister_lside_cdo (String minister_lside_cdo)
    {
        this.minister_lside_cdo = minister_lside_cdo;
    }

    public String getCoo_rside_minister ()
    {
        return coo_rside_minister;
    }

    public void setCoo_rside_minister (String coo_rside_minister)
    {
        this.coo_rside_minister = coo_rside_minister;
    }

    public String getCoo_lside_minister ()
    {
        return coo_lside_minister;
    }

    public void setCoo_lside_minister (String coo_lside_minister)
    {
        this.coo_lside_minister = coo_lside_minister;
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
        return "Upgrade{" +
                "_id=" + _id +
                ", director_slide_mini='" + director_slide_mini + '\'' +
                ", director_slide_total='" + director_slide_total + '\'' +
                ", director_mimi='" + director_mini + '\'' +
                ", director_total='" + director_total + '\'' +
                ", cdo_rside_director='" + cdo_rside_director + '\'' +
                ", cdo_lside_director='" + cdo_lside_director + '\'' +
                ", minister_rside_cdo='" + minister_rside_cdo + '\'' +
                ", minister_lside_cdo='" + minister_lside_cdo + '\'' +
                ", coo_rside_minister='" + coo_rside_minister + '\'' +
                ", coo_lside_minister='" + coo_lside_minister + '\'' +
                ", create_name='" + create_name + '\'' +
                ", create_date=" + create_date +
                ", update_name='" + update_name + '\'' +
                ", update_date=" + update_date +
                '}';
    }
}
