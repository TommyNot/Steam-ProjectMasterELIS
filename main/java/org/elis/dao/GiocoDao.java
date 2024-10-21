package org.elis.dao;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

public interface GiocoDao {
	
	Gioco add(Gioco g);
	List<Gioco> findAll();
	List<Gioco> findByName(String nome);
	Gioco findGiocoById(long id);
	List<Gioco> findGiocoGenereByGenere(long idGenere);
	List<Gioco> findGiocoOffertaByOfferta(Offerta offerta);
	List<Gioco> VisualizzaGiochiPerUtente(long idUtente);
	Gioco updateGiocoNome(long id,String nome);
	Gioco updateGiocoDataRilascio(long id,LocalDate data);
	Gioco updateGiocoDescrzione(long id,String descrzione);
	Gioco updateGiocoImmagine(long id,byte[] immagine);
	Gioco updateGiocoPrezzo(long id,double prezzo);
	Gioco updateGiocoOfferta(long id,long offerta);
	Gioco updateGiocoGenere(long id,Genere genere);
	Gioco deleteGioco(long id);
	List<Gioco> VisualizzaGiochiInOfferta();
	List<Gioco> addOffertaToGiochiByGenere(long idGenere, long idOfferta);
	List<Gioco> addGiocoOfferta(long idGioco, long idOfferta);

}
