package org.elis.jpa;

import java.util.List;

import org.elis.daoJpa.JpaDaoFactory;
import org.elis.daoJpa.JpaUtenteDao;
import org.elis.model.Utente;



import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class UtenteDaoJpa implements JpaUtenteDao {
	
	private static UtenteDaoJpa instance;
	
	private UtenteDaoJpa() {
		
	}
	
	public static UtenteDaoJpa getInstance() {
		if(instance == null) {
			instance = new UtenteDaoJpa();
		}
		return instance;
	}

	@Override
	public List<Utente> getAll() {
		EntityManager em = JpaDaoFactory.getEntityManager();
		Query q = em.createQuery("select a from utente a");
		return q.getResultList();
	}
}
