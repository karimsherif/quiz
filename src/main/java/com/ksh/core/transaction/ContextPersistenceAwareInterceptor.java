package com.ksh.core.transaction;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ksh.core.BaseObject;
import com.ksh.core.util.ThreadLocalUserSessionUtils;

@ContextPersistenceAware
@Interceptor
public class ContextPersistenceAwareInterceptor extends BaseObject implements Serializable {

    private static final long serialVersionUID = -1157644660909912899L;

    protected final static EntityManagerFactory EMF;

    static {
	EMF = Persistence.createEntityManagerFactory("eesPU");
    }

    @AroundInvoke
    public Object managePersistenceContext(InvocationContext context) throws Throwable {
	EntityManager entityManager = ThreadLocalUserSessionUtils.getEntityManager();
	boolean isEntityManagerOpened = false;
	if (entityManager == null) {
	    entityManager = EMF.createEntityManager();// entityManager.isOpen();
	    ThreadLocalUserSessionUtils.setEntityManager(entityManager);
	    isEntityManagerOpened = true;
	}
	Object result = null;
	try {
	    result = context.proceed();
	} catch (Exception e) {
//	    logger.error(e.getMessage());

	    e.printStackTrace();
	    throw e;
	    // return result;
	} finally {
	    if (isEntityManagerOpened) {
		entityManager.close();
		ThreadLocalUserSessionUtils.setEntityManager(null);
	    }
	}
	return result;
    }
}
