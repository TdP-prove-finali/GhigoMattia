package it.polito.tdp.provaFinale;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import it.polito.tdp.provaFinale.model.Albergo;
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
    private Button btnEliminaFiltriLuoghi;

    @FXML
    private Button btnImpostaFiltriLuoghi;
    
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
    	this.cmbCulto.getItems().add("Non interessato");
   		this.cmbCulto.getItems().add("Mediamente interessato");
   		this.cmbCulto.getItems().add("Molto interessato");
    	this.cmbIntrattenimento.getItems().clear();
    	this.cmbIntrattenimento.getItems().add("Non interessato");
   		this.cmbIntrattenimento.getItems().add("Mediamente interessato");
   		this.cmbIntrattenimento.getItems().add("Molto interessato");
    	this.cmbMusei.getItems().clear();
    	this.cmbMusei.getItems().add("Non interessato");
   		this.cmbMusei.getItems().add("Mediamente interessato");
   		this.cmbMusei.getItems().add("Molto interessato");
    	if(a!=null) {
    		this.labelLuoghi.setDisable(false);
    		this.btnImpostaFiltriLuoghi.setDisable(false);
    		this.btnEliminaFiltriLuoghi.setDisable(false);
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
        		this.txtArea.setText(model.getAlberghiFiltrati().size()+" alberghi trovati");
        	}
    	}
    	else {
        	this.txtArea.clear();
        	this.txtArea.setText(model.getAllAlberghi().size()+" alberghi trovati");
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
    void handleBtnImpostaFiltriLuoghi(ActionEvent event) {
    	
    }
    
    @FXML
    void handleBtnEliminaFiltriLuoghi(ActionEvent event) {
    	this.cmbTempo.getItems().clear();
    	this.cmbTempo.getItems().add(0.5+" h");
    	for(int i=1;i<=12;i++) {
    		this.cmbTempo.getItems().add(i+" h");
    	}
    	this.cmbCulto.getItems().clear();
    	this.cmbCulto.getItems().add("Non interessato");
    	this.cmbCulto.getItems().add("Mediamente interessato");
    	this.cmbCulto.getItems().add("Molto interessato");
    	this.cmbIntrattenimento.getItems().clear();
    	this.cmbIntrattenimento.getItems().add("Non interessato");
    	this.cmbIntrattenimento.getItems().add("Mediamente interessato");
    	this.cmbIntrattenimento.getItems().add("Molto interessato");
    	this.cmbMusei.getItems().clear();
    	this.cmbMusei.getItems().add("Non interessato");
   		this.cmbMusei.getItems().add("Mediamente interessato");
   		this.cmbMusei.getItems().add("Molto interessato");
    }
    
    @FXML
    void handleBtnCalcolaItinerario(ActionEvent event) {

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
    	this.txtArea.setText(model.getAllAlberghi().size()+" alberghi trovati");
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
    	
    	this.cmbCulto.getItems().add("Non interessato");
    	this.cmbCulto.getItems().add("Mediamente interessato");
    	this.cmbCulto.getItems().add("Molto interessato");
    	this.cmbIntrattenimento.getItems().add("Non interessato");
    	this.cmbIntrattenimento.getItems().add("Mediamente interessato");
    	this.cmbIntrattenimento.getItems().add("Molto interessato");
   		this.cmbMusei.getItems().add("Non interessato");
   		this.cmbMusei.getItems().add("Mediamente interessato");
   		this.cmbMusei.getItems().add("Molto interessato");
    }
}