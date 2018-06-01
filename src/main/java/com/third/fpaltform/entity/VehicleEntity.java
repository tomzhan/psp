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
@Table(name="VEHICLE")
public class VehicleEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1221800550912072974L;
	
	@Id
	@Column(name = "UID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	
	@OneToMany(mappedBy="vehicle")
	@JsonIgnore
	private Set<DiagnosisLog> diagnosisLogs;
	
	@Column(name = "VIN")
	private String vin;//vin码
	
	@Column(name = "BATTERY_DIAGNOSIS")
	private String batteryDiagnosis;//电池检测
	
	@Column(name = "DRIVE_DIAGNOSIS")
	private String driveDiagnosis;//电驱动检测
	
	@Column(name = "INSTRUMENT_DIAGNOSIS")
	private String instrumentDiagnosis;//仪表检测
	
	@Column(name = "BATTERY_ID")
	private Integer batteryId;//
	
	@Column(name = "DRIVER_ID")
	private Integer driverId;//
	
	@Column(name = "INSTRUMENT_ID")
	private Integer instrumentId;//

	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Set<DiagnosisLog> getDiagnosisLogs() {
		return diagnosisLogs;
	}

	public void setDiagnosisLogs(Set<DiagnosisLog> diagnosisLogs) {
		this.diagnosisLogs = diagnosisLogs;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
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

	public Integer getBatteryId() {
		return batteryId;
	}

	public void setBatteryId(Integer batteryId) {
		this.batteryId = batteryId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Integer getInstrumentId() {
		return instrumentId;
	}

	public void setInstrumentId(Integer instrumentId) {
		this.instrumentId = instrumentId;
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
