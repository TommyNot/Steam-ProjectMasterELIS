package org.elis.dao;

import java.util.List;

import org.elis.model.Gioco;
import org.elis.model.Recensione;

public interface RecensioneDao {
    Recensione add(Recensione r);
    List<Recensione> findAll();
    Recensione findRecensioneById(long id);
    List<Recensione> findRecensioneByIdUtente(long idUtente);
    List<Recensione> findRecensioneByIdGioco(long idGioco);
    Recensione updateVoto(long id, int voto);
    Recensione updateTesto(long id, String testo);
    Recensione deleteRecensioneById(long id);
}
