package org.elis.jpa;

import java.util.List;

import org.elis.dao.LibreriaDao;
import org.elis.model.Libreria;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
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
		return (Libreria)q.getSingleResult();
	}

	@Override
	public List<Libreria> findByIdUtente(long id_utente) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from libreria a where a.libreriaUtente_id=:id_utente");
		q.setParameter("id_utente", id_utente);
		return q.getResultList();
	}

	@Override
	public Libreria updateNome(long id, String nome) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		t.begin();
		Libreria l = em.find(Libreria.class, id);
		l.setNome(nome);
		t.commit();
		return l;
	}
	
	@Override
	public void deleteById(long id) {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		EntityTransaction t = em.getTransaction();
		Libreria l = em.find(Libreria.class, id);
		t.begin();
		em.remove(l);
		t.commit();
	}
	
	
}
