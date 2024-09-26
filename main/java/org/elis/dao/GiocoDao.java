package org.elis.dao;

import java.util.List;

import org.elis.model.Gioco;

public interface GiocoDao {
	
	Gioco add();
	List<Gioco> findAll();
	Gioco finByName();
	Gioco updateGioco();
	Gioco deleteGioco();

}
