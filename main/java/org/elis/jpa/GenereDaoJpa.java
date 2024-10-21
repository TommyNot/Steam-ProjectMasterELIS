package org.elis.jpa;

import java.util.ArrayList;
import java.util.List;

import org.elis.dao.GenereDao;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;

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

	@Override
	public List<Genere> addGenereOfferta(long idGenere, long idOfferta) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Genere> generi = new ArrayList<>();
        try {
            t.begin();

            // Trova l'istanza dell'offerta
            Offerta offerta = em.find(Offerta.class, idOfferta);
            if (offerta == null) {
                System.out.println("Offerta non trovata con ID: " + idOfferta);
                return generi;
            }

            Genere genere = em.find(Genere.class, idGenere);
            if (genere != null) {
                genere.setOffertaGenere(offerta);
                em.merge(genere);
            } else {
                System.out.println("Genere non trovato con ID: " + idGenere);
                return generi;
            }
            t.commit();

            generi = em.createQuery("SELECT g FROM Genere g WHERE g.offertaGenere.id = :idOfferta", Genere.class)
                       .setParameter("idOfferta", idOfferta)
                       .getResultList();
        } catch (Exception e) {
            if (t.isActive()) {
                t.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
        return generi;
	}

	@Override
	public List<Genere> removeGenereOfferta(long idGenere, long idOfferta) {
		
		 EntityManager em = DaoFactoryJpa.getEntityManager();
		 
	    try {
	        em.getTransaction().begin();
	        
	        Query q = em.createQuery("UPDATE Genere g SET g.genereOfferta.id = :idOfferta WHERE g.id = :idGenere");
	        q.setParameter("idOfferta", null);
	        q.setParameter("idGenere", idGenere);
	        q.executeUpdate();
	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } 
	    return null;
	}
}


