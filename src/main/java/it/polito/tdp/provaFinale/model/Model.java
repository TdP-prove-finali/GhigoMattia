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
	private Altro parcoVicino;
	private Toretto toretVicino;
	private Altro localeVicino;
	private Altro cinemaVicino;
	private Teatro teatroVicino;
	private Chiesa chiesaVicina;
	private Museo museoVicino;
	
	private LatLng coordinateCentro = new LatLng(45.07121307478032, 7.685087280059961); //coordinate centro di Torino, nello specifico si riferiscono al centro di Piazza Castello

	private Albergo albergoScelto;
	
	private List<Luogo> itinerarioMigliore;
	private double durata;
	private List<Luogo> itinerarioMiglioreFiltrato;
	private double durataFiltrata;
	
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
		this.allAltri = new ArrayList<>(dao.readAltriLuoghi());
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
		
		if(bici==true) {
			if(disabili==true) {
				if(animali==true) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici && a.getDisabili()==disabili && a.getAnimali()==animali) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
				if(animali==false) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici && a.getDisabili()==disabili) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
			}
			if(disabili==false) {
				if(animali==true) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici && a.getAnimali()==animali) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
				if(animali==false) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getBici()==bici) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
			}
		}
		if(bici==false) {
			if(disabili==true) {
				if(animali==true) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getDisabili()==disabili && a.getAnimali()==animali) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
				if(animali==false) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getDisabili()==disabili) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
			}
			if(disabili==false) {
				if(animali==true) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza && a.getAnimali()==animali) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
				if(animali==false) {
					for(Albergo a : this.allAlberghi) {
						if(a.getPrezzo()<=prezzo && a.getStelle()>=stelle && LatLngTool.distance(coordinateCentro, a.getCoordinate(), LengthUnit.KILOMETER)<=distanza) {
							this.alberghiFiltrati.add(a);
						}
					}
				}
			}
		}
	}
	
	public void setAlbergo(Albergo albergo) {
		this.albergoScelto = albergo;
		
		double distanzaChiesa = Double.MAX_VALUE;
		Chiesa chiesaVicina = null;
		for(Chiesa c : this.allChiese) {
			if(LatLngTool.distance(this.albergoScelto.getCoordinate(), c.getCoordinate(), LengthUnit.KILOMETER)<distanzaChiesa) {
				distanzaChiesa = LatLngTool.distance(albergoScelto.getCoordinate(), c.getCoordinate(), LengthUnit.KILOMETER);
				chiesaVicina = c;
			}
		}
		this.chiesaVicina = chiesaVicina;
		
		double distanzaCinema = Double.MAX_VALUE;
		Altro cinemaVicino = null;
		for(Altro a : this.allAltri) {
			if(a.getTipo().compareTo("Cinema")==0 && LatLngTool.distance(this.albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER)<distanzaCinema) {
				distanzaCinema = LatLngTool.distance(albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER);
				cinemaVicino = a;
			}
		}
		this.cinemaVicino = cinemaVicino;
		
		double distanzaLocale = Double.MAX_VALUE;
		Altro localeVicino = null;
		for(Altro a : this.allAltri) {
			if(a.getTipo().compareTo("Locale storico")==0 && LatLngTool.distance(this.albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER)<distanzaLocale) {
				distanzaLocale = LatLngTool.distance(albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER);
				localeVicino = a;
			}
		}
		this.localeVicino = localeVicino;
		
		double distanzaMuseo = Double.MAX_VALUE;
		Museo museoVicino = null;
		for(Museo m : this.allMusei) {
			if(LatLngTool.distance(this.albergoScelto.getCoordinate(), m.getCoordinate(), LengthUnit.KILOMETER)<distanzaMuseo) {
				distanzaMuseo = LatLngTool.distance(albergoScelto.getCoordinate(), m.getCoordinate(), LengthUnit.KILOMETER);
				museoVicino = m;
			}
		}
		this.museoVicino = museoVicino;
		
		double distanzaParco = Double.MAX_VALUE;
		Altro parcoVicino = null;
		for(Altro a : this.allAltri) {
			if(a.getTipo().compareTo("Parco")==0 && LatLngTool.distance(this.albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER)<distanzaParco) {
				distanzaParco = LatLngTool.distance(albergoScelto.getCoordinate(), a.getCoordinate(), LengthUnit.KILOMETER);
				parcoVicino = a;
			}
		}
		this.parcoVicino = parcoVicino;	
		
		double distanzaTeatro = Double.MAX_VALUE;
		Teatro teatroVicino = null;
		for(Teatro t : this.allTeatri) {
			if(LatLngTool.distance(this.albergoScelto.getCoordinate(), t.getCoordinate(), LengthUnit.KILOMETER)<distanzaTeatro) {
				distanzaTeatro = LatLngTool.distance(albergoScelto.getCoordinate(), t.getCoordinate(), LengthUnit.KILOMETER);
				teatroVicino = t;
			}
		}
		this.teatroVicino = teatroVicino;
		
		double distanzaToret = Double.MAX_VALUE;
		Toretto toretVicino = null;
		for(Toretto t : this.allToretti) {
			if(LatLngTool.distance(this.albergoScelto.getCoordinate(), t.getCoordinate(), LengthUnit.KILOMETER)<distanzaToret) {
				distanzaToret = LatLngTool.distance(albergoScelto.getCoordinate(), t.getCoordinate(), LengthUnit.KILOMETER);
				toretVicino = t;
			}
		}
		this.toretVicino = toretVicino;
	}
	
	public void creaGrafo(int stelleIntrattenimento, int stelleCulto, int stelleMusei) {
		this.grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);

		//creo la lista contente i luoghi vicini all'albergo
		this.luoghiVicini = new ArrayList<>();
		for(Luogo l : this.allLuoghi) {
			//parchi, toret e locali non vengono aggiunti al grafo
			if(l.getTipo().compareTo("Parco")!=0 && l.getTipo().compareTo("Toret")!=0 && l.getTipo().compareTo("Locale storico")!=0) {
				//luoghi aggiunti in modo diverso in base alla distanza dell'albergo dal centro
				if(LatLngTool.distance(coordinateCentro, albergoScelto.getCoordinate(), LengthUnit.KILOMETER)<=0.5) {
					if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && l.getTipo().compareTo("Chiesa")!=0 && l.getTipo().compareTo("Cinema")!=0 && l.getTipo().compareTo("Teatro")!=0 && l.getTipo().compareTo("Museo")!=0) {
						this.luoghiVicini.add(l);
					}
					else if(l.getTipo().compareTo("Chiesa")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && stelleCulto>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && stelleCulto==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Cinema")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && stelleIntrattenimento>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && stelleIntrattenimento==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Teatro")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && stelleIntrattenimento>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && stelleIntrattenimento==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Museo")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.4 && stelleMusei>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=0.2 && stelleMusei==1){
							this.luoghiVicini.add(l);
						}
					}
				}
				else if(LatLngTool.distance(coordinateCentro, albergoScelto.getCoordinate(), LengthUnit.KILOMETER)>0.5 && LatLngTool.distance(coordinateCentro, albergoScelto.getCoordinate(), LengthUnit.KILOMETER)<=6) {
					if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=2 && l.getTipo().compareTo("Chiesa")!=0 && l.getTipo().compareTo("Cinema")!=0 && l.getTipo().compareTo("Teatro")!=0 && l.getTipo().compareTo("Museo")!=0) {
						this.luoghiVicini.add(l);
					}
					else if(l.getTipo().compareTo("Chiesa")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=2 && stelleCulto>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=1.2 && stelleCulto==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Cinema")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=2 && stelleIntrattenimento>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=1.2 && stelleIntrattenimento==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Teatro")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=2 && stelleIntrattenimento>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=1.2 && stelleIntrattenimento==1){
							this.luoghiVicini.add(l);
						}
					}
					else if(l.getTipo().compareTo("Museo")==0) {
						if(LatLngTool.distance(albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=2 && stelleMusei>1) {
							this.luoghiVicini.add(l);
						}
						else if(LatLngTool.distance(this.albergoScelto.getCoordinate(), l.getCoordinate(), LengthUnit.KILOMETER)<=1.2 && stelleMusei==1){
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
		Luogo partenza = new Luogo(this.albergoScelto.getNome(), "PARTENZA", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
		Luogo arrivo = new Luogo(this.albergoScelto.getNome(), "ARRIVO", this.albergoScelto.getIndirizzo(), this.albergoScelto.getCoordinate(), 0);
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
					if((l1.getTipo().compareTo(l2.getTipo())==0 && (l1.getTipo().compareTo("Museo")==0 || l1.getTipo().compareTo("Teatro")==0 || l1.getTipo().compareTo("Cinema")==0)) || LatLngTool.distance(l1.getCoordinate(), l2.getCoordinate(), LengthUnit.KILOMETER)>3) {
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
	}
	
	public void creaItinerario(double tempoDisponibile, int stelleIntrattenimento, int stelleCulto, int stelleMusei) {
		
		this.creaGrafo(stelleIntrattenimento, stelleCulto, stelleMusei);
		
		this.itinerarioMigliore = new ArrayList<>();
		this.durata = Double.MAX_VALUE;
		this.itinerarioMiglioreFiltrato = new ArrayList<>();
		this.durataFiltrata = Double.MAX_VALUE;
		
		List<Luogo> parziale = new ArrayList<>();
		Luogo partenza = null;
		Luogo arrivo = null;
		for(Luogo l : this.luoghiVicini) {
			if(l.getTipo().compareTo("PARTENZA")==0) {
				partenza=l;
			}
			else if(l.getTipo().compareTo("ARRIVO")==0) {
				arrivo = l;
			}
		}
		parziale.add(partenza);
		ricorsione(parziale, this.getAdiacenti(partenza), arrivo, 0.0, false, false, 0, false, tempoDisponibile, stelleIntrattenimento, stelleCulto, stelleMusei);
	}
	
	private void ricorsione(List<Luogo> parziale, List<Luogo> adiacenti, Luogo arrivo, double cont, boolean cinema, boolean teatri, int chiese, boolean musei, double tempoDisponibile, int stelleIntrattenimento, int stelleCulto, int stelleMusei) {
		if(parziale.get(parziale.size()-1).equals(arrivo)) {
			if((parziale.size()>this.itinerarioMigliore.size() || (parziale.size()==this.itinerarioMigliore.size() && cont<this.durata))) {
				this.itinerarioMigliore = new ArrayList<>(parziale);
				this.durata = cont;
			}
			if(stelleIntrattenimento==3) {
				if(stelleCulto==3) {
					if(stelleMusei==3) {
						if(cinema==true && teatri==true && chiese>=2 && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if(cinema==true && teatri==true && chiese>=2) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if(cinema==true && teatri==true && chiese>=2) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
				}
				else if(stelleCulto==2) {
					if(stelleMusei==3) {
						if(cinema==true && teatri==true && chiese>=1 && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if(cinema==true && teatri==true && chiese>=1) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if(cinema==true && teatri==true && chiese>=1) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
				}
				else if(stelleCulto==1) {
					if(stelleMusei==3) {
						if(cinema==true && teatri==true && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if(cinema==true && teatri==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if(cinema==true && teatri==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
				}
			}
			else if(stelleIntrattenimento==2) {
				if(stelleCulto==3) {
					if(stelleMusei==3) {
						if((cinema==true || teatri==true) && chiese>=2 && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if((cinema==true || teatri==true) && chiese>=2) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if((cinema==true || teatri==true) && chiese>=2) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}	
					}
				}
				else if(stelleCulto==2) {
					if(stelleMusei==3) {
						if((cinema==true || teatri==true) && chiese>=1 && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if((cinema==true || teatri==true) && chiese>=1) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if((cinema==true || teatri==true) && chiese>=1) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
				}
				else if(stelleCulto==1) {
					if(stelleMusei==3) {
						if((cinema==true || teatri==true) && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if(cinema==true || teatri==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if(cinema==true || teatri==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
				}
			}
			else if(stelleIntrattenimento==1) {
				if(stelleCulto==3) {
					if(stelleMusei==3) {
						if(chiese>=2 && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if(chiese>=2) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if(chiese>=2) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
				}
				else if(stelleCulto==2) {
					if(stelleMusei==3) {
						if(chiese>=1 && musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==2) {
						if(chiese>=1) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}
					}
					else if(stelleMusei==1) {
						if(chiese>=1) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}	
					}
				}
				else if(stelleCulto==1) {
					if(stelleMusei==3) {
						if(musei==true) {
							if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
								this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
								this.durataFiltrata = cont;
							}
						}	
					}
					else if(stelleMusei==2) {
						if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
							this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
							this.durataFiltrata = cont;
						}
					}
					else if(stelleMusei==1) {
						if((parziale.size()>this.itinerarioMiglioreFiltrato.size() || (parziale.size()==this.itinerarioMiglioreFiltrato.size() && cont<this.durataFiltrata))) {
							this.itinerarioMiglioreFiltrato = new ArrayList<>(parziale);
							this.durataFiltrata = cont;
						}
					}
				}
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
						if((cont+precedente+l.getVisita()+successivo)<=tempoDisponibile){
							parziale.add(l);
							teatri=true;
							ricorsione(parziale, this.getAdiacenti(l), arrivo, cont+precedente+l.getVisita(), cinema, teatri, chiese, musei, tempoDisponibile, stelleIntrattenimento, stelleCulto, stelleMusei);
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
						if((cont+precedente+l.getVisita()+successivo)<=tempoDisponibile){
							parziale.add(l);
							cinema=true;
							ricorsione(parziale, this.getAdiacenti(l), arrivo, cont+precedente+l.getVisita(), cinema, teatri, chiese, musei, tempoDisponibile, stelleIntrattenimento, stelleCulto, stelleMusei);
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
					if((cont+precedente+l.getVisita()+successivo)<=tempoDisponibile){
						parziale.add(l);
						chiese++;
						ricorsione(parziale, this.getAdiacenti(l), arrivo, cont+precedente+l.getVisita(), cinema, teatri, chiese, musei, tempoDisponibile, stelleIntrattenimento, stelleCulto, stelleMusei);
						chiese--;
						parziale.remove(l);
					}
				}
				else if(l.getTipo().compareTo("Museo")==0) {
					if(musei==false) {
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
						if((cont+precedente+l.getVisita()+successivo)<=tempoDisponibile){
							parziale.add(l);
							musei=true;
							ricorsione(parziale, this.getAdiacenti(l), arrivo, cont+precedente+l.getVisita(), cinema, teatri, chiese, musei, tempoDisponibile, stelleIntrattenimento, stelleCulto, stelleMusei);
							musei=false;
							parziale.remove(l);
						}
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
					if((cont+precedente+l.getVisita()+successivo)<=tempoDisponibile){
						parziale.add(l);
						ricorsione(parziale, this.getAdiacenti(l), arrivo, cont+precedente+l.getVisita(), cinema, teatri, chiese, musei, tempoDisponibile, stelleIntrattenimento, stelleCulto, stelleMusei);
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
	
	public List<Luogo> getItinerarioMiglioreFiltrato() {
		return itinerarioMiglioreFiltrato;
	}
	
	public double getDurataFiltrata() {
		if(this.itinerarioMiglioreFiltrato.size()==0) {
			return 0.0;
		}
		else {
			return this.durataFiltrata;
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
	
	public Chiesa getChiesa(){
		return this.chiesaVicina;
	}
	
	public Altro getCinema() {
		return this.cinemaVicino;
	}
	
	public Altro getLocale(){
		return this.localeVicino;
	}
	
	public Museo getMuseo() {
		return this.museoVicino;
	}
	
	public Altro getParco(){
		return this.parcoVicino;
	}
	
	public Teatro getTeatro(){
		return this.teatroVicino;
	}
	
	public Toretto getToret() {
		return this.toretVicino;
	}
	
	
}
