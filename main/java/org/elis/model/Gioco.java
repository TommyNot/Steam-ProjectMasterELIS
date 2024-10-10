package org.elis.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

@Entity()
public class Gioco {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) 
		private long id;
	
	    @CreationTimestamp
	    @Column(name = "data_creazione", nullable = false) 
	    private LocalDateTime data_creazione;

	    @UpdateTimestamp
	    @Column(name = "data_ultima_modifica")
	    private LocalDateTime data_ultima_modifica;

	    @Column(name = "nome", nullable = false, length = 255)
	    private String nome;

	    @Column(name = "data_rilascio")
	    private LocalDate data_rilascio;

	    @Column(name = "descrizione", columnDefinition = "TEXT")
	    private String descrizione;

	    @Lob 
	    @Column(name = "byte_immagine")
	    private byte[] byteImmagine;

	    @Column(name = "eliminato", nullable = false)
	    private boolean eliminato;

	    @Column(name = "prezzo", nullable = false)
	    private double prezzo;

	    @ManyToOne 
	    @JoinColumn(name = "offerta_id")
	    private Offerta offerta;

	    @ManyToOne 
	    @JoinColumn(name = "genere_id")
	    private Genere genere;

	    @Column(name = "id_utente", nullable = false)
	    private long idUtente;

    // Costruttore
    public Gioco(long id, LocalDateTime data_creazione, LocalDateTime data_ultima_modifica, String nome,
                 LocalDate data_rilascio, String descrizione, byte[] byteImmagine, boolean eliminato, double prezzo,
                 Offerta offerta, long idUtente) {
        this.id = id;
        this.data_creazione = data_creazione;
        this.data_ultima_modifica = data_ultima_modifica;
        this.nome = nome;
        this.data_rilascio = data_rilascio;
        this.descrizione = descrizione;
        this.byteImmagine = byteImmagine;
        this.eliminato = eliminato;
        this.prezzo = prezzo;
        this.offerta = offerta;
        this.idUtente = idUtente;
    }

    public Gioco() {
        // Costruttore vuoto
    }

    // Getters e Setters
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

    public LocalDate getData_rilascio() {
        return data_rilascio;
    }

    public void setData_rilascio(LocalDate dataRilascio) {
        this.data_rilascio = dataRilascio;
    }

    public String getDescrzione() {
        return descrizione;
    }

    public void setDescrzione(String descrzione) {
        this.descrizione = descrzione;
    }

    public String getImmagine() {
        return Base64.getEncoder().encodeToString(byteImmagine);
    }

    public boolean isEliminato() {
        return eliminato;
    }

    public void setEliminato(boolean eliminato) {
        this.eliminato = eliminato;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public Offerta getOfferta() {
        return offerta;
    }

    public void setOfferta(Offerta offerta) {
        this.offerta = offerta;
    }

    public Genere getGenere() {
        return genere;
    }

    public void setGenere(Genere genere) {
        this.genere = genere;
    }

    public long getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(long idUtente) {
        this.idUtente = idUtente;
    }

    public byte[] getByteImmagine() {
        return byteImmagine;
    }

    public void setByteImmagine(byte[] byteImmagine) {
        this.byteImmagine = byteImmagine;
    }
}
