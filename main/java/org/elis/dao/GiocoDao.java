package org.elis.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.model.Gioco;
import org.elis.model.Offerta;
import org.elis.model.Utente;

public interface GiocoDao {
	
	Gioco add(String nome,LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo,Offerta offerta, Utente utente);
	List<Gioco> findAll();
	Gioco findByName(String nome);
	Gioco updateGioco(String nome,LocalDateTime dataRilascio, String descrizione, String immagine, boolean eliminato, double prezzo, Offerta offerta, Utente utente);
	Gioco deleteGioco(int id);

}
