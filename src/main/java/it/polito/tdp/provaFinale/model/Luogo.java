package it.polito.tdp.provaFinale.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class Luogo {

	private String nome;
	private String tipo;
	private String indirizzo;
	private LatLng coordinate;
	private int visita;
	
	public Luogo(String nome, String tipo, String indirizzo, LatLng coordinate, int visita) {
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

	public String getTipo() {
		return tipo;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public LatLng getCoordinate() {
		return coordinate;
	}

	public int getVisita() {
		return visita;
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
		Luogo other = (Luogo) obj;
		return Objects.equals(coordinate, other.coordinate) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(nome, other.nome) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(visita, other.visita);
	}

	@Override
	public String toString() {
		return nome + ", indirizzo = " + indirizzo;
	}
	
	
}
