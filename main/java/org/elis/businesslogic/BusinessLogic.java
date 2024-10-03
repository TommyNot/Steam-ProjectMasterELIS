package org.elis.businesslogic;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

public class BusinessLogic {
	
	
public static Utente UtenteLogin(String email,String password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().loginUtente(email, password);
}
	
public static List<Utente> UtenteFindAll() {
		
		return  DaoFactory.getDaoFactory().getUtenteDao().findAll();
	}


public static Utente UtenteAdd(String username, String email, String password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().add(username,email,password);
}
	
public static Utente UtenteFindByName(String username) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().findByName(username);
}


public static Utente UpdateUsername(long id,String username) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().updateUsername(id,username);
}

public static Utente UtenteDeletByPassword(long id,String password) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().deleteByPassword(id, password);
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

public static Offerta findOffertaById(long id) {
	return DaoFactory.getDaoFactory().getOffertaDao().selectById(id);
}

public static Offerta findOffertaByNome(String nome) {
	return DaoFactory.getDaoFactory().getOffertaDao().selectByName(nome);
}

public static Gioco eliminaGioco(String nome) {
	
	
	return DaoFactory.getDaoFactory().getGiocoDao().deleteGioco(nome);
}

public static Utente updateEmail(long id,String email) {
	return DaoFactory.getDaoFactory().getUtenteDao().updateEmail(id, email);
}

public static Utente updatePassword(long id,String password) {
	return DaoFactory.getDaoFactory().getUtenteDao().updatePassword(id, password);
	}

public static List<Gioco> GiocoCercaPerGenere(Genere genere) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().findGiocoGenereByGenere(genere);
}

public static List<Gioco> GiocoOfferta(Offerta offerta){
	
	return DaoFactory.getDaoFactory().getGiocoDao().findGiocoOffertaByOfferta(offerta);
			
}

public static Gioco updateGiocoNome(long id,String nome){
	
	return DaoFactory.getDaoFactory().getGiocoDao().updateGiocoNome(id,nome);
			
}


public static Gioco updateGiocoDescrzione(long id,String descrzione) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().updateGiocoDescrzione(id,descrzione);
}

public static Gioco updateGiocoImmagine(long id,String immagine) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().updateGiocoImmagine(id,immagine);
}

public static Gioco updateGiocoPrezzo(long id,double prezzo) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().updateGiocoPrezzo(id,prezzo);
}

public static Gioco updateGiocoDataRilascio(long id,LocalDateTime data) {
	
	return DaoFactory.getDaoFactory().getGiocoDao().updateGiocoDataRilascio(id, data);
			}
public static Utente UtenteDeletByNome(long id,String usernome) {
	
	return DaoFactory.getDaoFactory().getUtenteDao().deleteByNome(id, usernome);
}
}
