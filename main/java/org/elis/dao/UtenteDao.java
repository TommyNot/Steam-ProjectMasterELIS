package org.elis.dao;


import java.util.List;

import org.elis.model.Ruolo;
import org.elis.model.Utente;

public interface UtenteDao {
	
	Utente add(int ruolo , String username,String email, String password);
	List<Utente> findAll();
	Utente findByName(String username);
	Utente updateUsername(long id,String username);
	Utente updateEmail(long id,String email);
	Utente updatePassword(long id,String password);
	Utente deleteByName(String email,String password);
	Utente selectById(long id);
	

}
