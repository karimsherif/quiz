package com.ksh.core.transaction;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class EntityManagerClass {

	private static final String ENTITY_MANAGER = "eesPU";

	public static javax.persistence.EntityManager GET_ENTITY_MANAGER;

	{

	}

	public static javax.persistence.EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory(ENTITY_MANAGER);
		return emf;
	}

	public static javax.persistence.EntityManager getEntityManager() {
		// EntityManagerFactory emf =
		// Persistence.createEntityManagerFactory(ENTITY_MANAGER);
		// javax.persistence.EntityManager em =
		// getEntityManagerFactory().createEntityManager();
		if (GET_ENTITY_MANAGER == null) {
			GET_ENTITY_MANAGER = getEntityManagerFactory()
					.createEntityManager();
		}
		return GET_ENTITY_MANAGER;

	}
}
