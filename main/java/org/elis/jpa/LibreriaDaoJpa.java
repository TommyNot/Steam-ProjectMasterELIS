package org.elis.jpa;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.LibreriaDao;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Libreria;
import org.elis.model.Utente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class LibreriaDaoJpa implements LibreriaDao {
	private static LibreriaDaoJpa instance;
	
	private LibreriaDaoJpa() {
		
	}
	
	public static LibreriaDaoJpa getInstance() {
		if(instance == null) {
			instance = new LibreriaDaoJpa();
		} 
		return instance;
	}

	@Override
	public Libreria add(Libreria l) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();;
		em.persist(l);
		t.commit();
		return l;
	}

	@Override
	public List<Libreria> findAll() {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from Libreria a");
		return q.getResultList();
	}

	@Override
	public Libreria findByName(String nome) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from Libreria a where a.nome=:nome");
		q.setParameter("nome", nome);
		try {
			return (Libreria)q.getSingleResult();
		}catch (NoResultException e) {
	        return null;
	    }
	}

	@Override
	public List<Libreria> findByIdUtente(long id_utente) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from Libreria a where a.libreriaUtente.id=:id_utente");
		q.setParameter("id_utente", id_utente);
		try {
			return q.getResultList();
		}catch (NoResultException e) {
	        e.printStackTrace();
	        return null;  
	    }
	}

	@Override
	public Libreria updateNome(long id, String nome) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		Libreria l = null;
		LocalDateTime now = LocalDateTime.now();
		try {
			l = em.find(Libreria.class, id);
	    	 if (l == null) {
	             System.out.println("Libreria non trovata per l'ID: " + id);
	         }
	    	 
	    	 t.begin();
	    	 l.setNome(nome);
	    	 l.setData_ultima_modifica(now);
	    	 t.commit();
		}catch (Exception e) {
	    	if (t.isActive()) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    	 em.close();
	    }
	    return l;
	}
	
	@Override
	public Libreria deleteById(long id) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		Libreria l = null;
		try {
			l = em.find(Libreria.class, id);
			if (l != null) {
	            t.begin();
	            em.remove(l);
	            t.commit();
	        } else {
	            System.out.println("Libreria non trovata con ID: " + id);
	        }

	    } catch (NoResultException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	    
	        e.printStackTrace();
	    } 

	    return l;
		}

	@Override
	public List<Gioco> findGiochiByIdLibreria(long id_libreria) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select gioco from Gioco gioco join gioco.libreriaGiochi libreria where libreria.id=:id_libreria");
		q.setParameter("id_libreria", id_libreria);
		try {
			return q.getResultList();
		}catch (NoResultException e) {
	        e.printStackTrace();
	        return null;  
	    }
	}
	
	@Override
	public Libreria aggiungiGiocoALibreria(long id_libreria, long id_gioco) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		
		t.begin();
		
		Libreria libreria = em.find(Libreria.class,id_libreria);
		
		Gioco g = em.find(Gioco.class, id_gioco);
		
       
		g.getLibreriaGiochi().add(libreria);        
       
        t.commit();
        em.close();
        
        
       
		return libreria;
	}


	
	
	
}
