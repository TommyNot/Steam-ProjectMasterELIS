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
	
	public Recensione(long id, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica, int voto, String testo,
			Gioco gioco, Utente utente) {
		super();
		this.id = id;
		this.data_creazione = data_creazione;
		this.data_ultima_modifica = data_ultima_modifica;
		this.voto = voto;
		this.testo = testo;
		this.gioco = gioco;
		this.utente = utente;
	}
	
	public Recensione() {
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDateTime getData_creazione() {
		return data_creazione;
	}

	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}

	public LocalDateTime getData_ultima_modifica() {
		return data_ultima_modifica;
	}

	public void setData_ultima_modifica(LocalDateTime data_ultima_modifica) {
		this.data_ultima_modifica = data_ultima_modifica;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public Gioco getGioco() {
		return gioco;
	}

	public void setGioco(Gioco gioco) {
		this.gioco = gioco;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}
	
	
	
	
	
}
