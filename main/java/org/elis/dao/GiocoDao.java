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
	Gioco findByName(String nome);
	Gioco findGiocoById(long id);
	List<Gioco> findGiocoGenereByGenere(long idGenere);
	List<Gioco> findGiocoOffertaByOfferta(Offerta offerta);
	List<Gioco> VisualizzaGiochiPerUtente(long idUtente);
	Gioco updateGiocoNome(long id,String nome);
	Gioco updateGiocoDataRilascio(long id,LocalDateTime data);
	Gioco updateGiocoDescrzione(long id,String descrzione);
	Gioco updateGiocoImmagine(long id,String immagine);
	Gioco updateGiocoPrezzo(long id,double prezzo);
	Gioco updateGiocoOfferta(long id,Offerta offerta);
	Gioco updateGiocoGenere(long id,Genere genere);
	Gioco deleteGioco(long id);

}
