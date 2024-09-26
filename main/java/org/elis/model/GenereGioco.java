package org.elis.model;

public class GenereGioco {
	
	private long id;
	Gioco gioco;
	Genere genere;
	
	public GenereGioco(long id,Gioco gioco, Genere genere) {
		super();
		this.gioco = gioco;
		this.genere = genere;
	}

	public Gioco getGioco() {
		return gioco;
	}

	public void setGioco(Gioco gioco) {
		this.gioco = gioco;
	}

	public Genere getGenere() {
		return genere;
	}

	public void setGenere(Genere genere) {
		this.genere = genere;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
