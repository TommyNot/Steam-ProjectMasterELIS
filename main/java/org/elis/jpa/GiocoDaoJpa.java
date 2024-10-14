package org.elis.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.GiocoDao;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
		Query q = em.createQuery("select a from Utente a");

		return q.getResultList();
	}

	@Override
	public Gioco findByName(String nome) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q=em.createQuery("Select a from Utente a Where a.username=:username");
		q.setParameter("username", nome);
		return (Gioco) q.getSingleResult();
	}

	@Override
	public Gioco findGiocoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gioco> findGiocoGenereByGenere(long idGenere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gioco> findGiocoOffertaByOfferta(Offerta offerta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gioco> VisualizzaGiochiPerUtente(long idUtente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoNome(long id, String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoDataRilascio(long id, LocalDateTime data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoDescrzione(long id, String descrzione) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoImmagine(long id, String immagine) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoPrezzo(long id, double prezzo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoOfferta(long id, Offerta offerta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGiocoGenere(long id, Genere genere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco deleteGioco(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
