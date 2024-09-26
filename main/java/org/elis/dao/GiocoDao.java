package org.elis.dao;

import java.util.List;

import org.elis.model.Gioco;

public interface GiocoDao {
	
	Gioco add(S);
	List<Gioco> findAll();
	Gioco findByName();
	Gioco updateGioco();
	Gioco deleteGioco();

}
