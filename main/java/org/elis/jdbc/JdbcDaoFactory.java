package org.elis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.elis.dao.DaoFactory;
import org.elis.dao.GenereDao;
import org.elis.dao.GiocoDao;
import org.elis.dao.LibreriaDao;
import org.elis.dao.OffertaDao;
import org.elis.dao.UtenteDao;




public class JdbcDaoFactory extends DaoFactory{
	
	protected static Connection getConnection() throws Exception{
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/steampezzotto","root","root");
	}


	@Override
	public UtenteDao getUtenteDao() {
		// TODO Auto-generated method stub
		return UtenteDaoJDBC.getInstance();
	}

	@Override
	public GiocoDao getGiocoDao() {
		// TODO Auto-generated method stub
		return GiocoDaoJDBC.getInstance();
	}


	@Override
	public LibreriaDao getLibreriaDao() {
		// TODO Auto-generated method stub
		return LibreriaDaoJDBC.getInstance();
				
	}


	@Override
	public GenereDao getGenereDao() {
		// TODO Auto-generated method stub
		return GenereDaoJDBC.getInstance();
	}


	@Override
	public OffertaDao getOffertaDao() {
		// TODO Auto-generated method stub
		return OffertaDaoJDBC.getInstance();
	}




	








	
	
}
