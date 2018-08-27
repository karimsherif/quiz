package com.ksh.core.managedBean;

/*
 * @author Karim Sherif
 * karimsherif@
 */
import java.io.Serializable;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ksh.common.Constant;
import com.ksh.core.BaseObject;

public class BaseBean extends BaseObject implements Serializable {

	private static final long serialVersionUID = 465004318934748257L;

	public BaseBean() {
	}

	public static ExternalContext getExternalContext() {
		return (ExternalContext) FacesContext.getCurrentInstance().getExternalContext();
	}

	public static HttpServletResponse getResonse() {
		return (HttpServletResponse) getExternalContext().getResponse();
	}

	public static HttpServletRequest getRequest() {
		return (HttpServletRequest) getExternalContext().getRequest();
	}

	public static ServletContext getServletContext() {
		return getSession().getServletContext();
	}

	public static HttpSession getSession() {
		return (HttpSession) getExternalContext().getSession(true);
	}

	public static boolean isErrorMessagesEmpty() {
		return !FacesContext.getCurrentInstance().getMessages().hasNext();
	}

	// public static Object getApplicationAttribute(String value) {
	// FacesContext facesContext = FacesContext.getCurrentInstance();
	// return
	// FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(facesContext.getELContext(),
	// null, value);
	// }

	public static String getServerPath() {
		HttpServletRequest request = getRequest();
		return new StringBuffer().append("http://").append(request.getServerName()).append(":")
				.append(request.getServerPort()).append(request.getContextPath()).toString();
	}

	public static String getContextPath() {
		return getExternalContext().getRequestContextPath();
	}

	public static String getRealPathOfResourcesImages() {
		return getExternalContext().getRealPath("//resources//images");
	}

	public static String getRealPath(String path) {
		return getExternalContext().getRealPath(path);
	}

	public static String getResource() {

		return getSession().getServletContext().getRealPath(getRequest().getContextPath());
	}

	public static Object getParameter(String key) {

		return getExternalContext().getRequestParameterMap().get(key);
	}

	public static Object removeParameter(String key) {
		return getExternalContext().getRequestParameterMap().remove(key);
	}

	public static boolean isGetRequest() {
		String method = getRequest().getMethod();
		return method != null && method.equals("GET");
	}

	public static boolean isPostRequest() {
		String method = getRequest().getMethod();
		return method != null && method.equals("POST");
	}

	public static Map getMessagesMap() {
		return (Map) getExternalContext().getApplicationMap().get("messages");
	}

	public static String getMessage(String key) {
		String value = "";

		if (ResourceBundle.getBundle("com.ksh.bundle.quiz.messages", new Locale(Constant.DEFAULT_LOCALE))
				.containsKey(key)) {
			value = ResourceBundle.getBundle("com.ksh.bundle.quiz.messages", new Locale(Constant.DEFAULT_LOCALE))
					.getString(key);
		} else if (ResourceBundle
				.getBundle("com.ksh.bundle.administration.messages", new Locale(Constant.DEFAULT_LOCALE))
				.containsKey(key)) {
			value = ResourceBundle
					.getBundle("com.ksh.bundle.administration.messages", new Locale(Constant.DEFAULT_LOCALE))
					.getString(key);
		} else {
			value = key + ":Not Defined";
		}

		// String value =
		// ResourceBundle.getBundle("com.ksh.bundle.administration.messages").
		// getString(key);
		// if (value == null) {
		// value =
		// ResourceBundle.getBundle("com.ksh.bundle.tanciqia.messages").getString(key);
		// } else {
		// value = key + ":Not Defined";
		// }
		return value;
	}

	public static String getAdminMessage(String key) {
		String value = ResourceBundle
				.getBundle("com.ksh.bundle.administration.messages", new Locale(Constant.DEFAULT_LOCALE))
				.getString(key);
		if (value == null) {
			return key + ":Not Defined";
		}
		return value;
	}

	public static String getEESMessage(String key) {
		String value = ResourceBundle.getBundle("com.ksh.bundle.ees.messages", new Locale(Constant.DEFAULT_LOCALE))
				.getString(key);

		if (value == null) {
			return key + ":Not Defined";
		}
		return value;
	}

