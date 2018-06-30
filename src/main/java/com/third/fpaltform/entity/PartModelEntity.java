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
@Table(name = "PART_MODEL")
public class PartModelEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5703459363883257013L;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 自增长的编号
	
	@Column(name = "TYPE")
	private Integer type;//部件类型
	
	@Column(name = "MODELID")
	private Integer modelId;//型号
	
	@Column(name = "NAME")
	private String name;//部门名称
	
	@Column(name = "MANUFACTOR")
	private String manufactor;//厂家
	
	@Column(name = "MEMO")
	private String meno;//备注
	
	@Column(name = "CREATE_TIME")
	private Date createTime;//创建日期
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime;//更新日期

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
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the modelId
	 */
	public Integer getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the manufactor
	 */
	public String getManufactor() {
		return manufactor;
	}

	/**
	 * @param manufactor the manufactor to set
	 */
	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	/**
	 * @return the meno
	 */
	public String getMeno() {
		return meno;
	}

	/**
	 * @param meno the meno to set
	 */
	public void setMeno(String meno) {
		this.meno = meno;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
