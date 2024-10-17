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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity()
@Table(name="Genere")
public class Genere {
	
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
	private Offerta offertaGenere;
	
	@ManyToMany(mappedBy="genereGiochi")
	private List<Gioco> giochi;
	
	public Genere(long id, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica, String nome,
			Offerta offertaGenere) {
		
		this.id = id;
		this.data_creazione = data_creazione;
		this.data_ultima_modifica = data_ultima_modifica;
		this.nome = nome;
		this.offertaGenere = offertaGenere;
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

	public Offerta getOffertaGenere() {
		return offertaGenere;
	}

	public void setOffertaGenere(Offerta offertaGenere) {
		this.offertaGenere = offertaGenere;
	}
	
	public List<Gioco> getGiochi() {
	    return giochi;
	}

	public void setGiochi(List<Gioco> giochi) {
	    this.giochi = giochi;
	}
	
	
	
	

}
