package it.polito.tdp.provaFinale.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.provaFinale.model.Albergo;
import it.polito.tdp.provaFinale.model.Luogo;
import it.polito.tdp.provaFinale.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

public class FXMLController {

	Model model;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox checkAnimali;

    @FXML
    private CheckBox checkDisabili;

    @FXML
    private CheckBox checkBici;
    
    @FXML
    private Button btnImpostaFiltriHotel;
    
    @FXML
    private Button btnCalcolaItinerario;

    @FXML
    private ComboBox<String> cmbDistanza;

    @FXML
    private ComboBox<Albergo> cmbHotel;

    @FXML
    private ComboBox<String> cmbPrezzo;

    @FXML
    private ComboBox<Integer> cmbStelle;
    
    @FXML
    private ComboBox<String> cmbTempo;
    
    @FXML
    private GridPane gridFiltriLuoghi;

    @FXML
    private ComboBox<String> cmbCulto;

    @FXML
    private ComboBox<String> cmbIntrattenimento;

    @FXML
    private ComboBox<String> cmbMusei;
    
    @FXML
    private Label labelLuoghi;

    @FXML
    private TextArea txtArea;
    
    @FXML
    void btnImpostaAlbergo(ActionEvent event) {
    	this.txtArea.clear();
    	Albergo a = this.cmbHotel.getValue();
    	this.cmbTempo.getItems().clear();
    	this.cmbTempo.getItems().add(0.5+" h");
    	for(int i=1;i<=12;i++) {
    		this.cmbTempo.getItems().add(i+" h");
    	}
    	this.cmbCulto.getItems().clear();
    	this.cmbCulto.getItems().add("Molto interessato");
   		this.cmbCulto.getItems().add("Mediamente interessato");
   		this.cmbCulto.getItems().add("Non interessato");
    	this.cmbIntrattenimento.getItems().clear();
    	this.cmbIntrattenimento.getItems().add("Molto interessato");
   		this.cmbIntrattenimento.getItems().add("Mediamente interessato");
   		this.cmbIntrattenimento.getItems().add("Non interessato");
    	this.cmbMusei.getItems().clear();
    	this.cmbMusei.getItems().add("Molto interessato");
   		this.cmbMusei.getItems().add("Mediamente interessato");
   		this.cmbMusei.getItems().add("Non interessato");
    	if(a!=null) {
    		this.labelLuoghi.setDisable(false);
    		this.gridFiltriLuoghi.setDisable(false);
    		this.btnCalcolaItinerario.setDisable(false);
    		this.model.setAlbergo(a);
    	}
    	else {
    		this.txtArea.setText("Selezionare un albergo!");
    	}
    }
    
    @FXML
    void handleBtnImpostaFiltriHotel(ActionEvent event) {
    	boolean controllo = false;
    	Double prezzo = Double.MAX_VALUE;
    	if(this.cmbPrezzo.getValue()!=null) {
    		controllo = true;
    		int indice = this.cmbPrezzo.getValue().indexOf(" ");
    		prezzo = Double.parseDouble(this.cmbPrezzo.getValue().substring(0, indice));
    	}
    	Integer stelle = Integer.MIN_VALUE;
    	if(this.cmbStelle.getValue()!=null) {
    		controllo = true;
    		stelle = this.cmbStelle.getValue();
    	}
    	Double distanza = Double.MAX_VALUE;
    	if(this.cmbDistanza.getValue()!=null) {
    		controllo = true;
    		int indice = this.cmbDistanza.getValue().indexOf(" ");
    		distanza = Double.parseDouble(this.cmbDistanza.getValue().substring(0, indice));
    	}	
    	boolean animali = false;
    	if(this.checkAnimali.isSelected()) {
    		controllo = true;
    		animali = true;
    	}
    	boolean bici = false;
    	if(this.checkBici.isSelected()) {
    		controllo = true;
    		bici = true;
    	}
    	boolean disabili = false;
    	if(this.checkDisabili.isSelected()) {
    		controllo = true;
    		disabili = true;
    	}
    	if(controllo==true) {
        	this.txtArea.clear();
    		this.cmbHotel.getItems().clear();
    		model.creaListaAlberghi(prezzo, stelle, distanza, bici, disabili, animali);
        	List<Albergo> alberghiFiltrati = new ArrayList<>(model.getAlberghiFiltrati());
        	this.cmbHotel.getItems().addAll(alberghiFiltrati);
        	if(alberghiFiltrati.size()==0) {
            	this.txtArea.setText(model.getAlberghiFiltrati().size()+" alberghi trovati, modificare i filtri");
        	}
        	else {
        		this.cmbHotel.getItems().addAll();
        		this.txtArea.setText(model.getAlberghiFiltrati().size()+" alberghi trovati\n");
        		this.txtArea.appendText("Selezionare un hotel");
        	}
    	}
    	else {
        	this.txtArea.clear();
        	this.txtArea.setText(model.getAllAlberghi().size()+" alberghi trovati\n");
        	this.txtArea.appendText("Selezionare un hotel");
    	}
    }
    
