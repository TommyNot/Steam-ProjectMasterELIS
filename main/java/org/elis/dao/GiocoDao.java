package org.elis.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.model.Genere;
import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

public interface GiocoDao {
	
	Gioco add(String nome,LocalDateTime dataRilascio, String descrizione, String immagine, double prezzo, List<Genere> genere,Offerta offerta,long utente);
	List<Gioco> findAll();
	Gioco findByName(String nome);
	Gioco findGiocoById(long id);
	Gioco updateGiocoNome(String nome);
	Gioco updateGiocoDescrzione(long id,String descrzione);
	Gioco updateGiocoImmagine(long id,String immagine);
	Gioco updateGiocoPrezzo(long id,double prezzo);
	Gioco updateGiocoOfferta(long id,Offerta offerta);
	Gioco updateGiocoGenere(long id,Genere genere);
	Gioco deleteGioco(String nome);

}
