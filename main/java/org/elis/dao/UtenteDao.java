package org.elis.dao;


import java.util.List;

import org.elis.model.Ruolo;
import org.elis.model.Utente;

public interface UtenteDao {
	
	Utente add(Utente u);
	Utente loginUtente(String email,String password);
	List<Utente> findAll();
	Utente findByName(String username);
	Utente updateUsername(long id,String username);
	Utente updateEmail(long id,String email);
	Utente updatePassword(long id,String passwordVecchia);
	Utente deleteByPassword(long id,String passowrd);
	Utente deleteByNome(long id,String username);
	Utente selectById(long id);
	Utente ripristinaPassword(String username,String email,String password);
	

}
