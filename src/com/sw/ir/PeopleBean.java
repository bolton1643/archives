package com.sw.ir;

public class PeopleBean
{
	private String city = null;
	private Integer id = null;
	private String name = null;
	private String street = null;

	public PeopleBean(
		String pcity,
		Integer pid,
		String pname,
		String pstreet
		)
	{
		city = pcity;
		id = pid;
		name = pname;
		street = pstreet;
	}

	public PeopleBean getMe()
	{
		return this;
	}

	public String getCity()
	{
		return city;
	}

	public Integer getId()
	{
		return id;
	}

	public String getName()
	{
		return name;
	}

	public String getStreet()
	{
		return street;
	}
}
