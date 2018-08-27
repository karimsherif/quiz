package com.ksh.core.transaction;

import java.io.Serializable;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ksh.core.util.ThreadLocalUserSessionUtils;

@Transactional
@Interceptor
public class TransactionalInterceptor implements Serializable {

	private static final long serialVersionUID = 1889073600432817015L;

	@AroundInvoke
	public Object manageTransaction(InvocationContext context) throws Throwable {
		Object result = null;
		EntityManager entityManager = ThreadLocalUserSessionUtils
				.getEntityManager();
		if (entityManager != null) {
			EntityTransaction transaction = null;
			boolean isTransactionActive = false;
			// for Transaction Propagation
			try {
				transaction = ThreadLocalUserSessionUtils.getEntityManager()
						.getTransaction();
				if (transaction == null || !transaction.isActive()) {
					transaction = entityManager.getTransaction();
					transaction.begin();
					isTransactionActive = true;
				}
				result = context.proceed();
				if (isTransactionActive) {
					transaction.commit();
				}
			} catch (Throwable t) {
				if (isTransactionActive) {
					try {
						transaction.rollback();
					} catch (Exception e) {
						e.printStackTrace();
						// logger.error("Exception TransactionalInterceptor , transaction.rollback()");
					}
				}
				throw t;
			}
		} else {
			result = context.proceed();
		}
		return result;
	}
}
