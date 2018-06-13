package com.third.fpaltform.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;


@Embeddable
public class DictionaryMultiKeys implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2794077691366282298L;
	
	@Column(name = "ID")
	private Integer uid;// 自增长的编号
	
	@Column(name = "CODE")
	private String code;

	public DictionaryMultiKeys(Integer uid, String code) {
		super();
		this.uid = uid;
		this.code = code;
	}

	public DictionaryMultiKeys() {
		super();
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "DictionaryMultiKeys [uid=" + uid + ", code=" + code + "]";
	}

}
