package com.example.LqcSpringBoot.model;

import java.awt.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class Sk implements Serializable {
    private String id;

    private String userid;

    private String name;

    private String cpname;

    private Date dktime;

    private Date usetime;

    private String sex;

    private Date jcj;

    private String status;

    private String num;

    private String zb;

    public Map map;

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCpname() {
        return cpname;
    }

    public void setCpname(String cpname) {
        this.cpname = cpname == null ? null : cpname.trim();
    }

    public Date getDktime() {
        return dktime;
    }

    public void setDktime(Date dktime) {
        this.dktime = dktime;
    }

    public Date getUsetime() {
        return usetime;
    }

    public void setUsetime(Date usetime) {
        this.usetime = usetime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getJcj() {
        return jcj;
    }

    public void setJcj(Date jcj) {
        this.jcj = jcj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num == null ? null : num.trim();
    }

    public String getZb() {
        return zb;
    }

    public void setZb(String zb) {
        this.zb = zb == null ? null : zb.trim();
    }
}