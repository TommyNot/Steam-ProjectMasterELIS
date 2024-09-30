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

public static Gioco GiocoAdd(String nome, LocalDateTime dataRilascio, String descrizione, String immagine, double prezzo,List<Genere> genere, Offerta offerta,long idUtente) {
	

	return DaoFactory.getDaoFactory().getGiocoDao().add(nome,dataRilascio,descrizione,immagine,prezzo,genere,offerta,idUtente);

}

public static List<Gioco> VisualizzaTuttiGiochi() {
	
	return DaoFactory.getDaoFactory().getGiocoDao().findAll();
}

public static Gioco TrovaByName(String nome) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().findByName(nome);
}

public static Genere GenereAdd(String nome) {
	return DaoFactory.getDaoFactory().getGenereDao().add(nome);
}

public static List<Genere> VisalizzaTuttiGeneri(){
	return DaoFactory.getDaoFactory().getGenereDao().findAll();
}

public static Genere SelectByName(String nome) {
	return DaoFactory.getDaoFactory().getGenereDao().findByName(nome);
}


public static Genere getGenereById(long idGenere) {
	
	return DaoFactory.getDaoFactory().getGenereDao().selectById(idGenere);
}

public static Genere DeleteByName(String nome) {
	return DaoFactory.getDaoFactory().getGenereDao().deleteByName(nome);
}

public static Offerta offertaAdd(String nome, double sconto, LocalDateTime data_inizio, LocalDateTime data_fine) {
	return DaoFactory.getDaoFactory().getOffertaDao().add(nome, sconto, data_inizio, data_fine);
	
}

public static List<Offerta> offertaVisualizzaTutto() {
	return DaoFactory.getDaoFactory().getOffertaDao().findAll();
}

public static Offerta updatePrezzoOfferta(long id,double prezzo) {
	return DaoFactory.getDaoFactory().getOffertaDao().updatePrezzo(id, prezzo);
}

public static Offerta updateDataInizioOfferta(long id,LocalDateTime data_inizio) {
	return DaoFactory.getDaoFactory().getOffertaDao().updateDataInizio(id, data_inizio);
}

public static Offerta updateDataFineOfferta(long id,LocalDateTime data_fine) {
	return DaoFactory.getDaoFactory().getOffertaDao().updateDataFine(id, data_fine);
}

public static Offerta deleteByNameOfferta(String username,long id) {
	return DaoFactory.getDaoFactory().getOffertaDao().deleteByNome(username, id);
}

}
