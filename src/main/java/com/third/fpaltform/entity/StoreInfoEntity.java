package com.third.fpaltform.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STORE_INFO")
public class StoreInfoEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4861636446814454118L;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 自增长的编号
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "NATION")
	private String nation;
	
	@Column(name = "UNIT")
	private String unit;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "ADDR")
	private String addr;
	
	@Column(name = "CONTACT")
	private String contact;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE")
	private String phone;
	
	@Column(name = "MOBILE")
	private String mobile;
	
	@Column(name = "SIZE")
	private Integer size;
	
	@Column(name = "division_Uid")
	private Integer divisionUid;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Integer getDivisionUid() {
		return divisionUid;
	}

	public void setDivisionUid(Integer divisionUid) {
		this.divisionUid = divisionUid;
	}
}
