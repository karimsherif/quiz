package com.ksh.quiz.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ksh.core.managedBean.BaseBean;

/*
 * @author KarimSherif
 */
@Named
@ViewScoped
public class VisitorLoginBean extends BaseBean {
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String email;

	@PostConstruct
	public void loadData() {
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
