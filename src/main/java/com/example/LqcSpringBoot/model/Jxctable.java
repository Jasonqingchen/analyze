package com.example.LqcSpringBoot.model;

import java.io.Serializable;

public class Jxctable implements Serializable {
    private String id;

    private String pnumber;

    private String pname;

    private String color;

    private String type;

    private String qcs;

    private String rccount;

    private String cccount;

    private String jccount;

    private String pdcount;

    private String cycount;

    private String bz;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber == null ? null : pnumber.trim();
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname == null ? null : pname.trim();
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

    public String getQcs() {
        return qcs;
    }

    public void setQcs(String qcs) {
        this.qcs = qcs == null ? null : qcs.trim();
    }

    public String getRccount() {
        return rccount;
    }

    public void setRccount(String rccount) {
        this.rccount = rccount == null ? null : rccount.trim();
    }

    public String getCccount() {
        return cccount;
    }

    public void setCccount(String cccount) {
        this.cccount = cccount == null ? null : cccount.trim();
    }

    public String getJccount() {
        return jccount;
    }

    public void setJccount(String jccount) {
        this.jccount = jccount == null ? null : jccount.trim();
    }

    public String getPdcount() {
        return pdcount;
    }

    public void setPdcount(String pdcount) {
        this.pdcount = pdcount == null ? null : pdcount.trim();
    }

    public String getCycount() {
        return cycount;
    }

    public void setCycount(String cycount) {
        this.cycount = cycount == null ? null : cycount.trim();
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }
}