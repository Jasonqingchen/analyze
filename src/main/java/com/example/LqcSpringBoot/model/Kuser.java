package com.example.LqcSpringBoot.model;

import java.io.Serializable;
import java.util.Date;

public class Kuser implements Serializable {
    private String id;

    private String name;

    private String num;

    private String sex;

    private String cm;

    private String phone;

    private String dyname;

    private String status;

    private Date date;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCm() {
        return cm;
    }

    public void setCm(String cm) {
        this.cm = cm == null ? null : cm.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getDyname() {
        return dyname;
    }

    public void setDyname(String dyname) {
        this.dyname = dyname == null ? null : dyname.trim();
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

}