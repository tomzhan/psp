package com.third.fpaltform.entity;

import java.io.Serializable;

public class DealwithDiagnosis implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private boolean batterchargemaintain;
	
	private boolean battermaintain;
	
	private boolean changebatter;
	
	private boolean changebrake;
	
	private boolean changecontrol;
	
	private boolean changedriversensor;
	
	private boolean changefeepart;
	
	private boolean changemotor;
	
	private boolean changetransfer;
	
	private boolean changewire;
	
	private boolean plugwire;

	public boolean isBatterchargemaintain() {
		return batterchargemaintain;
	}

	public void setBatterchargemaintain(boolean batterchargemaintain) {
		this.batterchargemaintain = batterchargemaintain;
	}

	public boolean isBattermaintain() {
		return battermaintain;
	}

	public void setBattermaintain(boolean battermaintain) {
		this.battermaintain = battermaintain;
	}

	public boolean isChangebatter() {
		return changebatter;
	}

	public void setChangebatter(boolean changebatter) {
		this.changebatter = changebatter;
	}

	public boolean isChangebrake() {
		return changebrake;
	}

	public void setChangebrake(boolean changebrake) {
		this.changebrake = changebrake;
	}

	public boolean isChangecontrol() {
		return changecontrol;
	}

	public void setChangecontrol(boolean changecontrol) {
		this.changecontrol = changecontrol;
	}

	public boolean isChangedriversensor() {
		return changedriversensor;
	}

	public void setChangedriversensor(boolean changedriversensor) {
		this.changedriversensor = changedriversensor;
	}

	public boolean isChangefeepart() {
		return changefeepart;
	}

	public void setChangefeepart(boolean changefeepart) {
		this.changefeepart = changefeepart;
	}

	public boolean isChangemotor() {
		return changemotor;
	}

	public void setChangemotor(boolean changemotor) {
		this.changemotor = changemotor;
	}

	public boolean isChangetransfer() {
		return changetransfer;
	}

	public void setChangetransfer(boolean changetransfer) {
		this.changetransfer = changetransfer;
	}

	public boolean isChangewire() {
		return changewire;
	}

	public void setChangewire(boolean changewire) {
		this.changewire = changewire;
	}

	public boolean isPlugwire() {
		return plugwire;
	}

	public void setPlugwire(boolean plugwire) {
		this.plugwire = plugwire;
	}
}
