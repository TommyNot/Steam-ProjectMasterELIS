package org.elis.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Recensione {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@CreationTimestamp
	@Column(name="data_creazione", nullable=false)
	private LocalDateTime data_creazione;
	
	@UpdateTimestamp
	@Column(name="data_ultima_modifica", nullable=false)
	private LocalDateTime data_ultima_modifica;
	
	@Column(name="voto", nullable=false)
	private int voto;
	
	@Column(name="testo")
	private String testo;
	
	@ManyToOne
	private Gioco gioco;
	
	@ManyToOne
	private Utente idUtente;
	
	public Recensione(long id, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica, int voto, String testo,
			Gioco gioco, Utente idUtente) {
		super();
		this.id = id;
		this.data_creazione = data_creazione;
		this.data_ultima_modifica = data_ultima_modifica;
		this.voto = voto;
		this.testo = testo;
		this.gioco = gioco;
		this.idUtente = idUtente;
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

	public Utente getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(Utente idUtente) {
		this.idUtente = idUtente;
	}
	
	
	
	
	
}
