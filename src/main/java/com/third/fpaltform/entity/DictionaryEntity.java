package com.third.fpaltform.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "DICTIONARY")
public class DictionaryEntity implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7513895645363975330L;
	
	@EmbeddedId
	private DictionaryMultiKeys dictionaryMultiKeys;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "ENAME")
	private String emane;

	@Column(name = "NEXTID")
	private String nextId;
	
	@Transient
	private Integer id;
	@Transient
	private String code;
	

	public DictionaryMultiKeys getDictionaryMultiKeys() {
		return dictionaryMultiKeys;
	}

	public void setDictionaryMultiKeys(DictionaryMultiKeys dictionaryMultiKeys) {
		this.dictionaryMultiKeys = dictionaryMultiKeys;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmane() {
		return emane;
	}

	public void setEmane(String emane) {
		this.emane = emane;
	}

	public String getNextId() {
		return nextId;
	}

	public void setNextId(String nextId) {
		this.nextId = nextId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
