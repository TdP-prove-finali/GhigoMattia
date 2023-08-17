package it.polito.tdp.provaFinale.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class tAltro implements Comparable<tAltro>{

	private String nome;
	private String tipo;
	private String indirizzo;
	private LatLng coordinate;
	private int visita;
	
	public tAltro(String nome, String tipo, String indirizzo, LatLng coordinate, int visita) {
		super();
		this.nome = nome;
		this.tipo = tipo;
		this.indirizzo = indirizzo;
		this.coordinate = coordinate;
		this.visita = visita;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTipo() {
		return tipo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public LatLng getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(LatLng coordinate) {
		this.coordinate = coordinate;
	}

	public int getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinate, indirizzo, nome, tipo, visita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		tAltro other = (tAltro) obj;
		return Objects.equals(coordinate, other.coordinate) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(nome, other.nome) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(visita, other.visita);
	}

	@Override
	public String toString() {
		return nome + ", indirizzo = " + indirizzo;
	}

	@Override
	public int compareTo(tAltro o) {
		return this.nome.compareTo(o.nome);
	}
	
	
}
