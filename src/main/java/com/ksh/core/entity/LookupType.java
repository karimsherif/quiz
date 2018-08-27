package com.ksh.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ksh.core.BaseObject;

@Entity
@Table(name = "cor_lookup_type")
public class LookupType extends BaseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3159335646420018363L;

	public static LookupType SALARY_OPEATION=new LookupType(100L);
	public static LookupType EMPLOYEE_STATUS=new LookupType(110L);
	
	@Id
	@Column(name = "recid", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long recid;

	@Column(name = "name")
	private String name;

	@Column(name = "parent_id")
	private Long parentId;

	@Column(name = "level")
	private Long level = 0L;

	// -----------------------constructor------------------------//

	public LookupType() {
	}

	@Override
	public String toString() {
		return "LookupType [recid=" + recid + ", name=" + name + "]";
	}

	public LookupType(Long id) {
		this.recid = id;
	}

	public LookupType(Long recid, String name) {
		super();
		this.recid = recid;
		this.name = name;
	}

	// -------------------------get.-set.-------------------------//

	public Long getRecid() {
		return this.recid;
	}

	public void setRecid(Long recid) {
		this.recid = recid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}

}
