package com.ksh.common;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

import com.ksh.core.managedBean.BaseBean;

/*
 * @author Karim Sherif
 */
@Named
@ViewScoped
public class ThemeBean extends BaseBean {

    private String theme;
    private static final long serialVersionUID = 1L;

    @PostConstruct
    public void loadData() {
	loadMyTheam();
    }

    public String getTheme() {
	try {
	    Cookie[] cookies = super.getRequest().getCookies();
	    if (cookies == null || cookies.length < 1) {
		this.theme = getDefaultTheme();
	    }
	    String themeName = null;
	    for (int i = 0; i < cookies.length; i++) {
		Cookie cookie = cookies[i];
		themeName = cookie.getName();
		if (themeName.equals("themeName")) {
		    this.theme = cookie.getValue();
		    break;
		}

	    }
	    if (this.theme == null) {
		this.theme = getDefaultTheme();
	    }
	} catch (Exception e) {
	    this.theme = getDefaultTheme();
	}
	return this.theme;
    }

    public void loadMyTheam() {

    }

    public String saveTheme(String themeName) {
	Cookie cookie = new Cookie("themeName", themeName);
	cookie.setMaxAge(60 * 60 * 24 * 30);// means 1 month
	// String
	// cookiePath=super.getRequest().getContextPath()+"/controlling/";
	String cookiePath = super.getRequest().getContextPath() + "/";
	cookie.setPath(cookiePath);
	super.getResonse().addCookie(cookie);
	return "toThemePage";
    }

    public void setDefaultTheme() {
	Cookie cookie = new Cookie("themeName", Constant.DEFAULT_THEME);
	cookie.setPath(super.getRequest().getServerName() + ":" + super.getRequest().getServerPort() + "/" + super.getRequest().getContextPath()
		+ "/");
//	cookie.setPath(super.getRequest().getServerName() + ":" + super.getRequest().getServerPort() + "/" + super.getRequest().getContextPath()
//		+ "/controlling/");
	super.getResonse().addCookie(cookie);
    }

    public String getDefaultTheme() {
	return Constant.DEFAULT_THEME;
    }
}
