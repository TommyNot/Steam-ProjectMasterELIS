package org.elis.jpa;

import java.util.List;

import org.elis.dao.UtenteDao;
import org.elis.model.Ruolo;
import org.elis.model.Utente;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class UtenteDaoJpa implements UtenteDao {
	
	private static UtenteDaoJpa instance;
	
	private UtenteDaoJpa() {
		
	}
	
	public static UtenteDaoJpa getInstance() {
		if(instance == null) {
			instance = new UtenteDaoJpa();
		}
		return instance;
	}


	public List<Utente> findAll() {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from Utente a");

		return q.getResultList();
	}

	@Override
	public Utente add(Utente u) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(u);
		t.commit();
		return u;
	
	}

	@Override
	public Utente loginUtente(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByName(String username){
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q=em.createQuery("Select a from Utente a Where a.username=:username");
		q.setParameter("username", username);
		 try {
		        return (Utente) q.getSingleResult();
		    } catch (NoResultException e) {
		        return null;
		    }
	}

	@Override
	public Utente updateUsername(long id, String username) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
		    Utente utente = null;
		    try {
		    	utente = em.find(Utente.class, id);
		    	 if (utente == null) {
		             System.out.println("Utente non trovato per l'ID: " + id);
		             return null;
		         }
		    	 
		    	 em.getTransaction().begin();
		    	 utente.setUsername(username);
		    	 em.getTransaction().commit(); 
		    	 
		    } catch (Exception e) {
		    	if (em.getTransaction().isActive()) {
		            em.getTransaction().rollback();
		        }
		        e.printStackTrace();
		    } finally {
		    	 em.close();
		    }
		    return utente;
	}
	
	@Override
	public Utente updateEmail(long id, String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente updatePassword(long id, String passwordVecchia) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente deleteByPassword(long id, String passowrd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente deleteByNome(long id, String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente ripristinaPassword(String username, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}
}
