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
	private List<Altro> parchiVicini;
	private Toretto toretVicino;
	private List<Altro> localiVicini;
	private Luogo partenza;
	private Luogo arrivo;
	
	private LatLng centro = new LatLng(45.07121307478032, 7.685087280059961); //coordinate centro di Torino, nello specifico si riferiscono al centro di Piazza Castello

	private Albergo albergoScelto;
	private double tempoDisponibile;
	private int stelleCulto;
	private int stelleIntrattenimento;
	private int stelleStorico;
	
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
		for(Toretto t : allToretti) {
			this.allLuoghi.add(new Luogo(t.getNome(), t.getTipo(), t.getIndirizzo(), t.getCoordinate(), t.getVisita()));
		}
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

	public void creaListaParchi() {
		this.parchiVicini = new ArrayList<>();
		for(Altro a : this.allAltri) {
			if(a.getTipo().compareTo("Parco")==0 && LatLngTool.distance(this.albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER)<2) {
				this.parchiVicini.add(a);
			}
		}
	}
	
	public void setToret() {
		double distanza = Double.MAX_VALUE;
		Toretto vicino = null;
		for(Toretto t : this.allToretti) {
			if(LatLngTool.distance(this.albergoScelto.getCoordinate(), t.getCoordinate(), LengthUnit.KILOMETER)<distanza) {
				distanza = LatLngTool.distance(albergoScelto.getCoordinate(), t.getCoordinate(), LengthUnit.KILOMETER);
				vicino = t;
			}
		}
		this.toretVicino = vicino;
	}
	
	public void creaListaLocali() {
		this.localiVicini = new ArrayList<>();
		for(Altro a : this.allAltri) {
			if(a.getTipo().compareTo("Locale storico")==0 && LatLngTool.distance(this.albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER)<2) {
				this.localiVicini.add(a);
			}
		}
	}
	
	public void creaGrafo() {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		//creo la lista contente i luoghi vicini all'albergo
		this.luoghiVicini = new ArrayList<>();
		for(Luogo l : this.allLuoghi) {
			//parchi, toret e locali non vengono aggiunti al grafo
			if(l.getTipo().compareTo("Parco")!=0 && l.getTipo().compareTo("Toret")!=0 && l.getTipo().compareTo("Locale storico")!=0) {
				//luoghi aggiunti in modo diverso in base alla distanza dell'albergo dal centro
				if(LatLngTool.distance(centro, albergoScelto.getCoordinate(), LengthUnit.KILOMETER)<=0.5) {
					if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && l.getTipo().compareTo("Chiesa")!=0 && l.getTipo().compareTo("Teatro")!=0 && l.getTipo().compareTo("Cinema")!=0) {
						this.luoghiVicini.add(l);
					}
					else if(l.getTipo().compareTo("Chiesa")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && this.stelleCulto>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && this.stelleCulto==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Teatro")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && this.stelleIntrattenimento>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && this.stelleIntrattenimento==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Cinema")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && this.stelleIntrattenimento>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && this.stelleIntrattenimento==1){
							this.luoghiVicini.add(l);
						}
					}
				}
				else {
					if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=4) {
						this.luoghiVicini.add(l);
					}
				}
			}
		}
		
		//aggiungo, al grafo, l'albergo come punto di partenza e di arrivo
		this.partenza = new Luogo(this.albergoScelto.getNome(), "PARTENZA", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		this.arrivo = new Luogo(this.albergoScelto.getNome(), "ARRIVO", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		this.luoghiVicini.add(partenza);
		this.luoghiVicini.add(arrivo);
		
		//aggiungo i vertici al grafo
		Graphs.addAllVertices(this.grafo, this.luoghiVicini);

		//aggiungo gli archi al grafo
		for(Luogo l1 : this.grafo.vertexSet()) {
			for(Luogo l2 : this.grafo.vertexSet()) {
				if(!l1.equals(l2)) {
					Boolean controllo = false;
					//non vengono collegati luoghi simili o troppo lontani
					if((l1.getTipo().compareTo(l2.getTipo())==0 && (l1.getTipo().compareTo("Chiesa")==0 || l1.getTipo().compareTo("Museo")==0 || l1.getTipo().compareTo("Teatro")==0 || l1.getTipo().compareTo("Cinema")==0) || l1.getTipo().compareTo("Piazza")==0) || LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER)>3) {
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
	
	public void creaItinerario(double tempoDisponibile, int stelleCulto, int stelleIntrattenimento, int stelleStorico) {		
		this.tempoDisponibile = tempoDisponibile;
		this.stelleCulto = stelleCulto;
		this.stelleIntrattenimento = stelleIntrattenimento;
		this.stelleStorico = stelleStorico;
		
		this.creaGrafo();
		
		this.itinerarioMigliore = new ArrayList<>();
		this.durata = Double.MAX_VALUE;
		
		List<Luogo> parziale = new ArrayList<>();
		parziale.add(this.partenza);
		ricorsione(parziale, this.getAdiacenti(partenza), 0.0, false, false, false);
	}
	
	private void ricorsione(List<Luogo> parziale, List<Luogo> adiacenti, double cont, boolean teatri, boolean cinema, boolean chiese) {
		if(parziale.get(parziale.size()-1).equals(arrivo)) {
			if((parziale.size()>this.itinerarioMigliore.size() || (parziale.size()==this.itinerarioMigliore.size() && cont<this.durata)) && chiese==true) {
				this.itinerarioMigliore = new ArrayList<>(parziale);
				this.durata = cont;
			}
			return;
		}
			
		for(Luogo l : adiacenti) {
			if(!parziale.contains(l)) {
				if(l.getTipo().compareTo("Teatro")==0) {
					if(teatri==false) {
						DefaultWeightedEdge arcoPrecedente = grafo.getEdge(parziale.get(parziale.size()-1), l);
						double precedente = this.grafo.getEdgeWeight(arcoPrecedente);
						double successivo = 0.0;
						double distanza = LatLngTool.distance(l.getCoordinate(), this.albergoScelto.getCoordinate(), LengthUnit.KILOMETER);
						if(distanza<=1.5) {
							successivo = distanza*60/4;
						}
						else {
							successivo = distanza*60/20;
						}
						if((cont+precedente+l.getVisita()+successivo)<=this.tempoDisponibile){
							parziale.add(l);
							teatri=true;
							ricorsione(parziale, this.getAdiacenti(l), cont+precedente+l.getVisita(), teatri, cinema, chiese);
							teatri=false;
							parziale.remove(l);
						}
					}
					else {
						return;
					}
				}
				else if(l.getTipo().compareTo("Cinema")==0) {
					if(cinema==false) {
						DefaultWeightedEdge arcoPrecedente = grafo.getEdge(parziale.get(parziale.size()-1), l);
						double precedente = this.grafo.getEdgeWeight(arcoPrecedente);
						double successivo = 0.0;
						double distanza = LatLngTool.distance(l.getCoordinate(), this.albergoScelto.getCoordinate(), LengthUnit.KILOMETER);
						if(distanza<=1.5) {
							successivo = distanza*60/4;
						}
						else {
							successivo = distanza*60/20;
						}
						if((cont+precedente+l.getVisita()+successivo)<=this.tempoDisponibile){
							parziale.add(l);
							cinema=true;
							ricorsione(parziale, this.getAdiacenti(l), cont+precedente+l.getVisita(), teatri, cinema, chiese);
							cinema=false;
							parziale.remove(l);
						}
					}
				}
				else if(l.getTipo().compareTo("Chiesa")==0) {
					DefaultWeightedEdge arcoPrecedente = grafo.getEdge(parziale.get(parziale.size()-1), l);
					double precedente = this.grafo.getEdgeWeight(arcoPrecedente);
					double successivo = 0.0;
					double distanza = LatLngTool.distance(l.getCoordinate(), this.albergoScelto.getCoordinate(), LengthUnit.KILOMETER);
					if(distanza<=1.5) {
						successivo = distanza*60/4;
					}
					else {
						successivo = distanza*60/20;
					}
					if((cont+precedente+l.getVisita()+successivo)<=this.tempoDisponibile){
						parziale.add(l);
						chiese=true;
						ricorsione(parziale, this.getAdiacenti(l), cont+precedente+l.getVisita(), teatri, cinema, chiese);
						chiese=false;
						parziale.remove(l);
					}
				}
				else {
					DefaultWeightedEdge arcoPrecedente = grafo.getEdge(parziale.get(parziale.size()-1), l);
					double precedente = this.grafo.getEdgeWeight(arcoPrecedente);
					double successivo = 0.0;
					double distanza = LatLngTool.distance(l.getCoordinate(), this.albergoScelto.getCoordinate(), LengthUnit.KILOMETER);
					if(distanza<=1.5) {
						successivo = distanza*60/4;
					}
					else {
						successivo = distanza*60/20;
					}
					if((cont+precedente+l.getVisita()+successivo)<=this.tempoDisponibile){
						parziale.add(l);
						ricorsione(parziale, this.getAdiacenti(l), cont+precedente+l.getVisita(), teatri, cinema, chiese);
						parziale.remove(l);
					}
				}
			}
		}
	}

	public List<Luogo> getAdiacenti(Luogo partenza) {
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
		this.allLuoghi.sort(null);
		return allLuoghi;
	}

	public List<Luogo> getLuoghiVicini(){
		this.luoghiVicini.sort(null);
		return this.luoghiVicini;
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
	
	public List<Altro> getParchi(){
		this.parchiVicini.sort(null);
		return this.parchiVicini;
	}
	
	public Toretto getToret() {
		return this.toretVicino;
	}
	
	public List<Altro> getLocali(){
		this.localiVicini.sort(null);
		return this.localiVicini;
	}
}
