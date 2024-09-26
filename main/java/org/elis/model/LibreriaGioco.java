package org.elis.model;

public class LibreriaGioco {
	
	private long id;
	Libreria libreria;
	Gioco gioco;
	
	public LibreriaGioco(long id, Libreria libreria, Gioco gioco) {
		super();
		this.id = id;
		this.libreria = libreria;
		this.gioco = gioco;
	}
	
	public LibreriaGioco() {
		
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Libreria getLibreria() {
		return libreria;
	}

	public void setLibreria(Libreria libreria) {
		this.libreria = libreria;
	}

	public Gioco getGioco() {
		return gioco;
	}

	public void setGioco(Gioco gioco) {
		this.gioco = gioco;
	}
	
	
	
	

}
