package com.third.fpaltform.entity;

import java.io.Serializable;

public class Diagnosis implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String vin;
	
	private BatteryDiagnosis batteryDiagnosis;
	
	private DealwithDiagnosis dealwithDiagnosis;
	
	private DriveDiagnosis driveDiagnosis;
	
	private InstrumentDiagnosis instrumentDiagnosis;
	
	private String sensorId;//传感器
	
	private String motoId;//电机

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public BatteryDiagnosis getBatteryDiagnosis() {
		return batteryDiagnosis;
	}

	public void setBatteryDiagnosis(BatteryDiagnosis batteryDiagnosis) {
		this.batteryDiagnosis = batteryDiagnosis;
	}

	public DealwithDiagnosis getDealwithDiagnosis() {
		return dealwithDiagnosis;
	}

	public void setDealwithDiagnosis(DealwithDiagnosis dealwithDiagnosis) {
		this.dealwithDiagnosis = dealwithDiagnosis;
	}

	public DriveDiagnosis getDriveDiagnosis() {
		return driveDiagnosis;
	}

	public void setDriveDiagnosis(DriveDiagnosis driveDiagnosis) {
		this.driveDiagnosis = driveDiagnosis;
	}

	public InstrumentDiagnosis getInstrumentDiagnosis() {
		return instrumentDiagnosis;
	}

	public void setInstrumentDiagnosis(InstrumentDiagnosis instrumentDiagnosis) {
		this.instrumentDiagnosis = instrumentDiagnosis;
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
}
