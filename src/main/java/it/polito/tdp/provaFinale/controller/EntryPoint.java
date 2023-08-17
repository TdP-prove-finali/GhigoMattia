package it.polito.tdp.provaFinale.controller;

import javafx.application.Application;
import it.polito.tdp.provaFinale.model.Model;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class EntryPoint extends Application {

    @Override
    public void start(Stage stage) throws Exception {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/provaFinale.fxml"));
        Parent root = loader.load();
        
        Model model = new Model();
        
        FXMLController controller = loader.getController();
        controller.setModel(model);
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        scene.getRoot().setStyle("-fx-font-family: 'serif'");

        stage.setTitle("Prova finale");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
