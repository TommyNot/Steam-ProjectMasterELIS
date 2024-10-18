package org.elis.jpa;

import java.util.List;

import org.elis.dao.RecensioneDao;
import org.elis.model.Gioco;
import org.elis.model.Recensione;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class RecensioneDaoJpa implements RecensioneDao {
	
	private static RecensioneDaoJpa instance;
	
	private RecensioneDaoJpa() {
		
	}
	
	public static RecensioneDaoJpa getInstance() {
		if(instance == null) {
			instance = new RecensioneDaoJpa();
		}
		return instance;
	}


	@Override
	public Recensione add(Recensione r) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(r);
		t.commit();
		return r;
		
	}

	@Override
	public List<Recensione> findAll() {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select r from Recensione r");
		return q.getResultList();
	}

	@Override
	public Recensione findRecensioneById(long id) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
	        Recensione r = null;

	        try {
	            r = em.find(Recensione.class, id);

	            if (r != null) {
	                System.out.println("Recensione trovata");
	            } else {
	                System.out.println("Nessuna recensione trovata con ID: " + id);
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            em.close();
	        }

	        return r;
	    }


	@Override
	public List<Recensione> findRecensioneByIdUtente(long idUtente) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    
	    try {
	        Query q = em.createQuery("SELECT r FROM Recensione r WHERE r.recensioneUtente.id = :idUtente");
	        q.setParameter("idUtente", idUtente);
	        return q.getResultList();
	        
	    } catch (NoResultException e) {
	        e.printStackTrace();
	        return null;  
	    }
	}

	@Override
	public List<Recensione> findRecensioneByIdGioco(long idGioco) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    
	    try {
	        Query q = em.createQuery("SELECT r FROM Recensione r WHERE r.idGioco.id = :idGioco");
	        q.setParameter("idGioco", idGioco);
	        return q.getResultList();
	        
	    } catch (NoResultException e) {
	        e.printStackTrace();
	        return null;  
	    }
	}

	@Override
	public Recensione updateVoto(long id, int voto) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
	    Recensione recensione = null;
	    try {
	        em.getTransaction().begin();

	        
	        recensione = em.find(Recensione.class, id);
	        if (recensione != null) {
	            recensione.setVoto(voto); 
	            em.merge(recensione); 
	            System.out.println("Voto aggiornato per la recensione: "+recensione.getVoto());
	        } else {
	            System.out.println("Nessuna recensione trovata con ID: " + id);
	        }

	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    }

	    return recensione;
	}
		
	

	@Override
	public Recensione updateTesto(long id, String testo) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
	    Recensione recensione = null;
	    try {
	        em.getTransaction().begin();

	        
	        recensione = em.find(Recensione.class, id);
	        if (recensione != null) {
	            recensione.setTesto(testo); 
	            em.merge(recensione); 
	            System.out.println("Testo aggiornato per la recensione: "+recensione.getTesto());
	        } else {
	            System.out.println("Nessuna recensione trovata con ID: " + id);
	        }

	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    }

	    return recensione;
	}
	

	@Override
	public Recensione deleteRecensioneById(long id) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Recensione r = null;

	    try {
	        r = em.find(Recensione.class, id);
	        if (r != null) {
	            em.getTransaction().begin();
	            em.remove(r);
	            em.getTransaction().commit();
	        } else {
	            System.out.println("Recensione non trovata con ID: " + id);
	        }

	    } catch (NoResultException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	    
	        e.printStackTrace();
	    } 

	    return r; 
	}
		


}
