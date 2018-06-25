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
@Table(name = "DIAGNOSIS_LOG")
public class DiagnosisLog implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8213593913158529551L;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 自增长的编号
	
	@ManyToOne
	@JoinColumn(name="VEHICLE_UID")
	@JsonIgnore
	private VehicleEntity vehicle;
	
	@Column(name = "BATTERY_DIAGNOSIS")
	private String batteryDiagnosis;//电池检测
	
	@Column(name = "DRIVE_DIAGNOSIS")
	private String driveDiagnosis;//电驱动检测
	
	@Column(name = "INSTRUMENT_DIAGNOSIS")
	private String instrumentDiagnosis;//仪表检测
	
	@Column(name = "BATTERY_CODE")
	private String batteryCode;//电池barcode
	
	@Column(name = "DRIVE_CODE")
	private String driveCode;//电驱动
	
	@Column(name = "INSTRUMENT_CODE")
	private String instrumentCode;//仪表barcode
	
	@Column(name = "CREATE_TIME")
	private Date createTime;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public VehicleEntity getVehicle() {
		return vehicle;
	}

	public void setVehicle(VehicleEntity vehicle) {
		this.vehicle = vehicle;
	}

	public String getBatteryDiagnosis() {
		return batteryDiagnosis;
	}

	public void setBatteryDiagnosis(String batteryDiagnosis) {
		this.batteryDiagnosis = batteryDiagnosis;
	}

	public String getDriveDiagnosis() {
		return driveDiagnosis;
	}

	public void setDriveDiagnosis(String driveDiagnosis) {
		this.driveDiagnosis = driveDiagnosis;
	}

	public String getInstrumentDiagnosis() {
		return instrumentDiagnosis;
	}

	public void setInstrumentDiagnosis(String instrumentDiagnosis) {
		this.instrumentDiagnosis = instrumentDiagnosis;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getBatteryCode() {
		return batteryCode;
	}

	public void setBatteryCode(String batteryCode) {
		this.batteryCode = batteryCode;
	}

	public String getDriveCode() {
		return driveCode;
	}

	public void setDriveCode(String driveCode) {
		this.driveCode = driveCode;
	}

	public String getInstrumentCode() {
		return instrumentCode;
	}

	public void setInstrumentCode(String instrumentCode) {
		this.instrumentCode = instrumentCode;
	}
	
	
}
