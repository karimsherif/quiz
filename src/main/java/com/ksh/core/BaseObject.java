/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksh.core;

import java.io.Serializable;

//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;

import com.ksh.core.util.Utils;

/**
 * 
 * @author mohammad
 */
public class BaseObject implements Serializable, Cloneable {


//	protected static Logger logger; // log all Tanciqia messages.


	private static final String LOG_PROPERTIES_FILE = "log4j.properties";
//	static {
//		PropertyConfigurator.configure(LOG_PROPERTIES_FILE);
//	}

	public BaseObject() {
//		logger = Logger.getLogger("KSHLogger");
	}

//	public static Logger getKSHLogger() {
//		if (Utils.isEmpty(logger))
//			logger = Logger.getLogger("KSHLogger");
//		return logger;
//	}
}
