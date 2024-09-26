package org.elis.dao;

import java.util.List;

import org.elis.model.Libreria;

public interface LibreriaDao {
	
	Libreria add();
	List<Libreria> findAll();
	Libreria findByName();
	Libreria updateNome();
	Libreria deleteByName();
	

}
