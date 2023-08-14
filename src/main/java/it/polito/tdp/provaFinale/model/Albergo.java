package it.polito.tdp.provaFinale.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class Albergo implements Comparable<Albergo>{

	private int id;
	private String nome;
	private String indirizzo;
	private double prezzo;
	private int stelle;
	private int cap;
	private String comune;
	private String provincia;
	private LatLng coordinate;
	private boolean bici;
	private String lingue;
	private boolean disabili;
	private boolean animali;
	private double distanzaCentro;
	
	public Albergo(int id, String nome, String indirizzo, double prezzo, int stelle, int cap, String comune,
			String provincia, LatLng coordinate, int bici,
			String lingue, int disabili, int animali, double distanzaCentro) {
		super();
		this.id = id;
		this.nome = nome;
		this.indirizzo = indirizzo;
		this.prezzo = prezzo;
		this.stelle = stelle;
		this.cap = cap;
		this.comune = comune;
		this.provincia = provincia;
		this.coordinate = coordinate;
		if(bici==1) {
			this.bici=true;
		}
		if(bici==0) {
			this.bici=false;
		}
		this.lingue = lingue;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public int getStelle() {
		return stelle;
	}

	public void setStelle(Integer stelle) {
		this.stelle = stelle;
	}

	public int getCap() {
		return cap;
	}

	public void setCap(Integer cap) {
		this.cap = cap;
	}

	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public LatLng getCoordinate() {
		return coordinate;
	}

	public void setCoordinate(LatLng coordinate) {
		this.coordinate = coordinate;
	}

	public boolean getBici() {
		return bici;
	}

	public void setBici(Boolean bici) {
		this.bici = bici;
	}

	public String getLingue() {
		return lingue;
	}

	public void setLingue(String lingue) {
		this.lingue = lingue;
	}

	public boolean getDisabili() {
		return disabili;
	}

	public void setDisabili(Boolean disabili) {
		this.disabili = disabili;
	}

	public boolean getAnimali() {
		return animali;
	}

	public void setAnimali(Boolean animali) {
		this.animali = animali;
	}

	public double getDistanzaCentro() {
		return distanzaCentro;
	}

	public void setDistanzaCentro(double distanzaCentro) {
		this.distanzaCentro = distanzaCentro;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animali, bici, cap, comune, coordinate, disabili, distanzaCentro, id, indirizzo, lingue,
				nome, prezzo, provincia, stelle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Albergo other = (Albergo) obj;
		return animali == other.animali && bici == other.bici && cap == other.cap
				&& Objects.equals(comune, other.comune) && Objects.equals(coordinate, other.coordinate)
				&& disabili == other.disabili
				&& Double.doubleToLongBits(distanzaCentro) == Double.doubleToLongBits(other.distanzaCentro)
				&& id == other.id && Objects.equals(indirizzo, other.indirizzo) && Objects.equals(lingue, other.lingue)
				&& Objects.equals(nome, other.nome)
				&& Double.doubleToLongBits(prezzo) == Double.doubleToLongBits(other.prezzo)
				&& Objects.equals(provincia, other.provincia) && stelle == other.stelle;
	}

	@Override
	public String toString() {
		return nome + ", indirizzo = " + indirizzo + ", distanza dal centro: " + Math.round(distanzaCentro*100.0)/100.0 + " km";
	}

	@Override
	public int compareTo(Albergo o) {
		return this.nome.compareTo(o.nome);
	}
	
	
}
