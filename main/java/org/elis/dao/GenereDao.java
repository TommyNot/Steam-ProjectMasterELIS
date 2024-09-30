package org.elis.dao;

import java.util.List;

import org.elis.model.Genere;

public interface GenereDao {
	Genere add(String nome);
	List<Genere> findAll();
	Genere findByName(String nome);
	Genere selectById(long id);
	Genere deleteByName(String nome);
}
