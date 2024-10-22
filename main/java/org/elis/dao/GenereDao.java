package org.elis.dao;

import java.util.List;

import org.elis.model.Genere;

public interface GenereDao {
	Genere add(String nome);
	List<Genere> findAll();
	Genere findByName(String nome);
	Genere selectById(long id);
	Genere deleteByName(String nome);
	Genere aggiungiGiocoaGnere(long idGenere , long idGioco);
	List<Genere> addGenereOfferta(long idGenere, long idOfferta);
	List<Genere> removeGenereOfferta(long idGenere,long idOfferta);
	Genere RimuoviGeneriDaGioco(long idGioco);
}
