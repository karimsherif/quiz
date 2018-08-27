package com.ksh.core.util;

import java.util.Set;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class CDIApplicationContext {

    public static final String JAVA_COMP_ENV_BEAN_MANAGER = Util.getJNDIContextName() + "BeanManager";

    public CDIApplicationContext() {

    }

    public static Object getBean(Class<?> clazz) {
	try {
	    BeanManager beanManager = (BeanManager) new InitialContext().lookup(JAVA_COMP_ENV_BEAN_MANAGER);
	    CreationalContext<?> ctx = beanManager.createCreationalContext(null);
	    Set<Bean<?>> beans = beanManager.getBeans(clazz);
	    return beanManager.getReference(beans.iterator().next(), clazz, ctx);
	} catch (NamingException e) {
	    e.printStackTrace();
	}
	return null;
    }

}
