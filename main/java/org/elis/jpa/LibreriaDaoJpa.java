package org.elis.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.LibreriaDao;
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
		Libreria l = null;
		LocalDateTime now = LocalDateTime.now();
		try {
			l = em.find(Libreria.class, id);
	    	 if (l == null) {
	             System.out.println("Libreria non trovata per l'ID: " + id);
	             return null;
	         }
	    	 
	    	 em.getTransaction().begin();
	    	 l.setNome(nome);
	    	 l.setData_ultima_modifica(now);
	    	 em.getTransaction().commit();
		}catch (Exception e) {
	    	if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback();
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
		Libreria l = null;
		try {
			l = em.find(Libreria.class, id);
			if (l != null) {
	            em.getTransaction().begin();
	            em.remove(l);
	            em.getTransaction().commit();
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
}