    @FXML
    void btnEliminaFiltriHotel(ActionEvent event) {
    	this.txtArea.clear();
    	this.cmbHotel.getItems().clear();
    	this.cmbHotel.getItems().addAll(model.getAllAlberghi());
    	this.txtArea.setText(model.getAllAlberghi().size()+" alberghi trovati");
    	this.cmbPrezzo.getItems().clear();
    	for(double i=50;i<=250;i+=50) {
    		this.cmbPrezzo.getItems().add(i+" €");
    	}
    	this.cmbStelle.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbStelle.getItems().add(i);
    	}
    	this.cmbDistanza.getItems().clear();
    	for(double i=1;i<=8;i++) {
    		this.cmbDistanza.getItems().add(i+" km");
    	}
    }
    
    @FXML
    void handleBtnCalcolaItinerario(ActionEvent event) {
    	if(this.cmbTempo.getValue()==null || this.cmbIntrattenimento.getValue()==null || this.cmbCulto.getValue()==null || this.cmbMusei.getValue()==null) {
    		this.txtArea.setText("Impostare i filtri richiesti per i luoghi");
    		return;
    	}
    	this.txtArea.clear();
    	int indice = this.cmbTempo.getValue().indexOf(" ");
    	double tempo = Double.parseDouble(this.cmbTempo.getValue().substring(0, indice));
    	int intrattenimento = 0;
    	int culto = 0;
    	int musei = 0;
    	if(this.cmbIntrattenimento.getValue().compareTo("Non interessato")==0) {
    		intrattenimento=1;
    	}
    	else if(this.cmbIntrattenimento.getValue().compareTo("Mediamente interessato")==0) {
    		intrattenimento=2;
    	}
    	else if(this.cmbIntrattenimento.getValue().compareTo("Molto interessato")==0) {
    		intrattenimento=3;
    	}
    	if(this.cmbCulto.getValue().compareTo("Non interessato")==0) {
    		culto=1;
    	}
    	else if(this.cmbCulto.getValue().compareTo("Mediamente interessato")==0) {
    		culto=2;
    	}
    	else if(this.cmbCulto.getValue().compareTo("Molto interessato")==0) {
    		culto=3;
    	}
    	if(this.cmbMusei.getValue().compareTo("Non interessato")==0) {
    		musei=1;
    	}
    	else if(this.cmbMusei.getValue().compareTo("Mediamente interessato")==0) {
    		musei=2;
    	}
    	else if(this.cmbMusei.getValue().compareTo("Molto interessato")==0) {
    		musei=3;
    	}
    	double tic = System.currentTimeMillis();
    	model.creaItinerario(tempo*60, intrattenimento, culto, musei);
    	double toc = System.currentTimeMillis();
    	List<Luogo> itinerarioTop = new ArrayList<>(model.getItinerarioMiglioreFiltrato());
    	List<Luogo> itinerario = new ArrayList<>(model.getItinerarioMigliore());
    	boolean controllo = false;
    	if(itinerarioTop.size()==0) {
    		this.txtArea.setText("Non è stato possibile creare un itineario che rispetti i filtri inseriti, modificarli e riprovare\n");
    		controllo=true;
    	}
    	else if(itinerarioTop.size()>0) {
        	this.txtArea.setText("Itinerario creato:\n");
        	for(int i=1;i<itinerarioTop.size()-1;i++) {
        		this.txtArea.appendText(itinerarioTop.get(i)+"\n");
        		if(itinerarioTop.get(i).getNome().compareTo(itinerario.get(i).getNome())!=0) {
        			controllo=true;
        		}
        	}
        	this.txtArea.appendText("\nNumero di posti visitabili: "+(itinerarioTop.size()-2));
        	this.txtArea.appendText("\nDurata itinerario: "+Math.round(model.getDurataFiltrata())+" minuti\n");
    	}
    	if(controllo==true) {
    		this.txtArea.appendText("\n\nItinerario alternativo, senza filtri:\n");
        	for(int i=1;i<itinerario.size()-1;i++) {
        		this.txtArea.appendText(itinerario.get(i)+"\n");
        	}
        	this.txtArea.appendText("\nNumero di posti visitabili: "+(itinerario.size()-2));
        	this.txtArea.appendText("\nDurata itinerario: "+Math.round(model.getDurata())+" minuti\n");
    	}
    	if(intrattenimento>=2) {
    		boolean a = false;
    		boolean b = false;
    		for(Luogo l : itinerarioTop) {
    			if(l.getTipo().compareTo("Cinema")==0) {
    				a = true;
    			}
    			if(l.getTipo().compareTo("Teatro")==0) {
    				b = true;
    			}
    		}
    		if(a==false) {
    			this.txtArea.appendText("\nCinema nelle vicinanze: "+model.getCinema());
    		}
    		if(b==false) {
    			this.txtArea.appendText("\nTeatro nelle vicinanze: "+model.getTeatro());
    		}
    	}
    	if(culto>=2) {
    		boolean a = false;
    		for(Luogo l : itinerarioTop) {
    			if(l.getTipo().compareTo("Chiesa")==0) {
    				a = true;
    			}
    		}
    		if(a==false) {
        		this.txtArea.appendText("\nChiesa nelle vicinanze: "+model.getChiesa());
    		}
    	}
    	if(musei>=2) {
    		boolean a = false;
    		for(Luogo l : itinerarioTop) {
    			if(l.getTipo().compareTo("Museo")==0) {
    				a = true;
    			}
    		}
    		if(a==false) {
        		this.txtArea.appendText("\nMuseo nelle vicinanze: "+model.getMuseo());
    		}
    	}
    	this.txtArea.appendText("\nLocale storico nelle vicinanze: "+model.getLocale());
    	this.txtArea.appendText("\nParco nelle vicinanze: "+model.getParco());
    	this.txtArea.appendText("\nToret, simbolo di Torino, nelle vicinanze: "+model.getToret());
    	this.txtArea.appendText("\n\nTempo impiegato per il calcolo del percorso: "+((toc-tic)/1000)+" secondi");
    }

    @FXML
    void logoaperTO(MouseEvent event) {
    	if (Desktop.isDesktopSupported()) {      
	         Desktop desktop = Desktop.getDesktop();
	         try {
	        	 URI uri = new URI("http://aperto.comune.torino.it/it/");
	        	 desktop.browse(uri);
	         } catch (IOException e) {
	            e.printStackTrace();
	         } catch (URISyntaxException e) {
	            e.printStackTrace();
	         }
	      }
    }
    
    @FXML
    void logoILoveToret(MouseEvent event) {
    	if (Desktop.isDesktopSupported()) {      
	         Desktop desktop = Desktop.getDesktop();
	         try {
	        	 URI uri = new URI("https://ilovetoret.it/it/");
	        	 desktop.browse(uri);
	         } catch (IOException excp) {
	            excp.printStackTrace();
	         } catch (URISyntaxException excp) {
	            excp.printStackTrace();
	         }
	      }
    }

    @FXML
    void initialize() {
        assert checkAnimali != null : "fx:id=\"CheckAnimali\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert checkDisabili != null : "fx:id=\"CheckDisabili\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert checkBici != null : "fx:id=\"checkBici\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbDistanza != null : "fx:id=\"cmbDistanza\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbHotel != null : "fx:id=\"cmbHotel\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbPrezzo != null : "fx:id=\"cmbPrezzo\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbStelle != null : "fx:id=\"cmbStelle\" was not injected: check your FXML file 'provaFinale.fxml'.";

    }

    public void setModel(Model model) {
    	this.model=model;
    	
    	this.cmbHotel.getItems().addAll(model.getAllAlberghi());
    	this.txtArea.clear();
    	this.txtArea.setText(model.getAllAlberghi().size()+" alberghi trovati\n");
    	this.txtArea.appendText("Selezionare un hotel");
    	for(double i=50;i<=250;i+=50) {
    		this.cmbPrezzo.getItems().add(i+" €");
    	}
    	for(int i=1;i<=5;i++) {
    		this.cmbStelle.getItems().add(i);
    	}
    	this.cmbDistanza.getItems().add("0.5 km");
    	for(double i=1;i<=8;i++) {
    		this.cmbDistanza.getItems().add(i+" km");
    	}
    	this.cmbTempo.getItems().add(0.5+" h");
    	for(int i=1;i<=12;i++) {
    		this.cmbTempo.getItems().add(i+" h");
    	}
    	
    	this.cmbCulto.getItems().add("Molto interessato");
    	this.cmbCulto.getItems().add("Mediamente interessato");
    	this.cmbCulto.getItems().add("Non interessato");
    	this.cmbIntrattenimento.getItems().add("Molto interessato");
    	this.cmbIntrattenimento.getItems().add("Mediamente interessato");
    	this.cmbIntrattenimento.getItems().add("Non interessato");
   		this.cmbMusei.getItems().add("Molto interessato");
   		this.cmbMusei.getItems().add("Mediamente interessato");
   		this.cmbMusei.getItems().add("Non interessato");
    }
}