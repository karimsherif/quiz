package com.ksh.core.entity.resultsetmapping;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.ksh.core.entity.BaseEntity;
import com.ksh.core.entity.BaseLookup;

@Entity
public class LookupMapping extends BaseEntity implements BaseLookup {

	private static final long serialVersionUID = -8487999926692650505L;

	@Id
	private Long recid;

	private String name;
	
	private String description;

	public LookupMapping() {
	}

	public LookupMapping(Long id) {
		this.recid = id;
	}

	public LookupMapping(Long recid, String name) {
		this.recid = recid;
		this.name = name;
	}
	public LookupMapping(Integer recid, String name) {
		this.recid = Long.parseLong(recid.toString());
		this.name = name;
	}

	public void setRecid(Long recid) {
		this.recid = recid;
	}

	public Long getRecid() {
		return recid;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "LookupMapping [recid=" + recid + ", name=" + name + ", description=" + description + "]";
	}

	public String getLabel() {
		return name;
	}

	public Long getValue() {
		return recid;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof LookupMapping))
			return false;
		else {
			LookupMapping other = (LookupMapping) obj;
			if (getRecid() == null) {
				if (other.getRecid() != null)
					return false;
			} else if (!getRecid().equals(other.getRecid()))
				return false;
			return true;
		}
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
