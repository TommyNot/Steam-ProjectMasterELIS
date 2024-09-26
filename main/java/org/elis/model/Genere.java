package org.elis.model;

import java.time.LocalDateTime;

public class Genere {
	
	private long id;
	private LocalDateTime data_creazione;
	private LocalDateTime data_ultima_modifica;
	private String nome;
	Offerta offerta;
	
	
	public Genere(long id, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica, String nome,
			Offerta offerta) {
		
		this.id = id;
		this.data_creazione = data_creazione;
		this.data_ultima_modifica = data_ultima_modifica;
		this.nome = nome;
		this.offerta = offerta;
	}
	
	public Genere() {
		
		
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Offerta getOfferta() {
		return offerta;
	}

	public void setOfferta(Offerta offerta) {
		this.offerta = offerta;
	}
	
	
	
	
	
	

}
