package org.elis.jdbc;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.OffertaDao;
import org.elis.model.Offerta;

public class OffertaDaoJDBC implements OffertaDao {
	
	private static OffertaDaoJDBC instance;
    
	   
    private OffertaDaoJDBC() {}
    
   
    public static OffertaDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OffertaDaoJDBC();
        }
        return instance;
    }


	@Override
	public Offerta add(String nome, double sconto, LocalDateTime data_inizio, LocalDateTime data_fine) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Offerta> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta updatePrezzo(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta updateDataInizio(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta updateDataFine(long id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Offerta deleteByNome(String nome, long id) {
		// TODO Auto-generated method stub
		return null;
	}
}
