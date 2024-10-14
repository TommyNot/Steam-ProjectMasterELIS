package org.elis.jpa;

import java.util.List;

import org.elis.dao.GenereDao;
import org.elis.model.Genere;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;

public class GenereDaoJpa implements GenereDao{
	
	private static GenereDaoJpa instance;
	
	private GenereDaoJpa() {
		
		
	}
	
	public static GenereDaoJpa getInstance() {
		if(instance == null) {
			instance = new GenereDaoJpa();
		}
		return instance;
	}

	@Override
	public Genere add(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genere> findAll() {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Query q = em.createQuery("select a from Genere a");

	    return q.getResultList();
	}

	@Override
	public Genere findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere deleteByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
