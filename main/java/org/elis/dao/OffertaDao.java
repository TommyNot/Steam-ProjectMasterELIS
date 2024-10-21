package org.elis.dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.elis.model.Offerta;

public interface OffertaDao {
	Offerta add(Offerta o);
	List<Offerta> findAll(); 
	Offerta selectById(long id);
	Offerta selectByName(String nome);
	Offerta updateSconto(long id,double sconto);
	Offerta updateDataInizio(long id,LocalDate data_inizio);
	Offerta updateDataFine(long id,LocalDate data_fine);
	Offerta deleteByNome(String nome,long id);
	
}
