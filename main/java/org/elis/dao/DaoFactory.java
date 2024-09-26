package org.elis.dao;


import org.elis.jdbc.JdbcDaoFactory;
import org.elis.jdbc.UtenteDaoJDBC;

public abstract class DaoFactory {
	
	
	public abstract UtenteDao getUtenteDao();

	
	public static JdbcDaoFactory getDaoFactory() {
		
		return new JdbcDaoFactory();
	}
}
