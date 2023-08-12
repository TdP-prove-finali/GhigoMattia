package it.polito.tdp.provaFinale.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class Toretto {

	private Integer id;
	private String tipo;
	private String indirizzo;
	private LatLng coordinate;
	private Integer visita;
	
	public Toretto(Integer id, String indirizzo, LatLng coordinate, Integer visita) {
		super();
		this.id = id;
		this.tipo = "Toret";
		this.indirizzo = indirizzo;
		this.coordinate = coordinate;
		this.visita = visita;
	}

	public Integer getNome() {
		return id;
	}

	public void setNome(Integer nome) {
		this.id = nome;
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

	public Integer getVisita() {
		return visita;
	}

	public void setVisita(Integer visita) {
		this.visita = visita;
	}

	@Override
	public int hashCode() {
		return Objects.hash(coordinate, indirizzo, id, tipo, visita);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Toretto other = (Toretto) obj;
		return Objects.equals(coordinate, other.coordinate) && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(id, other.id) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(visita, other.visita);
	}

	@Override
	public String toString() {
		return "Toretto: " + id + ", indirizzo = " + indirizzo + ", durata visita = " + visita;
	}
	
	
}
