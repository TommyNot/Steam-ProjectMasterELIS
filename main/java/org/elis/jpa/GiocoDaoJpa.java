package org.elis.jpa;

import java.nio.channels.NonReadableChannelException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.elis.dao.GiocoDao;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;

public class GiocoDaoJpa implements GiocoDao{
	
	private static GiocoDaoJpa instance;
	
	private GiocoDaoJpa() {
		
		
	}
	
	public static GiocoDaoJpa getInstance() {
		if(instance == null) {
			instance = new GiocoDaoJpa();
		}
		return instance;
	}


	@Override
	public Gioco add(Gioco g) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(g);
		t.commit();
		return g;
	}

	@Override
	public List<Gioco> findAll() {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Query q = em.createQuery("select a from Gioco a");

	    return q.getResultList();
	
	  
	}


	@Override
	public List<Gioco> findByName(String nome) {
	    try {
	        
	    	nome = nome.trim();

	        EntityManager em = DaoFactoryJpa.getEntityManager();
	        
	        
	        Query q = em.createQuery("SELECT g FROM Gioco g WHERE g.nome like :pattern");
	        q.setParameter("pattern", "%"+nome+"%");
	        

	        System.out.println("qua ci siamo arrivati ?");
	        var list = q.getResultList();
	        System.out.println(list);
	        return list;
	    } catch (NoResultException e) {
	        e.printStackTrace();
	        return null;
	    }
	}


	@Override
	public Gioco findGiocoById(long id) {
		 EntityManager em = DaoFactoryJpa.getEntityManager();
	        Gioco gioco = null;

	        try {
	            gioco = em.find(Gioco.class, id);

	            if (gioco != null) {
	                System.out.println("Gioco trovato: " + gioco.getNome());
	            } else {
	                System.out.println("Nessun gioco trovato con ID: " + id);
	                return null;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return gioco;
	    }

	@Override
	public List<Gioco> findGiocoGenereByGenere(long idGenere) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    List<Gioco> resu = null;

	    try {
	        
	    	Query q = em.createQuery("SELECT g FROM Gioco g JOIN g.genereGiochi ge WHERE ge.id = :idGenere");
	    	

	        
	         q.setParameter("idGenere", idGenere);
	          
	         resu = q.getResultList();
	         
	        if (resu.isEmpty()) {
	            System.out.println("Nessun gioco trovato per il genere con ID: " + idGenere);
	        } else {
	            System.out.println(" giochi trovati per il genere con ID: ");
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return resu; 

	    
	}


	@Override
	public List<Gioco> findGiocoOffertaByOfferta(Offerta offerta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gioco> VisualizzaGiochiPerUtente(long idUtente) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    
	    try {
	        Query q = em.createQuery("SELECT g FROM Gioco g WHERE g.idUtente.id = :idUtente");
	        q.setParameter("idUtente", idUtente);
	        return q.getResultList();
	        
	    } catch (NoResultException e) {
	        e.printStackTrace();
	        return null;  // Ritorna una lista vuota se non ci sono risultati
	    }
	    
	}


	@Override
	public Gioco updateGiocoNome(long id, String nome) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Gioco gioco = null;

	    try {
	        em.getTransaction().begin();

	        
	        gioco = em.find(Gioco.class, id);
	        if (gioco != null) {
	            gioco.setNome(nome); 
	            em.merge(gioco); 
	            System.out.println("Nome aggiornato per il gioco: " + gioco.getNome());
	        } else {
	            System.out.println("Nessun gioco trovato con ID: " + id);
	        }

	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    }

	    return gioco;
	}


	@Override
	public Gioco updateGiocoDataRilascio(long id, LocalDateTime data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoDescrzione(long id, String descrzione) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Gioco gioco = null;

	    try {
	        em.getTransaction().begin(); 

	        // Find the game by its ID
	        gioco = em.find(Gioco.class, id);
	        if (gioco != null) {
	            gioco.setDescrzione(descrzione); 
	            em.merge(gioco); 
	            System.out.println("Descrizione aggiornata per il gioco: " + gioco.getNome());
	        } else {
	            System.out.println("Nessun gioco trovato con ID: " + id);
	        }

	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    } 

	    return gioco; 
	}

	@Override
	public Gioco updateGiocoImmagine(long id, byte[] immagine) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Gioco gioco = null;

	    try {
	        em.getTransaction().begin(); 

	        // Find the game by its ID
	        gioco = em.find(Gioco.class, id);
	        if (gioco != null) {
	            gioco.setByteImmagine(immagine);
	            em.merge(gioco); 
	            System.out.println("Immagine aggiornata per il gioco: " + gioco.getNome());
	        } else {
	            System.out.println("Nessun gioco trovato con ID: " + id);
	        }

	        em.getTransaction().commit();
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    } 

	    return gioco;
	}

	@Override
	public Gioco updateGiocoPrezzo(long id, double prezzo) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Gioco gioco = null;

	    try {
	        em.getTransaction().begin();

	        
	        gioco = em.find(Gioco.class, id);
	        if (gioco != null) {
	            gioco.setPrezzo(prezzo); 
	            em.merge(gioco);
	            System.out.println("Prezzo aggiornato per il gioco: " + gioco.getNome());
	        } else {
	            System.out.println("Nessun gioco trovato con ID: " + id);
	        }

	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    } 

	    return gioco; 
	}


	@Override
	public Gioco updateGiocoOfferta(long id, Offerta offerta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoGenere(long id, Genere genere) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Gioco gioco = null;

	    try {
	        em.getTransaction().begin(); 

	        
	        gioco = em.find(Gioco.class, id);
	        if (gioco != null) {
	            gioco.setGenereGiochi((List<Genere>) genere); 
	            em.merge(gioco); 
	            System.out.println("Genere aggiornato per il gioco: " + gioco.getNome());
	        } else {
	            System.out.println("Nessun gioco trovato con ID: " + id);
	        }

	        em.getTransaction().commit(); 
	    } catch (Exception e) {
	        if (em.getTransaction().isActive()) {
	            em.getTransaction().rollback(); 
	        }
	        e.printStackTrace();
	    }

	    return gioco; 
	}


	@Override
	public Gioco deleteGioco(long id) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    Gioco gioco = null;

	    try {
	        // Trova il gioco per ID
	        gioco = em.find(Gioco.class, id);

	        // Se il gioco esiste, lo eliminaoim 
	        if (gioco != null) {
	            em.getTransaction().begin();
	            em.remove(gioco);
	            em.getTransaction().commit();
	        } else {
	            System.out.println("Gioco non trovato con ID: " + id);
	        }

	    } catch (NoResultException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	    
	        e.printStackTrace();
	    } 

	    return gioco; 
	}


}
