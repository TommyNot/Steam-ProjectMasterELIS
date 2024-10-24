package org.elis.businesslogic;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.dao.DaoFactory;
import org.elis.jpa.DaoFactoryJpa;
import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Libreria;
import org.elis.model.Offerta;
import org.elis.model.Recensione;
import org.elis.model.Ruolo;
import org.elis.model.Utente;

public class BusinessLogic {
	
	
	private static final String IMPLEMENTATION = "JPA";
	
public static Utente UtenteLogin(String email,String password) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().loginUtente(email, password);
}
	
public static List<Utente> UtenteFindAll() {
		
		return  DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().findAll();
	}


public static Utente UtenteAdd(Utente u) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().add(u);
}
	
public static Utente UtenteFindByName(String username) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().findByName(username);
}


public static Utente UpdateUsername(long id,String username) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().updateUsername(id,username);
}

public static Utente UtenteDeletByPassword(long id,String password) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().deleteByPassword(id, password);
}

public static Utente UtenteFindById(long id) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().selectById(id);
}

public static Gioco GiocoAdd(Gioco g) {
	

	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().add(g);

}

public static List<Gioco> VisualizzaTuttiGiochi() {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().findAll();
}

public static List<Gioco> VisualizzaGiochiInOfferta(){
	
	return DaoFactoryJpa.getDaoFactory(IMPLEMENTATION).getGiocoDao().VisualizzaGiochiInOfferta();
}

public static List<Gioco> VisualizzaTuttiGiochi(long id) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().VisualizzaGiochiPerUtente(id);
}

public static Gioco findGiocoById(long id) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().findGiocoById(id);

}
public static List<Gioco> TrovaByName(String nome) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().findByName(nome);
}

public static Genere GenereAdd(String nome) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().add(nome);
}

public static List<Genere> VisalizzaTuttiGeneri(){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().findAll();
}

public static Genere SelectByName(String nome) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().findByName(nome);
}

public static Genere aggiungiGiocoaGnere(long idGenere,long idGioco) {
	
	return DaoFactoryJpa.getDaoFactory(IMPLEMENTATION).getGenereDao().aggiungiGiocoaGnere(idGenere, idGioco);
}

public static Genere getGenereById(long idGenere) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().selectById(idGenere);
}

public static Genere DeleteByName(String nome) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().deleteByName(nome);
}

public static Offerta offertaAdd(Offerta offerta) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().add(offerta);
	
}

public static List<Offerta> offertaVisualizzaTutto() {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().findAll();
}

public static Offerta updateScontoOfferta(long id,double sconto) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().updateSconto(id, sconto);
}

public static Offerta updateDataInizioOfferta(long id,LocalDate data_inizio) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().updateDataInizio(id, data_inizio);
}

public static Offerta updateDataFineOfferta(long id,LocalDate data_fine) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().updateDataFine(id, data_fine);
}

public static Offerta deleteByNameOfferta(String username,long id) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().deleteByNome(username, id);
}

public static Offerta findOffertaById(long id) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().selectById(id);
}

public static Offerta findOffertaByNome(String nome) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().selectByName(nome);
}

public static Gioco eliminaGioco(long id) {
	
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().deleteGioco(id);
}

public static Utente updateEmail(long id,String email) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().updateEmail(id, email);
}

public static Utente updatePassword(long id,String password) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().updatePassword(id, password);
	}

public static List<Gioco> GiocoCercaPerGenere(long Idgenere) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().findGiocoGenereByGenere(Idgenere);
}

public static List<Gioco> GiocoOfferta(Offerta offerta){
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().findGiocoOffertaByOfferta(offerta);
			
}

public static Gioco updateGiocoNome(long id,String nome){
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().updateGiocoNome(id,nome);
			
}


public static Gioco updateGiocoDescrzione(long id,String descrzione) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().updateGiocoDescrzione(id,descrzione);
}

public static Gioco updateGiocoImmagine(long id,byte[] immagine) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().updateGiocoImmagine(id,immagine);
}

