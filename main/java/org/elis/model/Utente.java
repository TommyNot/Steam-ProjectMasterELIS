package org.elis.model;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity()
public class Utente {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@Column(name="ruolo", nullable=false)
	private Ruolo ruolo;
	
	@Column(name="username", nullable=false, unique= true)
	private String username;
	
	@Column(name="email", nullable=false, unique= true)
	private String email;
	
	@Column(name="password",nullable=false)
	private String password;
	
	@CreationTimestamp
	@Column(name="data_creazione", nullable=false)
	private LocalDateTime data_creazione;
	
	@UpdateTimestamp
	@Column(name="data_ultima_modifica", nullable=false)
	private LocalDateTime data_ultima_modifica;
	
	@OneToMany(mappedBy="idUtente")
	private List<Gioco> giochiPubblicati;
	
	@OneToMany(mappedBy="idUtente")
	private List<Libreria> librerie;
	
	@OneToMany(mappedBy="idUtente")
	private List<Recensione> rencensioni;
	
	
	public Utente(Ruolo ruolo, String username, String email, String password, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica) {
	    this.ruolo = ruolo;
	    this.username = username;
	    this.email = email;
	    this.password = password;
	    this.data_creazione = data_creazione;
	    this.data_ultima_modifica = data_ultima_modifica;
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
		return data_ultima_modifica;
	}


	public void setData_modifica(LocalDateTime data_ultima_modifica) {
		this.data_ultima_modifica = data_ultima_modifica;
	}
	
	
	

}
