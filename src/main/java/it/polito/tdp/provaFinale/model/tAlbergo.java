package it.polito.tdp.provaFinale.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class tAlbergo implements Comparable<tAlbergo>{

	private int id;
	private String nome;
	private String indirizzo;
	private double prezzo;
	private int stelle;
	private LatLng coordinate;
	private boolean bici;
	private boolean disabili;
	private boolean animali;
	private double distanzaCentro;
	
	public tAlbergo(int id, String nome, String indirizzo, double prezzo, int stelle, int cap, String comune,
			String provincia, LatLng coordinate, int bici,
			String lingue, int disabili, int animali, double distanzaCentro) {
		super();
		this.id = id;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.prezzo = prezzo;
		this.stelle = stelle;
		this.coordinate = coordinate;
		if(bici==1) {
			this.bici=true;
		}
		if(bici==0) {
			this.bici=false;
		}
		if(disabili==1) {
			this.disabili=true;
		}
		if(disabili==0) {
			this.disabili=false;
		}if(animali==1) {
			this.animali=true;
		}
		if(animali==0) {
			this.animali=false;
		}
		this.distanzaCentro = distanzaCentro;
	}

	public int getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public int getStelle() {
		return stelle;
	}

	public LatLng getCoordinate() {
		return coordinate;
	}

	public boolean getBici() {
		return bici;
	}

	public boolean getDisabili() {
		return disabili;
	}

	public boolean getAnimali() {
		return animali;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animali, bici, coordinate, disabili, distanzaCentro, id, indirizzo,
				nome, prezzo, stelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		tAlbergo other = (tAlbergo) obj;
		return animali == other.animali && bici == other.bici
				&& Objects.equals(coordinate, other.coordinate)
				&& disabili == other.disabili
				&& Double.doubleToLongBits(distanzaCentro) == Double.doubleToLongBits(other.distanzaCentro)
				&& id == other.id && Objects.equals(indirizzo, other.indirizzo)
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo)
				&& stelle == other.stelle;
	}

	@Override
	public String toString() {
		return nome + ", indirizzo = " + indirizzo + ", distanza dal centro: " + Math.round(distanzaCentro*100.0)/100.0 + " km";
	}

	@Override
	public int compareTo(tAlbergo o) {
		return this.nome.compareTo(o.nome);
	}
	
	
}
