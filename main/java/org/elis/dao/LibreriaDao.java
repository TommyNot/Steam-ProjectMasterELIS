package org.elis.dao;

import java.util.List;

import org.elis.model.Libreria;
import org.elis.model.Utente;

public interface LibreriaDao {
	
	Libreria add(String nome, long id_utente);
	List<Libreria> findAll();
	Libreria findByName(String nome);
	List<Libreria> findByIdUtente(long id_utente);
	Libreria updateNome(long id, String nome);
	Libreria deleteById(long id);
	
}
