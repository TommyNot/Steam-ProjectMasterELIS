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
import org.elis.model.Recensione;
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
	    EntityTransaction t = em.getTransaction();
	    Gioco gioco = null;

	    t.begin();

	        
	    gioco = em.find(Gioco.class, id);
	        
	        
	    gioco.setNome(nome); 
	    
	          

	    t.commit(); 
	    em.close();
	   

	    return gioco;
	}


	@Override
	public Gioco updateGiocoDataRilascio(long id, LocalDate data) {
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
	        
	        e.printStackTrace();
	    } 

	    return gioco; 
	}

	@Override
	public Gioco updateGiocoImmagine(long id, byte[] immagine) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    EntityTransaction t = em.getTransaction();
	    Gioco gioco = null;

	    try {
	        t.begin(); 

	        
	        gioco = em.find(Gioco.class, id);
	        if (gioco != null) {
	            gioco.setByteImmagine(immagine);
	            
	            System.out.println("Immagine aggiornata per il gioco: " + gioco.getNome());
	        } else {
	            System.out.println("Nessun gioco trovato con ID: " + id);
	        }

	        t.commit();
	    } catch (Exception e) {
	     
	        e.printStackTrace();
	    } 

	    return gioco;
	}

	@Override
	public Gioco updateGiocoPrezzo(long id, double prezzo) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    
	    EntityTransaction t = em.getTransaction();
	    Gioco gioco = null;

	    t.begin();

	        
	    gioco = em.find(Gioco.class, id);
	        
	        
	    gioco.setPrezzo(prezzo); 
	    
	          

	    t.commit(); 
	    em.close();
	   

	    return gioco;

	   
	}


	@Override
	public Gioco updateGiocoOfferta(long idGioco, Long idOfferta) {  // Long e non long per permettere null
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    EntityTransaction t = em.getTransaction();
	    Gioco gioco = null;

	    try {
	        t.begin();  // Inizia la transazione

	        
	        gioco = em.find(Gioco.class, idGioco);
	        if (gioco == null) {
	            System.out.println("Gioco non trovato con ID: " + idGioco);
	            return null;  // Interrompi se il gioco non viene trovato
	        }

	        
	        if (idOfferta == null) {
	            
	            gioco.setOffertaGioco(null);
	            System.out.println("Offerta rimossa dal gioco");
	        } else {
	            
	            Offerta offerta = em.find(Offerta.class, idOfferta);
	            if (offerta == null) {
	                System.out.println("Offerta non trovata con ID: " + idOfferta);
	                return null; 
	            }

	            
	            gioco.setOffertaGioco(offerta);
	            System.out.println("Offerta aggiornata con successo");
	        }

	        
	        t.commit();  
	    } catch (Exception e) {
	      
	        e.printStackTrace();
	    }
	    return gioco;  
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
	       
	        e.printStackTrace();
	    }

	    return gioco; 
	}


	@Override
	public Gioco deleteGioco(long id) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    EntityTransaction t = em.getTransaction();
	    
	    Gioco gioco = null;

	    try {
	    	t.begin();
	        gioco = em.find(Gioco.class, id);

	        
	        if (gioco != null) {
	        	Query q = em.createQuery("SELECT r FROM Recensione r WHERE r.gioco.id = :giocoId ");
	        	q.setParameter("giocoId", id);
	        	
	        	List<Recensione> recensioni = q.getResultList();
	        	
	        	for(Recensione rec : recensioni) {
	        		
	        		em.remove(rec);
	        	}
	            em.remove(gioco);
	            t.commit();
	            
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

	@Override
	public List<Gioco> VisualizzaGiochiInOfferta() {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		
		Gioco gioco = null;
		
		Query q = em.createQuery("SELECT g FROM Gioco g WHERE g.offertaGioco IS NOT NULL");
		
		return q.getResultList();
	}
	
	@Override
	public List<Gioco> addOffertaToGiochiByGenere(long idGenere, long idOfferta) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
	    EntityTransaction t = em.getTransaction();
	    List<Gioco> giochiOfferta = new ArrayList<>();
	    try {
	        t.begin();

	        Offerta offerta = em.find(Offerta.class, idOfferta);
	        if (offerta == null) {
	            System.out.println("Offerta non trovata con ID: " + idOfferta);
	            return giochiOfferta;
	        }

	        List<Gioco> giochi = em.createQuery(
	            "SELECT g FROM Gioco g JOIN g.genereGiochi gg WHERE gg.id = :idGenere", Gioco.class)
	            .setParameter("idGenere", idGenere)
	            .getResultList();
	        
	        for (Gioco gioco : giochi) {
	            gioco.setOffertaGioco(offerta);
	            em.merge(gioco);
	            giochiOfferta.add(gioco);
	        }

	        t.commit();
	    } catch (Exception e) {
	        if (t.isActive()) {
	            t.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        em.close();
	        
	    }
	    return giochiOfferta;
	}

	@Override
	public List<Gioco> addGiocoOfferta(long idGioco, long idOfferta) {
		
		EntityManager em = DaoFactoryJpa.getEntityManager();
        EntityTransaction t = em.getTransaction();
        List<Gioco> giochi = new ArrayList<>();
        try {
            t.begin();
            Gioco gioco = em.find(Gioco.class, idGioco);
            
            Offerta offerta = em.find(Offerta.class, idOfferta);
            if (offerta == null) {
            	gioco.setOffertaGioco(offerta);
                System.out.println("Offerta non trovata con ID: " + idOfferta);
                return giochi;
            }

           
            
            if (gioco != null) {
                gioco.setOffertaGioco(offerta);
                giochi.add(gioco);
            } else {
                System.out.println("Gioco non trovato con ID: " + idGioco);
                return giochi;
            }
           
             
            t.commit();
        } catch (Exception e) {
           
            e.printStackTrace();
        } 
        return giochi;
	}

	@Override
	public Gioco rimuoviGiocoOfferta(long idGioco) {
	    EntityManager em = DaoFactoryJpa.getEntityManager();
	    EntityTransaction t = em.getTransaction();
	    Gioco gioco = null;

	    try {
	        
	        t.begin(); 

	        
	        gioco = em.find(Gioco.class, idGioco);

	        if (gioco != null) {
	            
	            Offerta offerta = gioco.getOffertaGioco();
	            if (offerta != null) {
	                gioco.setOffertaGioco(null); 
	                 
	            }

	            em.merge(gioco); 
	        }

	        t.commit(); 
	    } catch (Exception e) {
	       
	        e.printStackTrace(); // Stampa l'errore
	    }

	    return gioco; // Restituisci il gioco aggiornato o null se non trovato
	}

}