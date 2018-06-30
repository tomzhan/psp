package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	
	@Column(name = "MODEL_UID")
	private Integer modelUid;
	
	@Column(name = "HVER")
	private Integer hver;//硬件版本
	
	@Column(name = "SVER")
	private Integer sver;//软件版本
	
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

	/**
	 * @return the uid
	 */
	public Integer getUid() {
		return uid;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(Integer uid) {
		this.uid = uid;
	}

	/**
	 * @return the modelUid
	 */
	public Integer getModelUid() {
		return modelUid;
	}

	/**
	 * @param modelUid the modelUid to set
	 */
	public void setModelUid(Integer modelUid) {
		this.modelUid = modelUid;
	}

	/**
	 * @return the hver
	 */
	public Integer getHver() {
		return hver;
	}

	/**
	 * @param hver the hver to set
	 */
	public void setHver(Integer hver) {
		this.hver = hver;
	}

	/**
	 * @return the sver
	 */
	public Integer getSver() {
		return sver;
	}

	/**
	 * @param sver the sver to set
	 */
	public void setSver(Integer sver) {
		this.sver = sver;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}

	/**
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**
	 * @return the signCode
	 */
	public String getSignCode() {
		return signCode;
	}

	/**
	 * @param signCode the signCode to set
	 */
	public void setSignCode(String signCode) {
		this.signCode = signCode;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the uploadTime
	 */
	public Date getUploadTime() {
		return uploadTime;
	}

	/**
	 * @param uploadTime the uploadTime to set
	 */
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
}
