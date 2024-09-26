package org.elis.businesslogic;

import java.util.List;

import org.elis.dao.DaoFactory;

import org.elis.model.Utente;

public class BusinessLogic {
	
public static List<Utente> UtenteFindAll() {
		
		return  DaoFactory.getDaoFactory().getUtenteDao().findAll();
	}


public static Utente UtenteAdd(int ruolo, String username, String email, String password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().add(ruolo,username,email,password);
}
	
public static Utente UtenteFindByName(String username) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().findByName(username);
}


public static Utente UtenteUpdateInfo(int id,String username,String email,String password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().update(id,username, email, password);
}

public static Utente UtenteDeletByEmail(String email,String Password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().deleteByName(email, Password);
}
}
