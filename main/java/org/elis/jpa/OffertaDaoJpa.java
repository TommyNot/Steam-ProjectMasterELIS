package org.elis.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.OffertaDao;
import org.elis.model.Libreria;
import org.elis.model.Offerta;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

public class OffertaDaoJpa implements OffertaDao{
	
	private static OffertaDaoJpa instance;
	
	private OffertaDaoJpa() {
		
	}
	
	public static OffertaDaoJpa getInstance() {
		if(instance == null) {
			instance = new OffertaDaoJpa();
		}
		return instance;
	}

	@Override
	public Offerta add(Offerta o) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		em.persist(o);
		t.commit();
		return o;
	}

	@Override
	public List<Offerta> findAll() {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from Offerta a");
		return q.getResultList();
	}

	@Override
	public Offerta selectById(long id) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		return em.find(Offerta.class, id);
	}

	@Override
	public Offerta selectByName(String nome) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		return em.find(Offerta.class, nome);
		
	}

	@Override
	public Offerta updateSconto(long id, double sconto) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Offerta o = em.find(Offerta.class, id);
		o.setSconto(sconto);
		t.commit();
		return o;
	}

	@Override
	public Offerta updateDataInizio(long id, LocalDate data_inizio) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Offerta o = em.find(Offerta.class, id);
		o.setData_inizio(data_inizio);
		t.commit();
		return o;
		
	}

	@Override
	public Offerta updateDataFine(long id, LocalDate data_fine) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Offerta o = em.find(Offerta.class, id);
		o.setData_fine(data_fine);
		t.commit();
		return o;
		
	}

	@Override
	public Offerta deleteByNome(String nome, long id) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		Offerta o = em.find(Offerta.class, nome);
		t.begin();
		em.remove(o);
		t.commit();
		return null;
	}
}
