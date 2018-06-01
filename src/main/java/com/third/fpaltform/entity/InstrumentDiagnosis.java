package com.third.fpaltform.entity;

import java.io.Serializable;

public class InstrumentDiagnosis implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String barcode;
	
	private boolean addkey;
	
	private boolean deckey;
	
	private boolean isfault;
	
	private boolean mkey;
	
	private boolean power;
	
	private boolean switchkey;

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public boolean isAddkey() {
		return addkey;
	}

	public void setAddkey(boolean addkey) {
		this.addkey = addkey;
	}

	public boolean isDeckey() {
		return deckey;
	}

	public void setDeckey(boolean deckey) {
		this.deckey = deckey;
	}

	public boolean isIsfault() {
		return isfault;
	}

	public void setIsfault(boolean isfault) {
		this.isfault = isfault;
	}

	public boolean isMkey() {
		return mkey;
	}

	public void setMkey(boolean mkey) {
		this.mkey = mkey;
	}

	public boolean isPower() {
		return power;
	}

	public void setPower(boolean power) {
		this.power = power;
	}

	public boolean isSwitchkey() {
		return switchkey;
	}

	public void setSwitchkey(boolean switchkey) {
		this.switchkey = switchkey;
	}
}
