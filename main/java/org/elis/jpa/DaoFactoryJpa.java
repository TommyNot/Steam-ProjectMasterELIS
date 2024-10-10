package org.elis.jpa;

import org.elis.daoJpa.JpaUtenteDao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DaoFactoryJpa {
	
	public UtenteDaoJpa getJpaUtenteDao() {
		return UtenteDaoJpa.getInstance();
	}
	
	protected static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("SteamProject").createEntityManager();
	}
	
	public static DaoFactoryJpa getDaoFactoryJpa() {
		return new DaoFactoryJpa();
	}
}
