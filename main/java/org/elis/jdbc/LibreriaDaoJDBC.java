package org.elis.jdbc;

import java.util.List;

import org.elis.dao.LibreriaDao;
import org.elis.model.Libreria;

public class LibreriaDaoJDBC implements LibreriaDao{
	
    private static LibreriaDaoJDBC instance;
    
    
    private LibreriaDaoJDBC() {}
    
   
    public static LibreriaDaoJDBC getInstance() {
        if (instance == null) {
        	
            instance = new LibreriaDaoJDBC();
        }
        
        return instance;
    }


	@Override
	public Libreria add() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Libreria> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Libreria findByName() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Libreria updateNome() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Libreria deleteByName() {
		// TODO Auto-generated method stub
		return null;
	}


}
