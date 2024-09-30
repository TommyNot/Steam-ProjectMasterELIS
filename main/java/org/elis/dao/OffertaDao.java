package org.elis.dao;

import java.time.LocalDateTime;
import java.util.List;

import org.elis.model.Offerta;

public interface OffertaDao {
	Offerta add(String nome,double sconto,LocalDateTime data_inizio, LocalDateTime data_fine);
	List<Offerta> findAll();
	Offerta updatePrezzo(long id,double prezzo);
	Offerta updateDataInizio(long id,LocalDateTime data_inizio);
	Offerta updateDataFine(long id,LocalDateTime data_fine);
	Offerta deleteByNome(String nome,long id);
}
