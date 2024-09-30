package org.elis.jdbc;

import java.util.List;

import org.elis.dao.GenereDao;
import org.elis.model.Genere;

public class GenereDaoJDBC implements GenereDao{

private static GenereDaoJDBC instance;
    
    
    private GenereDaoJDBC() {}
    
   
    public static GenereDaoJDBC getInstance() {
        if (instance == null) {
        	
            instance = new GenereDaoJDBC();
        }
        
        return instance;
    }
	
	@Override
	public Genere add(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Genere> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere selectById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Genere deleteByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

}
