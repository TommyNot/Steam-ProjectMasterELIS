package org.elis.daoJpa;

import java.util.List;

import org.elis.model.Utente;

public interface JpaUtenteDao {
	
	List<Utente> getAll();
}
