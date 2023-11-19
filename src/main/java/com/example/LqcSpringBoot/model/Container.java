package com.example.LqcSpringBoot.model;

import java.io.Serializable;
import java.util.Date;

public class Container implements Serializable {
    private String id;

    private String gysfs;

    private String gnumber;


    private String gsize;

    private String phone;

    private String bankname;

    private String cgdate;

    private String status;

    private String dgdate;

    private String banknumber;

    private String content;

    private String type;

    private String count;

    private String price;
    private String dls;

    private String pol;

    private String pod;

    private String chuanname;

    private String dlgs;

    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getGysfs() {
        return gysfs;
    }

    public void setGysfs(String gysfs) {
        this.gysfs = gysfs == null ? null : gysfs.trim();
    }

    public String getGnumber() {
        return gnumber;
    }

    public void setGnumber(String gnumber) {
        this.gnumber = gnumber == null ? null : gnumber.trim();
    }


    public String getGsize() {
        return gsize;
    }

    public void setGsize(String gsize) {
        this.gsize = gsize == null ? null : gsize.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname == null ? null : bankname.trim();
    }

    public String getCgdate() {
        return cgdate;
    }

    public void setCgdate(String cgdate) {
        this.cgdate = cgdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getDgdate() {
        return dgdate;
    }

    public void setDgdate(String dgdate) {
        this.dgdate = dgdate;
    }

    public String getBanknumber() {
        return banknumber;
    }

    public void setBanknumber(String banknumber) {
        this.banknumber = banknumber == null ? null : banknumber.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count == null ? null : count.trim();
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price == null ? null : price.trim();
    }

    public String getDls() {
        return dls;
    }

    public void setDls(String dls) {
        this.dls = dls;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public String getChuanname() {
        return chuanname;
    }

    public void setChuanname(String chuanname) {
        this.chuanname = chuanname;
    }

    public String getDlgs() {
        return dlgs;
    }

    public void setDlgs(String dlgs) {
        this.dlgs = dlgs;
    }
}