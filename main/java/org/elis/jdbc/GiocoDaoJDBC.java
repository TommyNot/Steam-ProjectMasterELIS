package org.elis.jdbc;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.GiocoDao;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

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
	public Gioco add(String nome,LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo,
			Offerta offerta, Utente utente) {
		
		return null;
	}

	@Override
	public List<Gioco> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco findByName(String nome) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco updateGioco(String nome,LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo,Offerta offerta, Utente utente) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gioco deleteGioco(long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
