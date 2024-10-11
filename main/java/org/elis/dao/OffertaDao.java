package org.elis.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.model.Offerta;

public interface OffertaDao {
	Offerta add(Offerta o);
	List<Offerta> findAll(); 
	Offerta selectById(long id);
	Offerta selectByName(String nome);
	Offerta updatePrezzo(long id,double prezzo);
	Offerta updateDataInizio(long id,LocalDateTime data_inizio);
	Offerta updateDataFine(long id,LocalDateTime data_fine);
	Offerta deleteByNome(String nome,long id);
}
