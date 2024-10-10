package org.elis.daoJpa;

import org.elis.jpa.DaoFactoryJpa;
import org.elis.jpa.UtenteDaoJpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public abstract class JpaDaoFactory extends DaoFactoryJpa {
	
	
	public abstract UtenteDaoJpa getJpaUtenteDao();
	
	
}
