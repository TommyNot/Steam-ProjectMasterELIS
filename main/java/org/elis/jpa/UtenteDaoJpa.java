package org.elis.jpa;

import java.util.List;

import org.elis.dao.UtenteDao;
import org.elis.model.Utente;



import jakarta.persistence.EntityManager;
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

	@Override
<<<<<<< Updated upstream
	public List<Utente> getAll() {
		EntityManager em = JpaDaoFactory.getEntityManager();
		Query q = em.createQuery("select a from Utente a");
=======
	public List<Utente> findAll() {
		EntityManager em = DaoFactoryJpa.getEntityManager();
		Query q = em.createQuery("select a from utente a");
>>>>>>> Stashed changes
		return q.getResultList();
	}

	@Override
	public Utente add(String username, String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente loginUtente(String email, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente findByName(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Utente updateUsername(long id, String username) {
		// TODO Auto-generated method stub
		return null;
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
