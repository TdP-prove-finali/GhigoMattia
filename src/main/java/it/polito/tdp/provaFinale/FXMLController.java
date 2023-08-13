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
    private CheckBox CheckAnimali;

    @FXML
    private CheckBox CheckDisabili;

    @FXML
    private CheckBox checkBici;
    
    @FXML
    private Button btnEliminaFiltriLuoghi;

    @FXML
    private Button btnImpostaFiltriLuoghi;

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
    private ComboBox<Integer> cmbChiese;

    @FXML
    private ComboBox<Integer> cmbMusei;

    @FXML
    private ComboBox<Integer> cmbTeatri;
    
    @FXML
    private Label labelLuoghi;
    
    @FXML
    private Label labelNumeroHotel;

    @FXML
    private TextArea textArea;
    
    @FXML
    void btnInvio(ActionEvent event) {
    	Albergo a = this.cmbHotel.getValue();
    	this.cmbTempo.getItems().clear();
    	for(int i=1;i<=12;i++) {
    		this.cmbTempo.getItems().add(i+" h");
    	}
    	this.cmbChiese.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbChiese.getItems().add(i);
    	}
    	this.cmbMusei.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbMusei.getItems().add(i);
    	}
    	this.cmbTeatri.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbTeatri.getItems().add(i);
    	}
    	if(a!=null) {
    		this.labelLuoghi.setDisable(false);
    		this.btnImpostaFiltriLuoghi.setDisable(false);
    		this.btnEliminaFiltriLuoghi.setDisable(false);
    		this.gridFiltriLuoghi.setDisable(false);
    		this.model.setAlbergo(a);
    	}
    }
    
    @FXML
    void btnImpostaFiltriHotel(ActionEvent event) {
    	this.cmbHotel.getItems().clear();
    	Double prezzo = Double.MAX_VALUE;
    	if(this.cmbPrezzo.getValue()!=null) {
    		int indice = this.cmbPrezzo.getValue().indexOf(" ");
    		prezzo = Double.parseDouble(this.cmbPrezzo.getValue().substring(0, indice));
    	}
    	Integer stelle = Integer.MIN_VALUE;
    	if(this.cmbStelle.getValue()!=null) {
    		stelle = this.cmbStelle.getValue();
    	}
    	Double distanza = Double.MAX_VALUE;
    	if(this.cmbDistanza.getValue()!=null) {
    		int indice = this.cmbDistanza.getValue().indexOf(" ");
    		distanza = Double.parseDouble(this.cmbDistanza.getValue().substring(0, indice));
    	}
    	boolean bici = false;
    	if(this.checkBici.isSelected()) {
    		bici = true;
    	}
    	boolean disabili = false;
    	if(this.CheckDisabili.isSelected()) {
    		disabili = true;
    	}
    	boolean animali = false;
    	if(this.CheckAnimali.isSelected()) {
    		animali = true;
    	}
    	model.creaListaAlberghi(prezzo, stelle, distanza, bici, disabili, animali);
    	List<Albergo> alberghiFiltrati = new ArrayList<>(model.getAlberghiFiltrati());
    	if(alberghiFiltrati.size()==0) {
        	this.labelNumeroHotel.setText(model.getAlberghiFiltrati().size()+" alberghi trovati, modificare i filtri");
    	}
    	else {
    		this.cmbHotel.getItems().addAll();
    		this.labelNumeroHotel.setText(model.getAlberghiFiltrati().size()+" alberghi trovati");
    	}
    }
    
    @FXML
    void btnEliminaFiltriHotel(ActionEvent event) {
    	this.cmbHotel.getItems().clear();
    	this.cmbHotel.getItems().addAll(model.getAllAlberghi());
    	this.labelNumeroHotel.setText(model.getAllAlberghi().size()+" alberghi trovati");
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
    	this.checkBici.setSelected(false);
    	this.CheckDisabili.setSelected(false);
    	this.CheckAnimali.setSelected(false);
    }
    
    @FXML
    void handleBtnImpostaFiltriLuoghi(ActionEvent event) {

    }
    
    @FXML
    void handleBtnEliminaFiltriLuoghi(ActionEvent event) {
    	this.cmbTempo.getItems().clear();
    	for(int i=1;i<=12;i++) {
    		this.cmbTempo.getItems().add(i+" h");
    	}
    	this.cmbChiese.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbChiese.getItems().add(i);
    	}
    	this.cmbMusei.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbMusei.getItems().add(i);
    	}
    	this.cmbTeatri.getItems().clear();
    	for(int i=1;i<=5;i++) {
    		this.cmbTeatri.getItems().add(i);
    	}
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
        assert CheckAnimali != null : "fx:id=\"CheckAnimali\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert CheckDisabili != null : "fx:id=\"CheckDisabili\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert checkBici != null : "fx:id=\"checkBici\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbDistanza != null : "fx:id=\"cmbDistanza\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbHotel != null : "fx:id=\"cmbHotel\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbPrezzo != null : "fx:id=\"cmbPrezzo\" was not injected: check your FXML file 'provaFinale.fxml'.";
        assert cmbStelle != null : "fx:id=\"cmbStelle\" was not injected: check your FXML file 'provaFinale.fxml'.";

    }

    public void setModel(Model model) {
    	this.model=model;
    	this.cmbHotel.getItems().addAll(model.getAllAlberghi());
    	this.labelNumeroHotel.setText(model.getAllAlberghi().size()+" alberghi trovati");
    	for(double i=50;i<=250;i+=50) {
    		this.cmbPrezzo.getItems().add(i+" €");
    	}
    	for(int i=1;i<=5;i++) {
    		this.cmbStelle.getItems().add(i);
    	}
    	for(double i=1;i<=8;i++) {
    		this.cmbDistanza.getItems().add(i+" km");
    	}
    	for(int i=1;i<=12;i++) {
    		this.cmbTempo.getItems().add(i+" h");
    	}
    	for(int i=1;i<=5;i++) {
    		this.cmbChiese.getItems().add(i);
    	}
    	for(int i=1;i<=5;i++) {
    		this.cmbMusei.getItems().add(i);
    	}
    	for(int i=1;i<=5;i++) {
    		this.cmbTeatri.getItems().add(i);
    	}
    }
}