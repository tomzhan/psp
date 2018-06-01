package com.third.fpaltform.entity;

import java.io.Serializable;

public class BatteryDiagnosis implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String barcode;
	
	private boolean batteryfault;
	
	private boolean cellfault;
	
	private boolean cuttingout;
	
	private boolean isfault;
	
	private boolean lowtempraturecharge;
	
	private boolean lowtempraturedischarge;
	
	private boolean lowvoltage;
	
	private boolean maintain;
	
	private boolean overcharge;
	
	private boolean overdischarge;
	
	private boolean overtempraturecharge;
	
	private boolean overtempraturedischarge;
	
	private boolean overvoltage;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean isBatteryfault() {
		return batteryfault;
	}

	public void setBatteryfault(boolean batteryfault) {
		this.batteryfault = batteryfault;
	}

	public boolean isCellfault() {
		return cellfault;
	}

	public void setCellfault(boolean cellfault) {
		this.cellfault = cellfault;
	}

	public boolean isCuttingout() {
		return cuttingout;
	}

	public void setCuttingout(boolean cuttingout) {
		this.cuttingout = cuttingout;
	}

	public boolean isIsfault() {
		return isfault;
	}

	public void setIsfault(boolean isfault) {
		this.isfault = isfault;
	}

	public boolean isLowtempraturecharge() {
		return lowtempraturecharge;
	}

	public void setLowtempraturecharge(boolean lowtempraturecharge) {
		this.lowtempraturecharge = lowtempraturecharge;
	}

	public boolean isLowtempraturedischarge() {
		return lowtempraturedischarge;
	}

	public void setLowtempraturedischarge(boolean lowtempraturedischarge) {
		this.lowtempraturedischarge = lowtempraturedischarge;
	}

	public boolean isLowvoltage() {
		return lowvoltage;
	}

	public void setLowvoltage(boolean lowvoltage) {
		this.lowvoltage = lowvoltage;
	}

	public boolean isMaintain() {
		return maintain;
	}

	public void setMaintain(boolean maintain) {
		this.maintain = maintain;
	}

	public boolean isOvercharge() {
		return overcharge;
	}

	public void setOvercharge(boolean overcharge) {
		this.overcharge = overcharge;
	}

	public boolean isOverdischarge() {
		return overdischarge;
	}

	public void setOverdischarge(boolean overdischarge) {
		this.overdischarge = overdischarge;
	}

	public boolean isOvertempraturecharge() {
		return overtempraturecharge;
	}

	public void setOvertempraturecharge(boolean overtempraturecharge) {
		this.overtempraturecharge = overtempraturecharge;
	}

	public boolean isOvertempraturedischarge() {
		return overtempraturedischarge;
	}

	public void setOvertempraturedischarge(boolean overtempraturedischarge) {
		this.overtempraturedischarge = overtempraturedischarge;
	}

	public boolean isOvervoltage() {
		return overvoltage;
	}

	public void setOvervoltage(boolean overvoltage) {
		this.overvoltage = overvoltage;
	}
}
