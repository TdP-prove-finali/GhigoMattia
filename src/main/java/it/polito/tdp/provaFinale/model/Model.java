package it.polito.tdp.provaFinale.model;

import java.util.ArrayList;
import java.util.List;
import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import it.polito.tdp.provaFinale.db.provaFinaleDAO;

public class Model {

	private provaFinaleDAO dao;
	
	private Graph<Luogo, DefaultWeightedEdge> grafo;
	private List<Albergo> allAlberghi;
	private List<Albergo> alberghiFiltrati;
	private List<Luogo> allLuoghi;
	private List<Chiesa> allChiese;
	private List<Altro> allAltri;
	private List<Museo> allMusei;
	private List<Teatro> allTeatri;
	private List<Toretto> allToretti;
	
	private LatLng centro = new LatLng(45.07121307478032, 7.685087280059961); //coordinate centro di Torino, nello specifico si riferiscono al centro di Piazza Castello

	private Albergo albergoScelto;
	private double tempoDisponibile;
	private int stelleChiese;
	private int stelleMusei;
	private int stelleTeatri;
	
	private Luogo partenza;
	private Luogo arrivo;
	private List<Luogo> itinerarioMigliore;
	private double durata;
	
	public Model() {
		this.dao = new provaFinaleDAO();
		//aggiungo gli alberghi
		this.allAlberghi = new ArrayList<>(dao.readAlberghi());
		//creazione lista che conterrà tutti i tipi di luogo visitabili
		this.allLuoghi = new ArrayList<>();
		//aggiungo le chiese
		this.allChiese = new ArrayList<>(dao.readChiese());
		for(Chiesa c : allChiese) {
			this.allLuoghi.add(new Luogo(c.getNome(), c.getTipo(), c.getIndirizzo(), c.getCoordinate(), c.getVisita()));
		}
		//aggiungo altri tipi di luoghi
		this.allAltri = new ArrayList<>(dao.readLuoghi());
		for(Altro a : allAltri) {
			this.allLuoghi.add(new Luogo(a.getNome(), a.getTipo(), a.getIndirizzo(), a.getCoordinate(), a.getVisita()));
		}
		//aggiungo i musei
		this.allMusei = new ArrayList<>(dao.readMusei());
		for(Museo m : allMusei) {
			this.allLuoghi.add(new Luogo(m.getNome(), m.getTipo(), m.getIndirizzo(), m.getCoordinate(), m.getVisita()));
		}
		//aggiungo i teatri
		this.allTeatri = new ArrayList<>(dao.readTeatri());
		for(Teatro t : allTeatri) {
			this.allLuoghi.add(new Luogo(t.getNome(), t.getTipo(), t.getIndirizzo(), t.getCoordinate(), t.getVisita()));
		}
		//aggiungo i toretti
		this.allToretti = new ArrayList<>(dao.readToretti());
		for(Toretto t : allToretti) {
			this.allLuoghi.add(new Luogo(t.getNome().toString(), t.getTipo(), t.getIndirizzo(), t.getCoordinate(), t.getVisita()));
		}
	}
	
