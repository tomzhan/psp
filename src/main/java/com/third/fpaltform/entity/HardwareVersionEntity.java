package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "HARDWARE_VERSION")
public class HardwareVersionEntity implements Serializable{

	private static final long serialVersionUID = 239981581071051514L;
	//(0:已失效 1:未升级版本)
	public static final Integer STATUS_No = 0;
	public static final Integer STATUS_YES = 1;
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	@ManyToOne
	@JoinColumn(name="PART_MODEL_UID")
	@JsonIgnore
	private PartModelEntity partModel;
	
	@Column(name = "HVER")
	private String hver;//硬件版本
	
	@Column(name = "SVER")
	private String sver;//软件版本
	
	@Column(name = "URL")
	private String url;
	
	@Column(name = "OPERATOR", nullable = false, length = 30)
	private String operator;
	
	@Column(name = "STATUS", nullable = false)
	private Integer status;
	
	@Column(name = "SIGNCODE", nullable = false)
	private String signCode;
	
	@Column(name = "FILENAME", nullable = false)
	private String fileName;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "UPLOAD_TIME", nullable = false)
	private Date uploadTime;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public PartModelEntity getPartModel() {
		return partModel;
	}

	public void setPartModel(PartModelEntity partModel) {
		this.partModel = partModel;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSignCode() {
		return signCode;
	}

	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
