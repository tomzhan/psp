package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 部件表
 * @author Administrator
 *
 */
@Entity
@Table(name = "PART")
public class PartEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4445798642819222438L;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 自增长的编号
	
	@Column(name = "BAR_CODE")
	private String barCode;
	
	@Column(name = "TYPE")
	private int type;
	
	@Column(name = "MODELID")
	private int modelId;
	
	@Column(name = "HVER")
	private String hver;//硬件版本
	
	@Column(name = "SVER")
	private String sver;//软件版本
	
	@Column(name = "VOLTAGE")
	private String voltage;//额定工作电压
	
	@Column(name = "CURRENT")
	private String current;//工作电流
	
	@Column(name = "P1")
	private String p1;
	
	@Column(name = "P2")
	private String p2;
	
	@Column(name = "P3")
	private String p3;
	
	@Column(name = "P4")
	private String p4;
	
	@Column(name = "PRODUCT_DATE")
	private Date productDate;//生产日期
	
	@Column(name = "OUT_DATE")
	private Date outDate;//出厂日期
	
	@Column(name = "NEW_DATE")
	private Date newDate;//最后更新日期
	
	@Column(name = "STORE_ID")
	private String storeId;//门店编号
	
	@Column(name = "VIN")
	private String vin;
	
	@Column(name = "STATUS")
	private Integer status;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getModelId() {
		return modelId;
	}

	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	public String getHver() {
		return hver;
	}

	public void setHver(String hver) {
		this.hver = hver;
	}

	public String getSver() {
		return sver;
	}

	public void setSver(String sver) {
		this.sver = sver;
	}

	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	public String getCurrent() {
		return current;
	}

	public void setCurrent(String current) {
		this.current = current;
	}

	public String getP1() {
		return p1;
	}

	public void setP1(String p1) {
		this.p1 = p1;
	}

	public String getP2() {
		return p2;
	}

	public void setP2(String p2) {
		this.p2 = p2;
	}

	public String getP3() {
		return p3;
	}

	public void setP3(String p3) {
		this.p3 = p3;
	}

	public String getP4() {
		return p4;
	}

	public void setP4(String p4) {
		this.p4 = p4;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Date getOutDate() {
		return outDate;
	}

	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}

	public Date getNewDate() {
		return newDate;
	}

	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
