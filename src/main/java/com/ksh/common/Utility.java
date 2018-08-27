/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksh.common;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author KarimSherif
 */
public class Utility implements Serializable {

	private static final long serialVersionUID = 4285366513991073086L;
	static char[] Enc = { 'K', 'S', 'H' };

	public static String PasswordEncrypt(String password) {
		if (password == null) {
			return "";
		}
		int j = 0;
		char[] C = password.toCharArray();
		for (int i = 0; i < C.length; i++) {
			C[i] ^= Enc[j];
			j++;
			if (j > 2) {
				j = 0;
			}
		}
		return String.valueOf(C);
	}

	public static boolean convertNymberToBoolean(int number) {
		if (number > 0) {
			return true;
		} else {
			return false;
		}

	}

	public static int convertBooleanToNumber(boolean flag) {
		if (flag == true) {
			return 1;
		} else {
			return 0;
		}

	}

	public static void setSessionAttribute(String sharingName, Object object) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = req.getSession();
		session.setAttribute(sharingName, object);
	}

	public static Object getSessionAttribute(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(name);
		return obj;
	}

	public static Object getViewAttribute(String name) {
		if (FacesContext.getCurrentInstance().getViewRoot() != null) {
			Map<String, Object> viewMap = FacesContext.getCurrentInstance().getViewRoot().getViewMap();

			return viewMap.get(name);
		}
		return null;
	}

	public static Object getRequestAttribute(String name) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		return req.getAttribute(name);
	}

	public static void terminateSession() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest req = (HttpServletRequest) context.getExternalContext().getRequest();
		req.getSession().invalidate();

	}

	public static String getGeneratedString(int noOfChars) {
		char[] generatedString = new char[noOfChars];
		int character = 'A';
		int temp = 0;
		for (int i = 0; i < noOfChars; i++) {
			temp = (int) (Math.random() * 3);
			switch (temp) {
			case 0:
				character = '0' + (int) (Math.random() * 10);
				break;
			case 1:
				character = 'a' + (int) (Math.random() * 26);
				break;
			case 2:
				character = 'A' + (int) (Math.random() * 26);
				break;
			}
			generatedString[i] = (char) character;
		}
		return new String(generatedString);
	}

	public static String getSubBody(int from, int to, String string) {
		return string.substring(from, to);
	}

	public static java.sql.Timestamp convertToTimeStamp(java.util.Date date) {
		java.sql.Timestamp ts1 = new java.sql.Timestamp(date.getTime());
		return ts1;
	}

	public static java.util.Date convertToUtilDate(java.sql.Timestamp timestamp) {
		java.util.Date date = new java.util.Date(timestamp.getTime());
		return date;
	}

	public static boolean deleteFile(String fileName) {
		return new File(fileName).delete();

	}
	public static List<Long> getYearList() {
		List<Long> yearList = new ArrayList<Long>();
		for (Long i = 2010L; i <= 2025L; i++) {
			yearList.add(i);
		}
		return yearList;
	}

	public static List<Long> getMonthList() {
		List<Long> monthList = new ArrayList<Long>();
		for (Long i = 1L; i <= 12L; i++) {
			monthList.add(i);
		}
		return monthList;
	}
	
}// end of class

