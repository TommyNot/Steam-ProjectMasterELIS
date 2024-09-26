package org.elis.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

import org.elis.dao.DaoFactory;

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


	








	
	
}
