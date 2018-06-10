package com.third.fpaltform.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.third.fpaltform.common.ExcelResources;

/**
 * 部件表
 * @author Administrator
 *
 */
@Entity
@Table(name = "PART")
public class PartEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4445798642819222438L;
	
	@Id
	@Column(name = "UID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;// 自增长的编号
	
	@Column(name = "BAR_CODE")
	private String barCode;
	
	@Column(name = "TYPE")
	private int type;
	
	@Column(name = "MODELID")
	private int modelId;
	
	@Column(name = "HVER")
	private String hver;//硬件版本
	
	@Column(name = "SVER")
	private String sver;//软件版本
	
	@Column(name = "VOLTAGE")
	private String voltage;//额定工作电压
	
	@Column(name = "CURRENT")
	private String current;//最大限制工作电流-电驱系统
	
	@Column(name = "VOLTAGE_PRO_VALUE")
	private String voltageProValue;//电压保护值-电驱系统
	
	@Column(name = "ADAPTIVE_SENSOR")
	private String adaptiveSensor;//适配传感器-电驱系统
	
	@Column(name = "HIGH_SPEED_LIMIT")
	private String highSpeedLimit;//最高限速-电驱系统
	
	@Column(name = "CIRCUMFERENCE")
	private String circumference;//本轮周长-仪表

	@Column(name = "GEAR")
	private String gear;//档位-仪表
	
	@Column(name = "SOH")
	private String soh;//soh-电池
	
	@Column(name = "SOC")
	private String soc;//soc-电池
	
	@Column(name = "CYCLES")
	private String cycles;//循环次数-电池
	
	@Column(name = "PRODUCT_DATE")
	private Date productDate;//生产日期
	
	@Column(name = "OUT_DATE")
	private Date outDate;//出厂日期
	
	private String productTime;//生产日期
	
	private String outTime;//出厂日期
	
	@Column(name = "NEW_DATE")
	private Date newDate;//最后更新日期
	
	@Column(name = "STORE_ID")
	private String storeId;//门店编号
	
	@Column(name = "VIN")
	private String vin;
	
	@Column(name = "STATUS")
	private Integer status;//0: init 1:disable 2:enable

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
	 * @return the barCode
	 */
	@ExcelResources(order = 1, title = "barCode")
	public String getBarCode() {
		return barCode;
	}

	/**
	 * @param barCode the barCode to set
	 */
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	/**
	 * @return the type
	 */
	@ExcelResources(order = 2, title = "type")
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the modelId
	 */
	public int getModelId() {
		return modelId;
	}

	/**
	 * @param modelId the modelId to set
	 */
	public void setModelId(int modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the hver
	 */
	@ExcelResources(order = 3, title = "hver")
	public String getHver() {
		return hver;
	}

	/**
	 * @param hver the hver to set
	 */
	public void setHver(String hver) {
		this.hver = hver;
	}

	/**
	 * @return the sver
	 */
	@ExcelResources(order = 4, title = "sver")
	public String getSver() {
		return sver;
	}

	/**
	 * @param sver the sver to set
	 */
	public void setSver(String sver) {
		this.sver = sver;
	}

	/**
	 * @return the voltage
	 */
	@ExcelResources(order = 5, title = "voltage")
	public String getVoltage() {
		return voltage;
	}

	/**
	 * @param voltage the voltage to set
	 */
	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	/**
	 * @return the current
	 */
	@ExcelResources(order = 6, title = "current")
	public String getCurrent() {
		return current;
	}

	/**
	 * @param current the current to set
	 */
	public void setCurrent(String current) {
		this.current = current;
	}

	/**
	 * @return the voltageProValue
	 */
	@ExcelResources(order = 7, title = "voltageProValue")
	public String getVoltageProValue() {
		return voltageProValue;
	}

	/**
	 * @param voltageProValue the voltageProValue to set
	 */
	public void setVoltageProValue(String voltageProValue) {
		this.voltageProValue = voltageProValue;
	}

	/**
	 * @return the adaptiveSensor
	 */
	@ExcelResources(order = 8, title = "adaptiveSensor")
	public String getAdaptiveSensor() {
		return adaptiveSensor;
	}

	/**
	 * @param adaptiveSensor the adaptiveSensor to set
	 */
	public void setAdaptiveSensor(String adaptiveSensor) {
		this.adaptiveSensor = adaptiveSensor;
	}

	/**
	 * @return the highSpeedLimit
	 */
	@ExcelResources(order = 9, title = "highSpeedLimit")
	public String getHighSpeedLimit() {
		return highSpeedLimit;
	}

	/**
	 * @param highSpeedLimit the highSpeedLimit to set
	 */
	public void setHighSpeedLimit(String highSpeedLimit) {
		this.highSpeedLimit = highSpeedLimit;
	}

	/**
	 * @return the circumference
	 */
	@ExcelResources(order = 10, title = "circumference")
	public String getCircumference() {
		return circumference;
	}

	/**
	 * @param circumference the circumference to set
	 */
	public void setCircumference(String circumference) {
		this.circumference = circumference;
	}

	/**
	 * @return the gear
	 */
	@ExcelResources(order = 11, title = "gear")
	public String getGear() {
		return gear;
	}

	/**
	 * @param gear the gear to set
	 */
	public void setGear(String gear) {
		this.gear = gear;
	}

	/**
	 * @return the soh
	 */
	@ExcelResources(order = 12, title = "soh")
	public String getSoh() {
		return soh;
	}

	/**
	 * @param soh the soh to set
	 */
	public void setSoh(String soh) {
		this.soh = soh;
	}

	/**
	 * @return the soc
	 */
	@ExcelResources(order = 13, title = "soc")
	public String getSoc() {
		return soc;
	}

	/**
	 * @param soc the soc to set
	 */
	public void setSoc(String soc) {
		this.soc = soc;
	}

	/**
	 * @return the cycles
	 */
	@ExcelResources(order = 14, title = "cycles")
	public String getCycles() {
		return cycles;
	}

	/**
	 * @param cycles the cycles to set
	 */
	public void setCycles(String cycles) {
		this.cycles = cycles;
	}

	/**
	 * @return the productDate
	 */
	public Date getProductDate() {
		return productDate;
	}

	/**
	 * @param productDate the productDate to set
	 */
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	/**
	 * @return the outDate
	 */
	public Date getOutDate() {
		return outDate;
	}

	/**
	 * @param outDate the outDate to set
	 */
	public void setOutDate(Date outDate) {
		this.outDate = outDate;
	}
	
	/**
	 * @return the productTime
	 */
	@ExcelResources(order = 15, title = "productTime")
	public String getProductTime() {
		return productTime;
	}

	/**
	 * @param productTime the productTime to set
	 */
	public void setProductTime(String productTime) {
		this.productTime = productTime;
	}

	/**
	 * @return the outTime
	 */
	@ExcelResources(order = 16, title = "outTime")
	public String getOutTime() {
		return outTime;
	}

	/**
	 * @param outTime the outTime to set
	 */
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	/**
	 * @return the newDate
	 */
	public Date getNewDate() {
		return newDate;
	}

	/**
	 * @param newDate the newDate to set
	 */
	public void setNewDate(Date newDate) {
		this.newDate = newDate;
	}

	/**
	 * @return the storeId
	 */
	public String getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(String storeId) {
		this.storeId = storeId;
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
	 * @return the status
	 */
	public Integer getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
}
