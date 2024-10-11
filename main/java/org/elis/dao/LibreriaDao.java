package org.elis.dao;

import java.util.List;

import org.elis.model.Libreria;
import org.elis.model.Utente;

public interface LibreriaDao {
	
	Libreria add(Libreria l);
	List<Libreria> findAll();
	Libreria findByName(String nome);
	List<Libreria> findByIdUtente(long id_utente);
	Libreria updateNome(long id, String nome);
	void deleteById(long id);
	
}
