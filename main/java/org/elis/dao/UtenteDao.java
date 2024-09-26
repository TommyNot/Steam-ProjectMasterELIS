package org.elis.dao;


import java.util.List;

import org.elis.model.Ruolo;
import org.elis.model.Utente;

public interface UtenteDao {
	
	Utente add(int ruolo , String username,String email, String password);
	List<Utente> findAll();
	Utente findByName(String username);
	Utente update(int id,String username,String email, String password);
	Utente deleteByName(String email,String password);
	

}
