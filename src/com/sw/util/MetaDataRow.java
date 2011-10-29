package com.sw.util;

public class MetaDataRow {
	private String dName;
	private String dType;
	private Integer dLength;
	private String dAllowNull;
	private String dNotes;//备注
	private String dValue;//用来存放编辑时的数据

	public String getDValue() {
		return dValue;
	}

	public void setDValue(String value) {
		dValue = value;
	}

	public String getDAllowNull() {
		return dAllowNull;
	}

	public void setDAllowNull(String allowNull) {
		dAllowNull = allowNull;
	}

	public String getDName() {
		return dName;
	}

	public void setDName(String name) {
		dName = name;
	}

	public String getDType() {
		return dType;
	}

	public void setDType(String type) {
		dType = type;
	}

	public Integer getDLength() {
		return dLength;
	}

	public void setDLength(Integer length) {
		dLength = length;
	}

	public String getDNotes() {
		return dNotes;
	}

	public void setDNotes(String notes) {
		dNotes = notes;
	}

}
