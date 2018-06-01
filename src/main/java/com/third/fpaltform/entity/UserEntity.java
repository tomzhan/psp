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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "USER")
public class UserEntity implements Serializable{
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//DELETE(0), ACTIVATE(1), DISABLE(2);//0:账户已删除 1:启用状态 2:禁用状态 
	public static final Integer STATUS_DELETE = 0;
	public static final Integer STATUS_ACTIVATE = 1;
	public static final Integer STATUS_DISABLE = 2;
	
	@Id
	@Column(name = "UID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;	
	
	@ManyToOne
	@JoinColumn(name="DIVISION_UID")
	@JsonIgnore
	private DivisionEntity division;
	
	@JsonIgnore
	@OneToMany(mappedBy="userRole")
	private Set<UserRoleEntity> userRoles;
	
	@Column(name = "ACCOUNT", nullable=false, length=50)
	private String account;

	@Column(name = "PASSWORD", nullable=false, length=200)
	private String passWord;

	@Column(name="REMARK", length=200)
	private String remark;
	
	@Column(name = "STATUS", nullable=false, length=1)
	private Integer status;
	
	@Column(name = "CREATE_TIME", nullable = false)
	private Date createTime;
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	
	@Column(name = "OPERATOR", nullable = false)
	private String operator;
	
	@Column(name = "LOGIN_TIME")
	private Date loginTime;
	
	@Column(name = "TOKEN")
	private String token;
	
	@Transient
	private List<RoleEntity> roles;
	
	@Transient
	private Integer divisionUid;
	
	@Transient
	private String divisionName;
	
	@Version
	@Column(name = "VERSION")
	@JsonIgnore
	private Integer version;
	
	public UserEntity() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public DivisionEntity getDivision() {
		return division;
	}

	public void setDivision(DivisionEntity division) {
		this.division = division;
	}

	public Set<UserRoleEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRoleEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}
	
	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	
	public Integer getDivisionUid() {
		return divisionUid;
	}

	public void setDivisionUid(Integer divisionUid) {
		this.divisionUid = divisionUid;
	}

	public String getDivisionName() {
		return divisionName;
	}

	public void setDivisionName(String divisionName) {
		this.divisionName = divisionName;
	}

	public String toJson() {
		JSONObject json = new JSONObject();
		json.put("uid", uid);
		json.put("account", account);
		json.put("token", token);
		json.put("divisionUid", division == null ? null : division.getUid());
		json.put("status", status);
		json.put("operator", operator);
		json.put("loginTime", loginTime);
		json.put("createTime", createTime);
		json.put("remark", remark);
		return json.toString();
	}

	public UserEntity(Integer uid, String account, String remark, Integer status, Date createTime,
			Date updateTime, String operator, Date loginTime, String token, List<RoleEntity> roles, Integer divisionUid, String divisionName) {
		super();
		this.uid = uid;
		this.account = account;
		this.remark = remark;
		this.status = status;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.operator = operator;
		this.loginTime = loginTime;
		this.token = token;
		this.roles = roles;
		this.divisionUid = divisionUid;
		this.divisionName = divisionName;
	}
	
}
