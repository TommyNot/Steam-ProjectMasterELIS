package org.elis.jpa;

import org.elis.dao.DaoFactory;
import org.elis.dao.GenereDao;
import org.elis.dao.GiocoDao;
import org.elis.dao.LibreriaDao;
import org.elis.dao.OffertaDao;
import org.elis.dao.UtenteDao;
import org.elis.jdbc.UtenteDaoJDBC;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;

public class DaoFactoryJpa extends DaoFactory{
	

	//creo connessione con db
	protected static EntityManager getEntityManager() {
		return Persistence.createEntityManagerFactory("SteamProject").createEntityManager();
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
	
}
