package org.elis.dao;


import javax.management.InstanceAlreadyExistsException;

import org.elis.jdbc.JdbcDaoFactory;
import org.elis.jdbc.UtenteDaoJDBC;
import org.elis.jpa.DaoFactoryJpa;

public abstract class DaoFactory {
	
	private static DaoFactory instance;
	
	public abstract UtenteDao getUtenteDao();
	
	public abstract GiocoDao getGiocoDao();
	
	public abstract LibreriaDao getLibreriaDao();
	
	public abstract GenereDao getGenereDao();
	
	public abstract OffertaDao getOffertaDao();
	
	
	public static DaoFactory getDaoFactory(String s) {
		
		
		
		if(instance == null) {
			
			switch(s) {
			
			case"JDBC":
				instance = new JdbcDaoFactory();
				break;
			case "JPA":
				instance = new DaoFactoryJpa();
			}
		}
		return instance;
	}
}
