package com.third.fpaltform.entity;

import java.io.Serializable;

public class DriveDiagnosis implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private String barcode;
	
	private boolean brake;
	
	private boolean circleprotect;
	
	private boolean drivermosdefault;
	
	private boolean driversensor;
	
	private boolean electricmotor;
	
	private boolean flyprotect;
	
	private boolean hall;
	
	private boolean isfault;
	
	private boolean lowvoltageprotect;
	
	private boolean meterlinkdefault;
	
	private boolean mosovertemprature;
	
	private boolean motivepower;
	
	private boolean overcurrentprotect;
	
	private boolean speedsensor;
	
	private boolean transfer;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean isBrake() {
		return brake;
	}

	public void setBrake(boolean brake) {
		this.brake = brake;
	}

	public boolean isCircleprotect() {
		return circleprotect;
	}

	public void setCircleprotect(boolean circleprotect) {
		this.circleprotect = circleprotect;
	}

	public boolean isDrivermosdefault() {
		return drivermosdefault;
	}

	public void setDrivermosdefault(boolean drivermosdefault) {
		this.drivermosdefault = drivermosdefault;
	}

	public boolean isDriversensor() {
		return driversensor;
	}

	public void setDriversensor(boolean driversensor) {
		this.driversensor = driversensor;
	}

	public boolean isElectricmotor() {
		return electricmotor;
	}

	public void setElectricmotor(boolean electricmotor) {
		this.electricmotor = electricmotor;
	}

	public boolean isFlyprotect() {
		return flyprotect;
	}

	public void setFlyprotect(boolean flyprotect) {
		this.flyprotect = flyprotect;
	}

	public boolean isHall() {
		return hall;
	}

	public void setHall(boolean hall) {
		this.hall = hall;
	}

	public boolean isIsfault() {
		return isfault;
	}

	public void setIsfault(boolean isfault) {
		this.isfault = isfault;
	}

	public boolean isLowvoltageprotect() {
		return lowvoltageprotect;
	}

	public void setLowvoltageprotect(boolean lowvoltageprotect) {
		this.lowvoltageprotect = lowvoltageprotect;
	}

	public boolean isMeterlinkdefault() {
		return meterlinkdefault;
	}

	public void setMeterlinkdefault(boolean meterlinkdefault) {
		this.meterlinkdefault = meterlinkdefault;
	}

	public boolean isMosovertemprature() {
		return mosovertemprature;
	}

	public void setMosovertemprature(boolean mosovertemprature) {
		this.mosovertemprature = mosovertemprature;
	}

	public boolean isMotivepower() {
		return motivepower;
	}

	public void setMotivepower(boolean motivepower) {
		this.motivepower = motivepower;
	}

	public boolean isOvercurrentprotect() {
		return overcurrentprotect;
	}

	public void setOvercurrentprotect(boolean overcurrentprotect) {
		this.overcurrentprotect = overcurrentprotect;
	}

	public boolean isSpeedsensor() {
		return speedsensor;
	}

	public void setSpeedsensor(boolean speedsensor) {
		this.speedsensor = speedsensor;
	}

	public boolean isTransfer() {
		return transfer;
	}

	public void setTransfer(boolean transfer) {
		this.transfer = transfer;
	}
}
