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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity()
@Table(name="Libreria")
public class Libreria {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@CreationTimestamp
	@Column(name="data_creazione", nullable=false)
	private LocalDateTime data_creazione;
	
	@UpdateTimestamp
	@Column(name="data_ultima_modifica", nullable=false)
	private LocalDateTime data_ultima_modifica;
	
	@Column(name="nome", nullable=false, unique= true)
	private String nome;
	
	@ManyToOne
	private Utente libreriaUtente;
	
	@ManyToMany(mappedBy="libreriaGiochi")
	private List<Gioco> giochiAcquistati;
	
	public Libreria(String nome, Utente libreriaUtente) {
		super();
		this.nome = nome;
		this.libreriaUtente = libreriaUtente;
	}
	
	public Libreria() {
		
		
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

	public Utente getLibreriaUtente() {
		return libreriaUtente;
	}

	public void setLibreriaUtente(Utente libreriaUtente) {
		this.libreriaUtente = libreriaUtente;
	}
	
	
	

}
