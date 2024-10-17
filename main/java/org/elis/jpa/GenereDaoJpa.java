package org.elis.jpa;

import java.util.List;

import org.elis.dao.GenereDao;
import org.elis.model.Genere;
import org.elis.model.Gioco;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
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
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    try {
	        
	        Query query = em.createQuery("SELECT g FROM Genere g WHERE g.nome = :nome");
	        query.setParameter("nome", nome);
	        return  (Genere) query.getSingleResult();
	    } catch (NoResultException e) {
	        e.printStackTrace();
	        return null;
	    } 
	}


	@Override
	public Genere selectById(long id) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    try {
	        
	        return em.find(Genere.class, id);
	    }catch(NoResultException e) {
	    	
	    	
	    	e.printStackTrace();
	    }
		return null;
	}


	@Override
	public Genere deleteByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere aggiungiGiocoaGnere(long idGenere, long idGioco) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		Genere genere = em.find(Genere.class,idGenere);
		
		Gioco g = em.find(Gioco.class, idGioco);
		
       // genere.getGiochi().add(g);
        g.getGenereGiochi().add(genere);
        
       // em.persist(g);
        t.commit();
        em.close();
        
        
       
		return genere;
	}

}
