package com.example.dell.small_geeknews.V2EX.adapter.bean;

/**
 * Created by Dell on 2019/4/21.
 */

public class V2ExListBean {

     private String src;
     private String titles;
     private String info;
     private String secondTabs;
     private String autors;
     private String autortext;
     private String count;

    public V2ExListBean(String src, String titles, String info, String secondTabs, String autors, String autortext, String count) {
        this.src = src;
        this.titles = titles;
        this.info = info;
        this.secondTabs = secondTabs;
        this.autors = autors;
        this.autortext = autortext;
        this.count = count;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getSecondTabs() {
        return secondTabs;
    }

    public void setSecondTabs(String secondTabs) {
        this.secondTabs = secondTabs;
    }

    public String getAutors() {
        return autors;
    }

    public void setAutors(String autors) {
        this.autors = autors;
    }

    public String getAutortext() {
        return autortext;
    }

    public void setAutortext(String autortext) {
        this.autortext = autortext;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "V2ExListBean{" +
                "src='" + src + '\'' +
                ", titles='" + titles + '\'' +
                ", info='" + info + '\'' +
                ", secondTabs='" + secondTabs + '\'' +
                ", autors='" + autors + '\'' +
                ", autortext='" + autortext + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