public static Gioco updateGiocoPrezzo(long id,double prezzo) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().updateGiocoPrezzo(id,prezzo);
}

public static Gioco updateGiocoDataRilascio(long id,LocalDate data) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().updateGiocoDataRilascio(id, data);
			}
public static Utente UtenteDeletByNome(long id,String usernome) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().deleteByNome(id, usernome);
}

public static Utente RipristinaPassword(String username,String email,String password) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().ripristinaPassword(username, email, password);
}

public static Offerta OffertaById(long id) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getOffertaDao().selectById(id);
			}

public static Libreria LibreriaAdd(Libreria l) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().add(l);
}

public static List<Libreria> VisualizzaTutteLibrerie(){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().findAll();
}

public static Libreria findLibreriaByNome(String nome) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().findByName(nome);
}

public static List<Libreria> findLibreriaByIdUtente(long id_utente){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().findByIdUtente(id_utente);
}

public static Libreria updateLibreriaNome(long id, String nome) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().updateNome(id, nome);
}

public static Libreria eliminaLibreria(long id) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().deleteById(id);
}

public static List<Gioco> findGiochiByIdLibreria(long id_libreria){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().findGiochiByIdLibreria(id_libreria);
}

public static Libreria aggiungiGiocoALibreria(long id_libreria, long id_gioco) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().aggiungiGiocoALibreria(id_libreria, id_gioco);
}

public static Gioco deleteGiocoLibrary(long id_gioco, long id_libreria) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getLibreriaDao().deleteGiocoLibrary(id_gioco, id_libreria);
}

public static Recensione RecensioneAdd(Recensione r) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().add(r);
}

public static List<Recensione> VisualizzaTutteRecensioni(){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().findAll();
}

public static Recensione TrovaRecensioneById(long id) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().findRecensioneById(id);
}

public static List<Recensione> TrovaRecensioneByIdUtente(long idUtente){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().findRecensioneByIdUtente(idUtente);
}

public static List<Recensione> TrovaRecensioneByIdGioco(long idGioco){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().findRecensioneByIdGioco(idGioco);
}

public static Recensione updateRecensioneVoto(long id, int voto) {
		return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().updateVoto(id, voto);
	}

public static Recensione updateRecensioneTesto(long id, String testo) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().updateTesto(id, testo);
}

public static Recensione eliminaRecensione(long id) {
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getRecensioneDao().deleteRecensioneById(id);
}

public static List<Genere> genereOffertaAdd(long idGenere, long idOfferta){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().addGenereOfferta(idGenere, idOfferta);
}
public static List<Gioco>trovaTuttiGiochi(){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().findAll();
}

public static List<Genere> genereOffertaRemove(long idGenere, long idOfferta){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().removeGenereOfferta(idGenere, idOfferta);
}

public static List<Gioco> addOffertaToGiochiByGenere(long idGenere, long idOfferta){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().addOffertaToGiochiByGenere(idGenere, idOfferta);
}
public static List<Gioco> giocoOffertaAdd(long idGioco, long idOfferta){
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().addGiocoOfferta(idGioco, idOfferta);
}

public static List<Gioco> addGiocoOfferta(long idGioco, long idOfferta){
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().addGiocoOfferta(idGioco, idOfferta);
}

public static Gioco updateGiocoOfferta(long idGioco, long idOfferta) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().updateGiocoOfferta(idGioco, idOfferta);
			
}

public static Genere rimuoviGeneriDaGioco(long idGioco) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGenereDao().RimuoviGeneriDaGioco(idGioco);
		
	
}

public static Gioco  rimuoviGiocoOfferta(long idGioco) {
	
	return DaoFactory.getDaoFactory(IMPLEMENTATION).getGiocoDao().rimuoviGiocoOfferta(idGioco);
}
public static List<Utente> findByRuolo(Ruolo ruolo) {
    return DaoFactory.getDaoFactory(IMPLEMENTATION).getUtenteDao().findByRuolo(ruolo);
}

}
