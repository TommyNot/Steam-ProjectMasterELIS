package org.elis.dao;

import java.util.List;

import org.elis.model.Gioco;
import org.elis.model.Libreria;
import org.elis.model.Utente;

public interface LibreriaDao {
	
	Libreria add(Libreria l);
	List<Libreria> findAll();
	Libreria findByName(String nome);
	List<Libreria> findByIdUtente(long id_utente);
	Libreria updateNome(long id, String nome);
	Libreria deleteById(long id);
	List<Gioco> findGiochiByIdLibreria(long id_libreria);
	Libreria aggiungiGiocoALibreria(long id_libreria, long id_gioco);
	List<Libreria> searchGameInLibraryList(String nomeGioco);
	Gioco deleteGiocoLibrary(long id_gioco, long id_libreria);
}
