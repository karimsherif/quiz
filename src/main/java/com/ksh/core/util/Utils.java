package com.ksh.core.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ksh.common.Constant;

public class Utils {

	public static boolean isNotEmpty(List obj) {
		return obj != null && obj.size() != 0;
	}

	public static boolean isNotEmpty(String obj) {
		return obj != null && obj.length() != 0;
	}

	public static boolean isNotEmpty(Object obj) {
		return obj != null;
	}

	public static Boolean isEmptyString(String str) {
		if (str == null || str.equals(""))
			return true;
		return false;
	}

	public static boolean isNotEmpty(Long obj) {
		return obj != null && obj.toString().length() != 0 && obj > 0;
	}

	public static boolean isEmpty(Long obj) {
		return obj == null || (obj.toString().length() == 0 || obj <= 0);
	}

	public static boolean isEmpty(Double obj) {
		return obj == null || (obj.toString().length() == 0 || obj <= 0);
	}

	public static boolean isNotEmpty(Object[] obj) {
		return obj != null && obj.length != 0;
	}

	public static boolean isEmpty(Object obj) {
		return obj == null;
	}

	public static boolean isEmpty(List obj) {
		return obj == null || obj.size() == 0;
	}

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static double round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return (double) tmp / p;
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

	public static String hex(byte[] array) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < array.length; ++i) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
		}
		return sb.toString();

	}

	public static String md5pwd(String message, String salt) {

		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String msg = new StringBuffer(message).append("{").append(salt).append("}").toString();
			return hex(md.digest(msg.getBytes("UTF-8")));
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
		return null;
	}

	/**
	 * @param Date
	 *            the formated date style is dd/MM/yyyy HH:mm:ss
	 * @return Date as String
	 */
	public static String formatDateTime(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return dateFormat.format(date);
	}
	/**
	 * @param Date
	 *            the formated date style is dd/MM/yyyy
	 * @return String as Date
	 */
	public static Date converteStringToDat(String date) throws ParseException {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
		return format.parse(date);
	}
	/**
	 * @param Date
	 * @return String as Date
	 */
	public static Date converteStringToDatWithPattern(String date,String datePattern) throws ParseException {
		DateFormat format = new SimpleDateFormat(datePattern, Locale.ENGLISH);
		return format.parse(date);
	}

	/**
	 * @param Date
	 *            the formated date style is dd/MM/yyyy HH:mm:ss
	 * @return Date if the entered date was compatible with the format
	 *         dd/MM/yyyy HH:mm:ss other with null
	 */
	public static Date formatDateTimeAsDate(Date date) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			String fd = dateFormat.format(date);
			return dateFormat.parse(fd);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Date getMinDate() {
		DateFormat expensformatterFrom = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return expensformatterFrom.parse("01/01/1900");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Date getMaxDate() {
		DateFormat expensformatterFrom = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return expensformatterFrom.parse("30/12/3000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static List<Date> getDateList(Date date, int numberOfMonthes) {
		List<Date> dateList = new ArrayList<Date>();
		Calendar call = Calendar.getInstance();
		call.setTime(date);
//		call.add(Calendar.MONTH, 1);
		for (int i = 1; i <= numberOfMonthes; i++) {
			call.add(Calendar.MONTH, 1);
			dateList.add(call.getTime());
		}
		return dateList;

	}

	public static Boolean isFormatValid(String reqExpression, String value) {
		Pattern pattern = Pattern.compile(reqExpression);
		Matcher fit = pattern.matcher(value);
		return fit.matches();
	}
	public static String getEESMessage(String key) {
		String value = ResourceBundle.getBundle("com.ksh.bundle.quiz.messages", new Locale(Constant.DEFAULT_LOCALE))
				.getString(key);

		if (value == null) {
			return key + ":Not Defined";
		}
		return value;
	}

}
