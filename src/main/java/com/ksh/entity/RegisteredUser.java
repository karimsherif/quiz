package com.ksh.entity;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * @author Karim Sherif
 */
@ManagedBean(name = "registeredUser")
@SessionScoped
public class RegisteredUser implements Serializable {
	private static final long serialVersionUID = -9065465228061817197L;

	private Long userId;
	private Boolean isAdmin = Boolean.FALSE;
	private String direction = "ltr";
	private String locale = "ar";

	
	
	private Long ownerId;
	private Long visitorId;
	

	// ##############################################################################
	public RegisteredUser() {
	}

	public boolean hasPrivilege(int privilegeId) {
		// for (PrivilegeMapping pm : privilegeMappingList) {
		// if (privilegeId == pm.getPrivilegeId()) {
		// return true;
		// }
		// }
		// return false;
		return true;
	}

	public boolean hasCategoryPrivilege(Long categoryId) {
		// boolean h = false;
		// for (Long l : categoryList) {
		// if (l.equals(categoryId)) {
		// h = true;
		// break;
		// }
		// }
		// return h;
		return true;
	}

	public String changeToRTL() {
		this.direction = "rtl";
		return null;
	}

	public String changeToLTR() {
		this.direction = "ltr";
		return null;
	}

	// ##############################################################################


	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}


	public void setDirection(String direction) {
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLocale() {
		return locale;
	}


	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}


	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public Long getVisitorId() {
		return visitorId;
	}

	public void setVisitorId(Long visitorId) {
		this.visitorId = visitorId;
	}

}
