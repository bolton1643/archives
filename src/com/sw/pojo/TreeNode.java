package com.sw.pojo;

public class TreeNode {

	private Integer id;
	private String text;
	private Integer parentId;
	private String expanded;
	private String openurl;
	private String isfolder;

	public String getIsfolder() {
		return isfolder;
	}

	public void setIsfolder(String isfolder) {
		this.isfolder = isfolder;
	}

	public String getOpenurl() {
		return openurl;
	}

	public void setOpenurl(String openurl) {
		this.openurl = openurl;
	}

	public TreeNode() {

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
		return parentId;
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

}
