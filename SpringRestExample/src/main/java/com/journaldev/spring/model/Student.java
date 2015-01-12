package com.journaldev.spring.model;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
@XmlRootElement(name = "Student")
public class Student implements Serializable{

	private static final long serialVersionUID = -7788619177798333712L;
	
	private int id;
	private String name;
	private Date createdDate;
	
	
	public int getId() {
		return id;
	}
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	@XmlElement
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonSerialize(using=DateSerializer.class)
	public Date getCreatedDate() {
		return createdDate;
	}
	@XmlElement
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	
}
