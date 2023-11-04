package com.example.LqcSpringBoot.model;

import java.io.Serializable;

public class Rctable implements Serializable {
    private String id;

    private String rcdate;

    private String pnumber;

    private String color;

    private String type;

    private String rccount;

    private String costprice;

    private String costcount;

    private String bz;

    private String sshg;
    private String gys;
    private String pname;
    private String qcs;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRcdate() {
        return rcdate;
    }

    public void setRcdate(String rcdate) {
        this.rcdate = rcdate == null ? null : rcdate.trim();
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber == null ? null : pnumber.trim();
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getRccount() {
        return rccount;
    }

    public void setRccount(String rccount) {
        this.rccount = rccount == null ? null : rccount.trim();
    }

    public String getCostprice() {
        return costprice;
    }

    public void setCostprice(String costprice) {
        this.costprice = costprice == null ? null : costprice.trim();
    }

    public String getCostcount() {
        return costcount;
    }

    public void setCostcount(String costcount) {
        this.costcount = costcount == null ? null : costcount.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    public String getSshg() {
        return sshg;
    }

    public void setSshg(String sshg) {
        this.sshg = sshg == null ? null : sshg.trim();
    }

    public String getGys() {
        return gys;
    }

    public void setGys(String gys) {
        this.gys = gys;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getQcs() {
        return qcs;
    }

    public void setQcs(String qcs) {
        this.qcs = qcs;
    }
}