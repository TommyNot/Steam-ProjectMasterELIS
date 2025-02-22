package org.elis.jpa;

import org.elis.dao.DaoFactory;
import org.elis.dao.GenereDao;
import org.elis.dao.GiocoDao;
import org.elis.dao.LibreriaDao;
import org.elis.dao.OffertaDao;
import org.elis.dao.RecensioneDao;
import org.elis.dao.UtenteDao;
import org.elis.jdbc.UtenteDaoJDBC;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class DaoFactoryJpa extends DaoFactory{
	
	private static EntityManagerFactory entityManagerFactory;

	//creo connessione con db
	protected static EntityManager getEntityManager() {
		if(entityManagerFactory == null)
		{
			entityManagerFactory = Persistence.createEntityManagerFactory("SteamProject");
		}
		
		return entityManagerFactory.createEntityManager();
	}
	

	@Override
	public UtenteDao getUtenteDao() {
		
		return UtenteDaoJpa.getInstance();
	}

	@Override
	public GiocoDao getGiocoDao() {
		// TODO Auto-generated method stub
		return GiocoDaoJpa.getInstance();
	}

	@Override
	public LibreriaDao getLibreriaDao() {
		
		return LibreriaDaoJpa.getInstance();
	}

	@Override
	public GenereDao getGenereDao() {
		// TODO Auto-generated method stub
		return GenereDaoJpa.getInstance();
	}

	@Override
	public OffertaDao getOffertaDao() {
		// TODO Auto-generated method stub
		return OffertaDaoJpa.getInstance();
	}


	@Override
	public RecensioneDao getRecensioneDao() {
		// TODO Auto-generated method stub
		return RecensioneDaoJpa.getInstance();
	}
	
}
