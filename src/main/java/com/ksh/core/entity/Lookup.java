package com.ksh.core.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cor_lookup")
public class Lookup implements BaseLookup, Serializable {
    
    private static final long serialVersionUID = 5557055793503950007L;

    public static final Lookup SALARY_OPEATION_PLUS = new Lookup(101L);
    public static final Lookup SALARY_OPEATION_SUBTRACT = new Lookup(102L);
//    public static final Lookup EMPLOYEE_STATUS_INACTIVE = new Lookup(110L);
//    public static final Lookup EMPLOYEE_STATUS_ACTIVE = new Lookup(111L);

    @Id
    @Column(name = "recid", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long recid;

    @Column(name = "name")
    private String name;

    @Column(name = "lookup_type_id")
    private Long lookupTypeId;

    @ManyToOne
    @JoinColumn(name = "lookup_type_id", insertable = false, updatable = false)
    private LookupType lookupType;

    public Lookup() {
    }

    public Lookup(Long id) {
	this.recid = id;
    }

    public Lookup(Long id, String name) {
	this.recid = id;
	this.name = name;
    }

    @Override
    public String toString() {
	return "Lookup [recid=" + recid + ", name=" + name + ", lookupType=" + lookupType + "]";
    }

    public Long getRecid() {
	return recid;
    }

    public void setRecid(Long recid) {
	this.recid = recid;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getLabel() {
	return this.name;
    }

    public Long getValue() {
	return this.recid;
    }

    public void setLookupType(LookupType lookupType) {
	this.lookupType = lookupType;
    }

    public LookupType getLookupType() {
	return lookupType;
    }

    public void setLookupTypeId(Long lookupTypeId) {
	this.lookupTypeId = lookupTypeId;
    }

    public Long getLookupTypeId() {
	return lookupTypeId;
    }

}
