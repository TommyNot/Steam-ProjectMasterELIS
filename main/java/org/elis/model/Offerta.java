package org.elis.model;

import java.time.LocalDateTime;
import java.util.List;

public class Offerta {
	
	private long id;
	private LocalDateTime data_creazione;
	private LocalDateTime data_ultima_modifica;
	private String nome;
	private double sconto;
	private LocalDateTime data_inizio;
	private LocalDateTime data_fine;
	List<Gioco> gioco;
	List<Genere> genere;
	
	
	public Offerta(long id, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica, String nome,
			double sconto, LocalDateTime data_inizio, LocalDateTime data_fine) {
		super();
		this.id = id;
		this.data_creazione = data_creazione;
		this.data_ultima_modifica = data_ultima_modifica;
		this.nome = nome;
		this.sconto = sconto;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
	}
	
	public Offerta() {
		
		
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

	public double getSconto() {
		return sconto;
	}

	public void setSconto(double sconto) {
		this.sconto = sconto;
	}

	public LocalDateTime getData_inizio() {
		return data_inizio;
	}

	public void setData_inizio(LocalDateTime data_inizio) {
		this.data_inizio = data_inizio;
	}

	public LocalDateTime getData_fine() {
		return data_fine;
	}

	public void setData_fine(LocalDateTime data_fine) {
		this.data_fine = data_fine;
	}
	
	
	
	
	

}