	public static String getEESMessage(String messageKey, Map<String, String> params) {
		String value;
		String message = getEESMessage(messageKey);
		for (String key : params.keySet()) {
			value = params.get(key);
			message = message.replace("=:" + key, value);

		}
		return message;
	}

	public static String getTanciqiaMessage(String key) {
		String value = ResourceBundle.getBundle("com.ksh.bundle.tanciqia.messages", new Locale(Constant.DEFAULT_LOCALE))
				.getString(key);

		if (value == null) {
			return key + ":Not Defined";
		}
		return value;
	}

	// public static void addMessage(Severity sever, String message) {
	// FacesContext context = FacesContext.getCurrentInstance();
	// context.addMessage("savebutton", new FacesMessage(sever, message, ""));
	// }

	public static String getTanciqiaMessage(String messageKey, Map<String, String> params) {
		String value;
		String message = getTanciqiaMessage(messageKey);
		for (String key : params.keySet()) {
			value = params.get(key);
			message = message.replace("=:" + key, value);

		}
		return message;
	}

	public static void addMessage(Severity sever, String messageKey, Map<String, String> params) {
		String value;
		String message = getTanciqiaMessage(messageKey);
		for (String key : params.keySet()) {
			value = params.get(key);
			message = message.replace("=:" + key, value);

		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(sever, message, ""));
	}

	public static void addErrorMessage(String messageKey, Map<String, String> params) {
		String value;
		String message = getTanciqiaMessage(messageKey);
		if (params != null) {
			for (String key : params.keySet()) {
				value = params.get(key);
				message = message.replace("=:" + key, value);

			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static void addFatalMessage(String messageKey, Map<String, String> params) {
		String value;
		String message = getTanciqiaMessage(messageKey);
		if (params != null) {
			for (String key : params.keySet()) {
				value = params.get(key);
				message = message.replace("=:" + key, value);

			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, ""));
	}

	public static void addInfoMessage(String messageKey, Map<String, String> params) {
		String value;
		String message = getTanciqiaMessage(messageKey);
		if (params != null) {
			for (String key : params.keySet()) {
				value = params.get(key);
				message = message.replace("=:" + key, value);

			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void addWarnMessage(String messageKey, Map<String, String> params) {
		String value;
		String message = getTanciqiaMessage(messageKey);
		if (params != null) {
			for (String key : params.keySet()) {
				value = params.get(key);
				message = message.replace("=:" + key, value);

			}
		}

		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void addErrorMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, ""));
	}

	public static void addFatalMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_FATAL, message, ""));
	}

	public static void addInfoMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_INFO, message, ""));
	}

	public static void addWarnMessage(String message) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage("", new FacesMessage(FacesMessage.SEVERITY_WARN, message, ""));
	}

	public static void setRequestAttribute(String sharingName, Object object) {
		getRequest().setAttribute(sharingName, object);
	}

	public static Object getRequestAttribute(String name) {
		return getRequest().getAttribute(name);
	}

	public static void setSessionAttribute(String sharingName, Object object) {
		getSession().setAttribute(sharingName, object);
	}

	public static Object getSessionAttribute(String name) {
		return getSession().getAttribute(name);
	}

	public static void removeSessionAttribute(String name) {
		getSession().removeAttribute(name);
	}

	public static void setFlashAttribute(String key, Object value) {
		getExternalContext().getFlash().put(key, value);
	}

	public static void removeFlashAttribute(Object key) {
		getExternalContext().getFlash().remove(key);
	}

	public static Object getFlashAttribute(Object key) {
		return getExternalContext().getFlash().get(key);
	}

	public static void setViewAttribute(String key, Object value) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = fctx.getViewRoot();
		viewRoot.getViewMap().put(key, value);

	}

	public static Object getViewAttribute(String key) {
		FacesContext fctx = FacesContext.getCurrentInstance();
		UIViewRoot viewRoot = fctx.getViewRoot();
		return viewRoot.getViewMap().get(key);

	}

	public static void terminateSession() {
		getSession().invalidate();
	}
}