	public void creaListaAlberghi(double prezzo, int stelle, double distanza, boolean bici, boolean disabili, boolean animali) {
		this.alberghiFiltrati = new ArrayList<>();
		if(bici==true && disabili==true && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici().equals(bici) && a.getDisabili().equals(disabili) && a.getAnimali().equals(animali)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==true && disabili==true && animali==false) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici().equals(bici) && a.getDisabili().equals(disabili)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==true && disabili==false && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici().equals(bici) && a.getAnimali().equals(animali)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==true && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getDisabili().equals(disabili) && a.getAnimali().equals(animali)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==true && disabili==false && animali==false) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici().equals(bici)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==false && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getAnimali().equals(animali)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==true && animali==false) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getDisabili().equals(disabili)) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==false && animali==false){
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		
	}
	
	public void setAlbergo(Albergo albergo) {
		this.albergoScelto = albergo;
	}

	public void creaGrafo() {
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		this.partenza = new Luogo("Partenza: "+this.albergoScelto.getNome(), "Hotel", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		this.arrivo = new Luogo("Arrivo: "+this.albergoScelto.getNome(), "Hotel", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		this.allLuoghi.add(partenza);
		this.allLuoghi.add(arrivo);
		
		Graphs.addAllVertices(this.grafo, this.allLuoghi);

		for(Luogo l1 : this.grafo.vertexSet()) {
			for(Luogo l2 : this.grafo.vertexSet()) {
				if(!l1.equals(l2)) {
					Boolean controllo = false;
					if((l1.getTipo().compareTo("Toret")==0 && l2.getTipo().compareTo("Toret")==0) || (l1.getTipo().compareTo("Locale storico")==0 && l2.getTipo().compareTo("Locale storico")==0) || (l1.getTipo().compareTo("Teatro")==0 && l2.getTipo().compareTo("Teatro")==0)) {
						controllo = true;
					}
					if(controllo==false) {
						double distanza = LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER);
						if(distanza<=1.5) {
							Graphs.addEdgeWithVertices(this.grafo, l1, l2, LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER)*60/4);
						}
						else {
							Graphs.addEdgeWithVertices(this.grafo, l1, l2, LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER)*60/20);
						}
					}
				}
			}
		}
		System.out.println("Grafo creato con "+grafo.vertexSet().size()+ " vertici e "+grafo.edgeSet().size()+" archi");
	}
	
	public void creaItinerario(double tempoDisponibile, int stelleChiese, int stelleMusei, int stelleTeatri) {
		this.tempoDisponibile = tempoDisponibile;
		this.stelleChiese = stelleChiese;
		this.stelleMusei = stelleMusei;
		this.stelleTeatri = stelleTeatri;
		
		this.itinerarioMigliore = new ArrayList<>();
		this.durata = Double.MAX_VALUE;
		
		List<Luogo> parziale = new ArrayList<>();
		for(Luogo l : this.allLuoghi) {
			if(l.getNome().compareTo("Partenza: "+this.albergoScelto.getNome())==0) {
				parziale.add(l);
			}
		}
		ricorsione(parziale, this.getAdiacenti(parziale.get(0)), 0.0);
	}
	
	private void ricorsione(List<Luogo> parziale, List<Luogo> adiacenti, double cont) {
		System.out.println(adiacenti.size());

		if(parziale.get(parziale.size()-1).getNome().compareTo("Arrivo: "+this.albergoScelto.getNome())==0) {
			if(parziale.size()>this.itinerarioMigliore.size() || (parziale.size()==this.itinerarioMigliore.size() && cont<this.durata)) {
				this.itinerarioMigliore = new ArrayList<>(parziale);
				this.durata = cont;
			}
			return;
		}
			
		for(Luogo l : adiacenti) {
			if(!parziale.contains(l)) {
				DefaultWeightedEdge precedente = grafo.getEdge(parziale.get(parziale.size()-1), l);
				double successivo = 0.0;
				double distanza = LatLngTool.distance(l.getCoordinate(), this.albergoScelto.getCoordinate(), LengthUnit.KILOMETER);
				if(distanza<=1.5) {
					successivo = distanza*60/4;
				}
				else {
					successivo = distanza*60/20;
				}
				if((cont+this.grafo.getEdgeWeight(precedente)+l.getVisita()+successivo)<=this.tempoDisponibile){
					parziale.add(l);
					ricorsione(parziale, this.getAdiacenti(l), cont+=(l.getVisita()+this.grafo.getEdgeWeight(precedente)));
					parziale.remove(l);
				}
			}
		}
	}

	private List<Luogo> getAdiacenti(Luogo partenza) {
		List<Luogo> adiacenti = new ArrayList<>(Graphs.successorListOf(this.grafo, partenza));
		return adiacenti;
	}
	
	public List<Luogo> getItinerarioMigliore() {
		return itinerarioMigliore;
	}
	
	public double getDurata() {
		if(this.itinerarioMigliore.size()==0) {
			return 0.0;
		}
		else {
			return this.durata;
		}
	}
	
	public List<Albergo> getAllAlberghi() {
		this.allAlberghi.sort(null);
		return allAlberghi;
	}

	public List<Albergo> getAlberghiFiltrati() {
		this.alberghiFiltrati.sort(null);
		return alberghiFiltrati;
	}
	
	public Albergo getAlbergo(Integer i) {
		for(Albergo a : this.allAlberghi) {
			if(a.getId().compareTo(i)==0) {
				return a;
			}
		}
		return null;
	}

	public List<Luogo> getAllLuoghi() {
		return allLuoghi;
	}

	public List<Chiesa> getAllChiese() {
		return allChiese;
	}

	public List<Altro> getAllAltri() {
		return allAltri;
	}

	public List<Museo> getAllMusei() {
		return allMusei;
	}

	public List<Teatro> getAllTeatri() {
		return allTeatri;
	}

	public List<Toretto> getAllToretti() {
		return allToretti;
	}
	
	public Luogo getLuogo(String nome) {
		for(Luogo l : this.allLuoghi) {
			if(l.getNome().compareTo(nome)==0) {
				return l;
			}
		}
		return null;
	}
}
