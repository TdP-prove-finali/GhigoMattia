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
	private List<Luogo> luoghiVicini; //escludo i luoghi troppo lontani dall'albergo
	private List<Chiesa> allChiese;
	private List<Altro> allAltri;
	private List<Museo> allMusei;
	private List<Teatro> allTeatri;
	private List<Toretto> allToretti;
	private Luogo partenza;
	private Luogo arrivo;
	
	private LatLng centro = new LatLng(45.07121307478032, 7.685087280059961); //coordinate centro di Torino, nello specifico si riferiscono al centro di Piazza Castello

	private Albergo albergoScelto;
	private double tempoDisponibile;
	private int stelleChiese;
	private int stelleMusei;
	private int stelleTeatri;
	
	private List<Luogo> itinerarioMigliore;
	private double durata;
	
	public Model() {
		this.dao = new provaFinaleDAO();
		
		//aggiungo gli alberghi
		this.allAlberghi = new ArrayList<>(dao.readAlberghi());
		
		//creao la lista contenente tutti i luoghi visitabili
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
		
		//aggiungo i toret
		this.allToretti = new ArrayList<>(dao.readToretti());
//		for(Toretto t : allToretti) {
//			this.allLuoghi.add(new Luogo(t.getNome(), t.getTipo(), t.getIndirizzo(), t.getCoordinate(), t.getVisita()));
//		}
	}
	
	public void creaListaAlberghi(double prezzo, int stelle, double distanza, boolean bici, boolean disabili, boolean animali) {
		//creo la lista contenente gli alberghi filtrati
		this.alberghiFiltrati = new ArrayList<>();
		
		if(bici==true && disabili==true && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici && a.getDisabili()==disabili && a.getAnimali()==animali) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==true && disabili==true && animali==false) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici && a.getDisabili()==disabili) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==true && disabili==false && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici && a.getAnimali()==animali) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==true && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getDisabili()==disabili && a.getAnimali()==animali) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==true && disabili==false && animali==false) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==false && animali==true) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getAnimali()==animali) {
					this.alberghiFiltrati.add(a);
				}
			}
		}
		else if(bici==false && disabili==true && animali==false) {
			for(Albergo a : this.allAlberghi) {
				if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(centro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getDisabili()==disabili) {
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

		//aggiungo, al grafo, l'albergo come punto di partenza e di arrivo
		this.partenza = new Luogo("Partenza: "+this.albergoScelto.getNome(), "Partenza", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		this.arrivo = new Luogo("Arrivo: "+this.albergoScelto.getNome(), "Arrivo", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		this.allLuoghi.add(partenza);
		this.allLuoghi.add(arrivo);
		
		//creo la lista contente i luoghi vicini all'albergo
		this.luoghiVicini = new ArrayList<>();
		for(Luogo l : this.allLuoghi) {
			if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=4) {
				this.luoghiVicini.add(l);
			}
		}
		
		//aggiungo i vertici al grafo
		Graphs.addAllVertices(this.grafo, this.luoghiVicini);

		//aggiungo gli archi al grafo
		for(Luogo l1 : this.grafo.vertexSet()) {
			for(Luogo l2 : this.grafo.vertexSet()) {
				if(!l1.equals(l2)) {
					Boolean controllo = false;
					//non vengono collegati luoghi simili o troppo lontani
					if((l1.getTipo().compareTo(l2.getTipo())==0 && (l1.getTipo().compareTo("Chiesa")==0 || l1.getTipo().compareTo("Museo")==0 || l1.getTipo().compareTo("Teatro")==0 || l1.getTipo().compareTo("Toret")==0) || l1.getTipo().compareTo("Parco")==0 || l1.getTipo().compareTo("Locale storico")==0) || (l1.getTipo().compareTo("Toret")==0 && l2.getTipo().compareTo("Locale storico")==0) || (l1.getTipo().compareTo("Locale storico")==0 && l2.getTipo().compareTo("Toret")==0) || LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER)>4)	{
						controllo = true;
					}
					if(controllo==false) {
						double distanza = LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER);
						//la velocità di spostamento da un luogo ad un altro dipende dalla distanza, lo spostamento potrà avvenire a piedi oppure con mezzi pubblici
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
		parziale.add(this.partenza);
		ricorsione(parziale, this.getAdiacenti(partenza), 0.0, false, false);
	}
	
	private void ricorsione(List<Luogo> parziale, List<Luogo> adiacenti, double cont, boolean toretti, boolean locali) {
		System.out.println("i");
		
		if(parziale.get(parziale.size()-1).equals(arrivo)) {
			if((parziale.size()>this.itinerarioMigliore.size() || (parziale.size()==this.itinerarioMigliore.size()) && cont<this.durata)) {
				this.itinerarioMigliore = new ArrayList<>(parziale);
				this.durata = cont;
			}
			return;
		}
			
		for(Luogo l : adiacenti) {
			if(!parziale.contains(l)) {
				if(l.getTipo().compareTo("Toret")==0) {
					if(toretti==false) {
						if((cont+l.getVisita())<=this.tempoDisponibile){
							parziale.add(l);
							cont=cont+l.getVisita();
							toretti=true;
							ricorsione(parziale, this.getAdiacenti(l), cont, toretti, locali);
							cont=cont-l.getVisita();
							toretti=false;
							parziale.remove(l);
						}
					}
				}
				else if(l.getTipo().compareTo("Locale storico")==0) {
					if(locali==false) {
						if((cont+l.getVisita())<=this.tempoDisponibile){
							parziale.add(l);
							cont=cont+l.getVisita();
							locali=true;
							ricorsione(parziale, this.getAdiacenti(l), cont, toretti, locali);
							cont=cont-l.getVisita();
							locali=false;
							parziale.remove(l);
						}
					}
				}
//				DefaultWeightedEdge precedente = grafo.getEdge(parziale.get(parziale.size()-1), l);
//				double successivo = 0.0;
//				double distanza = LatLngTool.distance(l.getCoordinate(), this.albergoScelto.getCoordinate(), LengthUnit.KILOMETER);
//				if(distanza<=1.5) {
//					successivo = distanza*60/4;
//				}
//				else {
//					successivo = distanza*60/20;
//				}
				else {
					if((cont+l.getVisita())<=this.tempoDisponibile){
						parziale.add(l);
						cont=cont+l.getVisita();
						ricorsione(parziale, this.getAdiacenti(l), cont, toretti, locali);
						cont=cont-l.getVisita();
						parziale.remove(l);
					}
				}
			}
		}
	}

	private List<Luogo> getAdiacenti(Luogo partenza) {
		List<Luogo> adiacenti = Graphs.successorListOf(this.grafo, partenza);
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
			if(a.getId()==i) {
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
