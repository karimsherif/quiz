/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksh.filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ksh.common.Constant;

/**
 * 
 * @author Karim Sherif
 */
public class SecurityFilter implements Filter {

	private static final boolean debug = true;
	// The filter configuration object we are associated with. If
	// this value is null, this filter instance is not currently
	// configured.
	private FilterConfig filterConfig = null;

	public SecurityFilter() {
	}

	private void doBeforeProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (debug) {
			log("SecurityFilter:DoBeforeProcessing");
		}

	}

	private void doAfterProcessing(ServletRequest request, ServletResponse response)
			throws IOException, ServletException {
		if (debug) {
			log("SecurityFilter:DoAfterProcessing");
		}

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (debug) {
			log("SecurityFilter:doFilter()");
		}

		Throwable problem = null;
		try {
			HttpServletRequest requestt = (HttpServletRequest) request;
			HttpServletResponse responsee = (HttpServletResponse) response;

			// String PathInfo = requestt.getPathInfo();
			String PathInfo = requestt.getServletPath();
			requestt.setCharacterEncoding("UTF-8");
			// System.out.println("PathInfo = " + requestt.getPathInfo());
			// System.out.println("RequestURI = " + requestt.getRequestURI());
			// System.out.println("RequestURL = " + requestt.getRequestURL());
			// System.out.println("ContextPath = " +
			// requestt.getContextPath());
			// System.out.println("LocalAddr = " + requestt.getLocalAddr());
			// System.out.println("LocalName = " + requestt.getLocalName());
			// System.out.println("LocalPort = " + requestt.getLocalPort());
			// System.out.println("RemoteAddr = " + requestt.getRemoteAddr());
			// System.out.println("RemoteHost = " + requestt.getRemoteHost());
			// System.out.println("RemotePort = " + requestt.getRemotePort());
			// System.out.println("ServletPath = " +
			// requestt.getServletPath());
			// System.out.println("Protocol = " + requestt.getProtocol());
			// System.out.println("ServerPort = " + requestt.getServerPort());
			// System.out.println("ServerName = " + requestt.getServerName());

			// System.out.println("---------- local before=" +
			// Locale.getDefault());
			Object registeredUser = requestt.getSession().getAttribute("registeredUser");
//	    Object registeredUser = ((HttpSession)SessionMapping.USER_SESSION_MAP.get(requestt.getSession().getId())).getAttribute("registeredUser");

			if (PathInfo != null && PathInfo.equals("/controlling/login.xhtml")) {
				if (registeredUser == null) {// important to prevent the message

					Locale.setDefault(new Locale(Constant.DEFAULT_LOCALE));

					chain.doFilter(requestt, responsee);
				} else { // important to prevent the message
					// "this page couldn't be restored"
					requestt.getSession().invalidate();
					chain.doFilter(requestt, responsee);
				}

			} else if (PathInfo != null && !PathInfo.equals("/controlling/login.xhtml")
					&& PathInfo.contains("/controlling/")) {
				if (registeredUser == null) {

					Locale.setDefault(new Locale(Constant.DEFAULT_LOCALE));

					responsee.sendRedirect(requestt.getContextPath() + "/controlling/login.xhtml");
				} else {
					if (PathInfo.equals("/controlling/accessDenied.xhtml")) {
						// responsee.sendRedirect(requestt.getContextPath() +
						// "/controlling/accessDenied.xhtml");
						chain.doFilter(requestt, responsee);
					} else
						chain.doFilter(requestt, responsee);
				}
			}
//			else if (PathInfo != null && PathInfo.contains("/quiz/")) {
//				if (registeredUser == null) {
//					Locale.setDefault(new Locale(Constant.DEFAULT_LOCALE));
//					responsee.sendRedirect(requestt.getContextPath() + "/controlling/login.xhtml");
//				} else {
//					if (!checkScreenAuthorization(PathInfo, (RegisteredUser) registeredUser)) {
//						responsee.sendRedirect(requestt.getContextPath() + "/controlling/accessDenied.xhtml");
//					} else {
//						chain.doFilter(requestt, responsee);
//					}
//				}
//			} 
			else {
				chain.doFilter(requestt, responsee);
			}

		} catch (Throwable t) {
			// If an exception is thrown somewhere down the filter chain,
			// we still want to execute our after processing, and then
			// rethrow the problem after that.
			problem = t;
			// t.printStackTrace();
		}

		doAfterProcessing(request, response);

		// If there was a problem, we want to rethrow it if it is
		// a known type, otherwise log it.
		if (problem != null) {
			if (problem instanceof ServletException) {
				throw (ServletException) problem;
			}
			if (problem instanceof IOException) {
				throw (IOException) problem;
			}
			sendProcessingError(problem, response);
		}
	}

	/**
	 * Return the filter configuration object for this filter.
	 */
	public FilterConfig getFilterConfig() {
		return (this.filterConfig);
	}

	/**
	 * Set the filter configuration object for this filter.
	 * 
	 * @param filterConfig The filter configuration object
	 */
	public void setFilterConfig(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	/**
	 * Destroy method for this filter
	 */
	public void destroy() {
	}

	/**
	 * Init method for this filter
	 */
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
		if (filterConfig != null) {
			if (debug) {
				log("SecurityFilter:Initializing filter");
			}
		}
	}

	/**
	 * Return a String representation of this object.
	 */
	@Override
	public String toString() {
		if (filterConfig == null) {
			return ("SecurityFilter()");
		}
		StringBuffer sb = new StringBuffer("SecurityFilter(");
		sb.append(filterConfig);
		sb.append(")");
		return (sb.toString());
	}

	private void sendProcessingError(Throwable t, ServletResponse response) {
		String stackTrace = getStackTrace(t);

		if (stackTrace != null && !stackTrace.equals("")) {
			try {
				response.setContentType("text/html");
				PrintStream ps = new PrintStream(response.getOutputStream());
				PrintWriter pw = new PrintWriter(ps);
				pw.print("<html>\n<head>\n<title>Error</title>\n</head>\n<body>\n"); // NOI18N

				pw.print("<h1>The resource did not process correctly</h1>\n<pre>\n");
				pw.print(stackTrace);
				pw.print("</pre></body>\n</html>"); // NOI18N
				pw.close();
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		} else {
			try {
				PrintStream ps = new PrintStream(response.getOutputStream());
				t.printStackTrace(ps);
				ps.close();
				response.getOutputStream().close();
			} catch (Exception ex) {
			}
		}
	}

	public static String getStackTrace(Throwable t) {
		String stackTrace = null;
		try {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			pw.close();
			sw.close();
			stackTrace = sw.getBuffer().toString();
		} catch (Exception ex) {
		}
		return stackTrace;
	}

	public void log(String msg) {
//	filterConfig.getServletContext().log(msg);
	}

}
