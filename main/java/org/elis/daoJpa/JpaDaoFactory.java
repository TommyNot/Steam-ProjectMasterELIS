package org.elis.daoJpa;

import org.elis.jpa.DaoFactoryJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public abstract class JpaDaoFactory extends DaoFactoryJpa {
	
	
	protected static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("SteamProject").createEntityManager();
	}
	
	public abstract JpaUtenteDao getJpaUtenteDao();
	public static DaoFactoryJpa getDaoFactoryJpa() {
		return new DaoFactoryJpa();
	}
	
	
}
