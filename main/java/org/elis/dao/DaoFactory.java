package org.elis.dao;


import org.elis.jdbc.JdbcDaoFactory;
import org.elis.jdbc.UtenteDaoJDBC;

public abstract class DaoFactory {
	
	
	public abstract UtenteDao getUtenteDao();
	
	public abstract GiocoDao getGiocoDao();
	
	public abstract LibreriaDao getLibreriaDao();
	
	public abstract GenereDao getGenereDao();

	
	public static JdbcDaoFactory getDaoFactory() {
		
		return new JdbcDaoFactory();
	}
}
