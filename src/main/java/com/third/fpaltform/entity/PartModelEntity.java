package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	
	@OneToMany(mappedBy="partModel")
	@JsonIgnore
	private Set<HardwareVersionEntity> hardwareVersions;
	
	@Column(name = "TYPE")
	private Integer type;//部件类型
	
	@Column(name = "MODELID")
	private String modelId;//型号
	
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Set<HardwareVersionEntity> getHardwareVersions() {
		return hardwareVersions;
	}

	public void setHardwareVersions(Set<HardwareVersionEntity> hardwareVersions) {
		this.hardwareVersions = hardwareVersions;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufactor() {
		return manufactor;
	}

	public void setManufactor(String manufactor) {
		this.manufactor = manufactor;
	}

	public String getMeno() {
		return meno;
	}

	public void setMeno(String meno) {
		this.meno = meno;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
