package com.ksh.core.util;

import javax.persistence.EntityManager;

public class ThreadLocalUserSessionUtils {


	private static final String ENTITY_MANAGER = "entityManager";

	public static EntityManager getEntityManager() {
		return (EntityManager) ThreadLocalUserSession.get(ENTITY_MANAGER);
	}

	public static void setEntityManager(EntityManager entityManager) {
		ThreadLocalUserSession.set(ENTITY_MANAGER, entityManager);
	}
}
