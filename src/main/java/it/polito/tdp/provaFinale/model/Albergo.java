package it.polito.tdp.provaFinale.model;

import java.util.Objects;

import com.javadocmd.simplelatlng.LatLng;

public class Albergo implements Comparable<Albergo>{

	private Integer id;
	private String nome;
	private String indirizzo;
	private Double prezzo;
	private Integer stelle;
	private Integer cap;
	private String comune;
	private String provincia;
	private LatLng coordinate;
	private String telefono;
	private String email;
	private String sito;
	private Boolean bici;
	private String lingue;
	private Boolean disabili;
	private Boolean animali;
	
	public Albergo(Integer id, String nome, String indirizzo, Double prezzo, Integer stelle, Integer cap, String comune,
			String provincia, LatLng coordinate, String telefono, String email, String sito, Integer bici,
			String lingue, Integer disabili, Integer animali) {
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
		this.telefono = telefono;
		this.email = email;
		this.sito = sito;
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
	}

	public Integer getId() {
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

	public Double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(Double prezzo) {
		this.prezzo = prezzo;
	}

	public Integer getStelle() {
		return stelle;
	}

	public void setStelle(Integer stelle) {
		this.stelle = stelle;
	}

	public Integer getCap() {
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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSito() {
		return sito;
	}

	public void setSito(String sito) {
		this.sito = sito;
	}

	public Boolean getBici() {
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

	public Boolean getDisabili() {
		return disabili;
	}

	public void setDisabili(Boolean disabili) {
		this.disabili = disabili;
	}

	public Boolean getAnimali() {
		return animali;
	}

	public void setAnimali(Boolean animali) {
		this.animali = animali;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animali, bici, cap, comune, coordinate, disabili, email, id, indirizzo, lingue, nome,
				prezzo, provincia, sito, stelle, telefono);
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
		return Objects.equals(animali, other.animali) && Objects.equals(bici, other.bici)
				&& Objects.equals(cap, other.cap) && Objects.equals(comune, other.comune)
				&& Objects.equals(coordinate, other.coordinate) && Objects.equals(disabili, other.disabili)
				&& Objects.equals(email, other.email) && Objects.equals(id, other.id)
				&& Objects.equals(indirizzo, other.indirizzo) && Objects.equals(lingue, other.lingue)
				&& Objects.equals(nome, other.nome) && Objects.equals(prezzo, other.prezzo)
				&& Objects.equals(provincia, other.provincia) && Objects.equals(sito, other.sito)
				&& Objects.equals(stelle, other.stelle) && Objects.equals(telefono, other.telefono);
	}

	@Override
	public String toString() {
		return nome + ", indirizzo = " + indirizzo;
	}

	@Override
	public int compareTo(Albergo o) {
		return this.nome.compareTo(o.nome);
	}
	
	
}
