package org.elis.model;

import java.time.LocalDateTime;
import java.util.List;

public class Utente {
	
	private long id;
	private Ruolo ruolo;
	private String username;
	private String email;
	private String password;
	private LocalDateTime data_creazione;
	private LocalDateTime data_modifica;
	private List<Gioco> giochiPubblicati;
	private List<Libreria> librerie;
	private List<Recensione> rencensioni;
	
	
	public Utente(Ruolo ruolo, String username, String email, String password, LocalDateTime data_creazione, LocalDateTime data_modifica) {
	    this.ruolo = ruolo;
	    this.username = username;
	    this.email = email;
	    this.password = password;
	    this.data_creazione = data_creazione;
	    this.data_modifica = data_modifica;
	}

	public Utente() {
		
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public Ruolo getRuolo() {
		return ruolo;
	}


	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public LocalDateTime getData_creazione() {
		return data_creazione;
	}


	public void setData_creazione(LocalDateTime data_creazione) {
		this.data_creazione = data_creazione;
	}


	public LocalDateTime getData_modifica() {
		return data_modifica;
	}


	public void setData_modifica(LocalDateTime data_modifica) {
		this.data_modifica = data_modifica;
	}
	
	
	

}
