package com.ksh.common;

import java.io.Serializable;

/*
 * @author Karim Sherif
 */
public class Constant implements Serializable {

    private static final long serialVersionUID = 4226958284981768474L;

    public Constant() {
    }
    public static final String RESOURCES_IMAGES_PATH = "/resources/images";
    public static final String DEFAULT_THEME="redmond" ;//"redmond";//bootstrap
    public static final int BUFFER_SIZE = 1024;
    ///-----mail setting-----///
    public static final String SMTP_HOST_NAME = "smtp.gmail.com";
    public static final int SMTP_HOST_PORT = 465;
    public static final String SMTP_AUTH_USER = "test562288@gmail.com";
    public static final String SMTP_AUTH_PWD  = "k374582666";
    public static final String PROTOCOL  = "smtps";
    public static final String AUTH  = "true";
    ///-----Access setting-----///
    public static final Long ACCESS  = 1L;
    public static final Long NO_ACCESS  = 2L;
    public static final String DEFAULT_LOCALE  = "ar";
    public static final String DEFAULT_DIRECTION  = "rtl";
    ///-----Access setting-----///
    public static final int MAX_RESULTS=20;
    
    public static final String UNDEFINED="un defined";
    public static final Long UNDEFINED_VALUE=-1L;
    public static final String PORTAL="portal";
    public static final String WS="ws";
    public static final String MIGRATION="migration";
    


	public static final String RESOURCE_BUNDLE_KEY = "msgs";

	public static final String JNDI_CONTEXT_NAME_TOMCAT = "java:comp/env/"; // Tomcat

//	public static final String JNDI_CONTEXT_NAME_JBOSS = "java:comp/"; // JBOSS
	public static final String JNDI_CONTEXT_NAME_JBOSS = "java:global/"; // JBOSS

	public static final Integer APPLICATION_SERVER = 2; // JBOSS 1 / TOMCAT 2
    
}
