package org.elis.daoJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class JpaDaoFactory {
	
	
	protected static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("SteamProject").createEntityManager();
	}
}
