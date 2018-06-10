package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	
	@Lob
	@Column(name = "BATTERY_DIAGNOSIS")
	private String batteryDiagnosis;//电池检测
	@Lob
	@Column(name = "DRIVE_DIAGNOSIS")
	private String driveDiagnosis;//电驱动检测
	@Lob
	@Column(name = "INSTRUMENT_DIAGNOSIS")
	private String instrumentDiagnosis;//仪表检测
	
	@Column(name = "BATTERY_ID")
	private String batteryId;//
	
	@Column(name = "DRIVER_ID")
	private String driverId;//
	
	@Column(name = "INSTRUMENT_ID")
	private String instrumentId;//
	
	@Column(name = "SENSOR_ID")
	private String sensorId;//传感器
	
	@Column(name = "MOTO_ID")
	private String motoId;//电机

	@Column(name = "CREATE_TIME")
	private Date createTime;
	
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

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
	 * @return the diagnosisLogs
	 */
	public Set<DiagnosisLog> getDiagnosisLogs() {
		return diagnosisLogs;
	}

	/**
	 * @param diagnosisLogs the diagnosisLogs to set
	 */
	public void setDiagnosisLogs(Set<DiagnosisLog> diagnosisLogs) {
		this.diagnosisLogs = diagnosisLogs;
	}

	/**
	 * @return the vin
	 */
	public String getVin() {
		return vin;
	}

	/**
	 * @param vin the vin to set
	 */
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * @return the batteryDiagnosis
	 */
	public String getBatteryDiagnosis() {
		return batteryDiagnosis;
	}

	/**
	 * @param batteryDiagnosis the batteryDiagnosis to set
	 */
	public void setBatteryDiagnosis(String batteryDiagnosis) {
		this.batteryDiagnosis = batteryDiagnosis;
	}

	/**
	 * @return the driveDiagnosis
	 */
	public String getDriveDiagnosis() {
		return driveDiagnosis;
	}

	/**
	 * @param driveDiagnosis the driveDiagnosis to set
	 */
	public void setDriveDiagnosis(String driveDiagnosis) {
		this.driveDiagnosis = driveDiagnosis;
	}

	/**
	 * @return the instrumentDiagnosis
	 */
	public String getInstrumentDiagnosis() {
		return instrumentDiagnosis;
	}

	/**
	 * @param instrumentDiagnosis the instrumentDiagnosis to set
	 */
	public void setInstrumentDiagnosis(String instrumentDiagnosis) {
		this.instrumentDiagnosis = instrumentDiagnosis;
	}

	/**
	 * @return the batteryId
	 */
	public String getBatteryId() {
		return batteryId;
	}

	/**
	 * @param batteryId the batteryId to set
	 */
	public void setBatteryId(String batteryId) {
		this.batteryId = batteryId;
	}

	/**
	 * @return the driverId
	 */
	public String getDriverId() {
		return driverId;
	}

	/**
	 * @param driverId the driverId to set
	 */
	public void setDriverId(String driverId) {
		this.driverId = driverId;
	}

	/**
	 * @return the instrumentId
	 */
	public String getInstrumentId() {
		return instrumentId;
	}

	/**
	 * @param instrumentId the instrumentId to set
	 */
	public void setInstrumentId(String instrumentId) {
		this.instrumentId = instrumentId;
	}

	/**
	 * @return the sensorId
	 */
	public String getSensorId() {
		return sensorId;
	}

	/**
	 * @param sensorId the sensorId to set
	 */
	public void setSensorId(String sensorId) {
		this.sensorId = sensorId;
	}

	/**
	 * @return the motoId
	 */
	public String getMotoId() {
		return motoId;
	}

	/**
	 * @param motoId the motoId to set
	 */
	public void setMotoId(String motoId) {
		this.motoId = motoId;
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
