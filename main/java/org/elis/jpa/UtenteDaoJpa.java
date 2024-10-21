package org.elis.jpa;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.UtenteDao;
import org.elis.model.Recensione;
import org.elis.model.Ruolo;
import org.elis.model.Utente;



import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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
		EntityManager em = DaoFactoryJpa.getEntityManager();

	    try {
	        String select = "SELECT u FROM Utente u WHERE u.email = :email AND u.password = :password";
	        TypedQuery<Utente> query = em.createQuery(select, Utente.class);
	        
	        query.setParameter("email", email);
	        query.setParameter("password", password);
	        
	        Utente utente = query.getSingleResult();

	        System.out.println("Utente trovato: " + utente.getEmail());
	        return utente;

	    } catch (NoResultException e) {
	        System.out.println("Email o password errati.");
	        return null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    } finally {
	        em.close();
	    }
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
		    LocalDateTime now = LocalDateTime.now(); 
		    
		    try {
		    	utente = em.find(Utente.class, id);
		    	 if (utente == null) {
		             System.out.println("Utente non trovato per l'ID: " + id);
		             return null;
		         }
		    	 
		    	 em.getTransaction().begin();
		    	 utente.setUsername(username);
		    	 utente.setData_modifica(now);
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
		 EntityManager em = DaoFactoryJpa.getEntityManager();
		    Utente utente = null;
		    LocalDateTime now = LocalDateTime.now(); 
		    
		    try {
		    	utente = em.find(Utente.class, id);
		    	 if (utente == null) {
		             System.out.println("Utente non trovato per l'ID: " + id);
		             return null;
		         }
		    	 
		    	 em.getTransaction().begin();
		    	 utente.setEmail(email);
		    	 utente.setData_modifica(now);
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
	public Utente updatePassword(long id, String passwordConferma) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
		    Utente utente = null;
		    LocalDateTime now = LocalDateTime.now(); 
		    
		    try {
		    	utente = em.find(Utente.class, id);
		    	 if (utente == null) {
		             System.out.println("Utente non trovato per l'ID: " + id);
		             return null;
		         }
		    	 
		    	 em.getTransaction().begin();
		    	 utente.setPassword(passwordConferma);
		    	 utente.setData_modifica(now);
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
	public Utente deleteByPassword(long id, String password) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
	        EntityTransaction transaction = em.getTransaction();

	        try {
	            transaction.begin();

	            Utente utente = em.find(Utente.class, id);
	            if (utente == null) {
	                System.out.println("Utente non trovato con ID: " + id);
	                return null;
	            }

	            if (!utente.getPassword().equals(password)) {
	                System.out.println("Password non corretta per l'utente ID: " + id);
	                return null;
	            }

	            em.remove(utente);
	            transaction.commit();

	            System.out.println("Utente con ID " + id + " eliminato con successo.");
	            return utente;

	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return null;

	        } finally {
	            em.close();
	        }
	    }
            

	@Override
	public Utente deleteByNome(long id, String username) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
	        EntityTransaction transaction = em.getTransaction();
	        Utente utente=null;
	        
	        try {
	            transaction.begin();

	            utente = em.find(Utente.class, id);
	            if (utente == null) {
	                System.out.println("Utente non trovato con ID: " + id);
	                return null;
	            }

	            if (!utente.getUsername().equals(username)) {
	                System.out.println("Nome utente non corretto per l'utente ID: " + id);
	                return null;
	            }
	            if (utente != null) {
		        	Query q = em.createQuery("SELECT r FROM Recensione r WHERE r.gioco.id = :giocoId ");
		        	q.setParameter("utenteId", id);
		        	
		        	List<Recensione> recensioni = q.getResultList();
		        	
		        	for(Recensione rec : recensioni) {
		        		
		        	em.remove(rec);
		        	}
		        	
	            em.remove(utente);
	            transaction.commit(); 
	            System.out.println("Utente con ID " + id + " eliminato con successo.");
		        	}
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace();
	            return null;

	        } finally {
	            em.close();
	        }
			return utente;
   }

	@Override
	public Utente selectById(long id) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
		    Query q = em.createQuery("Select a from Utente a Where a.id = :id");
		    q.setParameter("id", id);
		    try {
		        return (Utente) q.getSingleResult();
		    } catch (NoResultException e) {
		        return null;
		    }
		}

	@Override
	public Utente ripristinaPassword(String username, String email, String password) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Utente utente = null;

        try {
            transaction.begin();
            
            Query query = em.createQuery("SELECT u FROM Utente u WHERE u.username = :username AND u.email = :email");
            query.setParameter("username", username);
            query.setParameter("email", email);
            utente = (Utente) query.getSingleResult();

            if (utente != null) {
                utente.setPassword(password);
                em.merge(utente);
            }

            transaction.commit();
            System.out.println("Password ripristinata con successo per l'utente: " + username);
        } catch (jakarta.persistence.NoResultException e) {
            System.out.println("Nessun utente trovato con le credenziali fornite.");
            
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            em.close();
        }

        return utente;
    }
		
}
