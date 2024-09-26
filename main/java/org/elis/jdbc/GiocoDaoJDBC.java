package org.elis.jdbc;

import java.util.List;

import org.elis.dao.GiocoDao;
import org.elis.model.Gioco;

public class GiocoDaoJDBC implements GiocoDao{
	
    private static GiocoDaoJDBC instance;
    
    
    private GiocoDaoJDBC() {}
    
   
    public static GiocoDaoJDBC getInstance() {
        if (instance == null) {
            instance = new GiocoDaoJDBC();
        }
        return instance;
    }


	@Override
	public Gioco add() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gioco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco findByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGioco() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco deleteGioco() {
		// TODO Auto-generated method stub
		return null;
	}

}
