package com.ksh.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import com.ksh.common.Constant;

public class Util {

	private static ResourceBundle bundle;

	public static final String fileSeparator = "/";

	public static final String backSlash = "\\";

	public static final String spcae = " ";

	public static final String dash = "-";

	public static ResourceBundle getBundle() {
		if (bundle == null) {
			FacesContext context = FacesContext.getCurrentInstance();
			bundle = context.getApplication().getResourceBundle(context, "config");
		}
		return bundle;
	}

	public static String getOSName() {
		return System.getProperty("os.name");
	}

	public static Boolean isWindows() {
		return getOSName().startsWith("Windows");
	}

	public static String getBundleValue(String key) {
		return getBundle().getString(key);
	}

	public static Double round(Double number, int precision) {
		int prec = 10 * precision;
		return Math.floor(number * prec + .5) / prec;
	}

	public static String getJNDIContextName() {
		String result = null;
//		if (isJBossServer()) {
//			result = Constant.JNDI_CONTEXT_NAME_JBOSS;
//		} else if (isTomcatServer()) {
			result = Constant.JNDI_CONTEXT_NAME_TOMCAT;
//		}
		return result;
	}

	public static Boolean isTomcatServer() {
		return getBundleValue("applicationServer").equals("2");// Constants.APPLICATION_SERVER.equals(2);
	}

	public static Boolean isJBossServer() {
		return getBundleValue("applicationServer").equals("1");// Constants.APPLICATION_SERVER.equals(1);
	}

	public static String getResourceFolderPath() {
		return ((String) getJNDIValue("resourcsFolder")) + getFileSeparator();
	}


	public static String getSendMailServer() {
		return ((String) getJNDIValue("mailServer"));
	}

	public static String getSendMailEmail() {
		return ((String) getJNDIValue("mailEmail"));
	}

	public static String getSendMailEmailPassword() {
		return ((String) getJNDIValue("mailEmailPassword"));
	}

	public static String getSendMailPort() {
		return ((String) getJNDIValue("mailPort"));
	}

	public static String getSendMailEmailHeader() {
		return ((String) getJNDIValue("mailHeader"));
	}

	public static String getFileSeparator() {
		return System.getProperty("file.separator");
	}

	public static Object getJNDIValue(String name) {
		String value = null;
		try {
			Context initCtx = new InitialContext();
//			if (isJBossServer()) {
//				value = (String) initCtx.lookup(name); // jboss
				value = (String) initCtx.lookup(Constant.JNDI_CONTEXT_NAME_JBOSS+name); // jboss
//			} else if (isTomcatServer()) {
//				Context envCtx = (Context) initCtx.lookup(Constant.JNDI_CONTEXT_NAME_TOMCAT); // tomcat
//				value = (String) envCtx.lookup(name);
//			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return value;
	}

	public static double round(double Rval, int Rpl) {
		double p = (double) Math.pow(10, Rpl);
		Rval = Rval * p;
		double tmp = Math.round(Rval);
		return (double) tmp / p;
	}

	public static Boolean isStringContains(String text, String innerText) {
		return (text != null && (text.contains(innerText)));
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
	 * format : yyyyMMddHHmmss
	 */
	public static String formatDateTime(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		return dateFormat.format(date);
	}

	/**
	 * format : yyyyMMdd
	 */
	public static String formatDate(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		return dateFormat.format(date);
	}

	/**
	 * format : dd-MMM-yyyy
	 */
	public static String formatDate2(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		return dateFormat.format(date);
	}

	/**
	 * format : dd-MMM-yy
	 */
	public static String formatDate3(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");
		return dateFormat.format(date);
	}

	/**
	 * format : dd/MM/yyyy
	 */
	public static String formatDate4(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}

	/**
	 * format : yyyy/MM/dd
	 */
	public static String formatDate5(Date date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		return dateFormat.format(date);
	}

	public static Boolean validateReqularExpression(String reqExpression, String value) {
		Pattern pattern = Pattern.compile(reqExpression);
		Matcher fit = pattern.matcher(value);
		return fit.matches();
	}

	public static boolean isNumeric(String obj) {
		return obj.matches("(\\d+)");
	}

	public static boolean isIndexExist(List<?> list, int index) {
		try {
			return null != list.get(index);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}
	}

	public static String getFileExtention(String fileName) {
		String fileExt = "";
		Integer index = fileName.lastIndexOf(".");
		if (index > 0) {
			fileExt = fileName.substring(index + 1, fileName.length());
		}
		return fileExt;
	}

	public static Long getUserId() {
		Long userId = 0L;
		try {
			HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(true);
			Object userIdNumber = httpSession.getAttribute("userId");
			userId = (Long) userIdNumber;
		} catch (Exception e) {
			System.out.println("No user's login");
		}
		return userId;
	}

	public static List<Long> convertStringIdListToLongIdList(List<String> stringIdList) {
		List<Long> longIdList = new ArrayList<Long>();
		if (Utils.isNotEmpty(stringIdList)) {
			for (String id : stringIdList) {
				longIdList.add(Long.valueOf(id));
			}
		}
		return longIdList;
	}

	public static String getDDMMSS(Double decimal) {
		String DDMMSS = "";
		if (Utils.isNotEmpty(DDMMSS)) {
			String[] DDMMSSArr = decimal.toString().split("\\.");
			if (Utils.isNotEmpty(DDMMSSArr) && DDMMSSArr.length > 0) {
				DDMMSS = DDMMSSArr[0];
				if (DDMMSSArr.length > 1) {
					double dd = Double.valueOf(DDMMSSArr[1]) / Math.pow(10, DDMMSSArr[1].length());
					decimal = dd * 60D;
					DDMMSSArr = decimal.toString().split("\\.");
					DDMMSS += "-";
					DDMMSS += DDMMSSArr[0];
					if (DDMMSSArr.length > 1) {
						double dd2 = Double.valueOf(DDMMSSArr[1]) / Math.pow(10, DDMMSSArr[1].length());
						decimal = dd2 * 60D;
						DDMMSS += "-";
						DDMMSS += Math.round(decimal);
					}
				}
			}
		}
		return DDMMSS;
	}

	public static String enc(String S) {
		char[] Enc = { 'K', 'S', 'H' };
		if (S == null) {
			return "";
		}
		int j = 0;
		char[] C = S.toCharArray();
		for (int i = 0; i < C.length; i++) {
			C[i] ^= Enc[j];
			j++;
			if (j > 2) {
				j = 0;
			}
			// System.out.print("" + C);
		}
		// System.out.println("Enc mothod");
		return String.valueOf(C);
	}

	public static void saveFile(String fileName, String destination, InputStream inputStream) throws IOException {
		// write the inputStream to a FileOutputStream
		OutputStream out = new FileOutputStream(new File(destination + fileSeparator + fileName));

		int read = 0;
		byte[] bytes = new byte[1024];

		while ((read = inputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}

		inputStream.close();
		out.flush();
		out.close();
//		System.out.println("New file created with Name="+fileName);
	}
}
