package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DIVISION")
public class DivisionEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 自增长的编号
	
	@Column(name = "PARENT_DIVISION_UID")
	Integer parentDivisionUid;
	
	@Column(name = "DIVISION_NAME", nullable=false, length=20)
	String divisionName;
	
	@Column(name = "DESCRIPTION", length=100)
	String description;
	
	@OneToMany(mappedBy="division")
	@JsonIgnore
	private Set<UserEntity> users;
	
	@OneToMany(mappedBy="divisionRole")
	@JsonIgnore
	private Set<DivisionRoleEntity> divisionRoles;
	
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	@Transient
	private List<DivisionEntity> divisions;
	
	@Transient
	private List<RoleEntity> roles;
	
	@JsonIgnore
	@Version
	@Column(name = "VERSION")
	private Integer version;

	public DivisionEntity() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getParentDivisionUid() {
		return parentDivisionUid;
	}

	public void setParentDivisionUid(Integer parentDivisionUid) {
		this.parentDivisionUid = parentDivisionUid;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}

	public Set<DivisionRoleEntity> getDivisionRoles() {
		return divisionRoles;
	}

	public void setDivisionRoles(Set<DivisionRoleEntity> divisionRoles) {
		this.divisionRoles = divisionRoles;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public List<DivisionEntity> getDivisions() {
		return divisions;
	}

	public void setDivisions(List<DivisionEntity> divisions) {
		this.divisions = divisions;
	}
	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

	public JSONObject toJson()
	{
		JSONObject json = new JSONObject();
		json.put("uid", uid);
		json.put("parentDivisionUid", parentDivisionUid);
		json.put("divisionName", divisionName);
		json.put("description", description);
		return json;
	}

	public DivisionEntity(Integer uid, Integer parentDivisionUid, String divisionName, String description,
			Date createTime, Date updateTime) {
		super();
		this.uid = uid;
		this.parentDivisionUid = parentDivisionUid;
		this.divisionName = divisionName;
		this.description = description;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	
}
