package com.sw.pojo;

public class TreeNodeRight {

    private Integer id;
    private String text;
    private Integer parentId;
    private String expanded;
    private String openurl;
    private String isfolder;

    private Integer userid;
    private Integer menuid;

    private String radd;
    private String rdelete;
    private String rmodify;
    private String rdownload;
    private String rprint;

    
    public TreeNodeRight() {
        this.userid = -1;
        this.menuid = -1;
        this.radd = "0";
        this.rdelete = "0";
        this.rmodify = "0";
        this.rdownload = "0";
        this.rprint = "0";
    }

    public String getRprint() {
        return rprint != null ? rprint : "0";
    }

    public void setRprint(String rprint) {
        this.rprint = rprint;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getParentId() {
        return parentId != null ? parentId : new Integer(0);
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getExpanded() {
        return expanded;
    }

    public void setExpanded(String expanded) {
        this.expanded = expanded;
    }

    public String getOpenurl() {
        return openurl;
    }

    public void setOpenurl(String openurl) {
        this.openurl = openurl;
    }

    public String getIsfolder() {
        return isfolder;
    }

    public void setIsfolder(String isfolder) {
        this.isfolder = isfolder;
    }

    public String getRadd() {
        return radd != null ? radd : "0";
    }

    public void setRadd(String radd) {
        this.radd = radd;
    }

    public String getRdelete() {
        return rdelete != null ? rdelete : "0";
    }

    public void setRdelete(String rdelete) {
        this.rdelete = rdelete;
    }

    public String getRmodify() {
        return rmodify != null ? rmodify : "0";
    }

    public void setRmodify(String rmodify) {
        this.rmodify = rmodify;
    }

    public String getRdownload() {
        return rdownload != null ? rdownload : "0";
    }

    public void setRdownload(String rdownload) {
        this.rdownload = rdownload;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getMenuid() {
        return menuid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

}
