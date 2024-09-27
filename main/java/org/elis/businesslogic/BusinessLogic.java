package org.elis.businesslogic;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
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


public static Utente UpdateUsername(long id,String username) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().updateUsername(id,username);
}

public static Utente UtenteDeletByEmail(String email,String Password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().deleteByName(email,Password);
}

public static Gioco GiocoAdd(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo, Offerta offerta) {
	


	return DaoFactory.getDaoFactory().getGiocoDao().add(nome,dataRilascio,descrizione,immagine,false,prezzo,offerta);

}
}
