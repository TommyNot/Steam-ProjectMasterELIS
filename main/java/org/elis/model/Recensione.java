package org.elis.model;

import java.time.LocalDateTime;

public class Recensione {
	private long id;
	private LocalDateTime data_creazione;
	private LocalDateTime data_ultima_modifica;
	private int voto;
	private String testo;
	private Gioco gioco;
	private Utente utente;
	
}
