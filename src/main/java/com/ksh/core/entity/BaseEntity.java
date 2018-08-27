package com.ksh.core.entity;

import java.io.Serializable;

import com.ksh.core.BaseObject;

public abstract class BaseEntity extends BaseObject implements Serializable {


	public abstract Long getRecid();

	private static final long serialVersionUID = 1065370168527897619L;

	
/*	@Override
	public boolean equals(Object entity) {
		if (entity == null) {
			return false;
		}
		if (getClass() != entity.getClass()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) entity;
		if (!this.getRecid().equals(other.getRecid())) {
			return false;
		}
		return true;
	}*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BaseEntity))
			return false;
		BaseEntity other = (BaseEntity) obj;
		if (getRecid() == null) {
			if (other.getRecid() != null)
				return false;
		} else if (!getRecid().equals(other.getRecid()))
			return false;
		return true;
	}
	public void loadLazyLists(Integer eagerLevel) {
	}
}
