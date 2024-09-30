package org.elis.businesslogic;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.model.Genere;
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

public static Utente UtenteDeletByEmail(long id,String email) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().deleteByName(id,email);
}

public static Utente UtenteFindById(long id) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().selectById(id);
}

public static Gioco GiocoAdd(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo,List<Genere> genere, Offerta offerta,long idUtente) {
	

	return DaoFactory.getDaoFactory().getGiocoDao().add(nome,dataRilascio,descrizione,immagine,false,prezzo,genere,offerta,idUtente);

}

public static List<Gioco> VisualizzaTuttiGiochi() {
	
	return DaoFactory.getDaoFactory().getGiocoDao().findAll();
}

public static Gioco TrovaByName(String nome) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().findByName(nome);
}


}
