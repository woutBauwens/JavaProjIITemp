/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class MotivatieHistoriekController extends GridPane {

    @FXML
    private ListView<String> motivatieHistoriekListV;
    @FXML
    private Button okBtn;
    @FXML
    private TextArea motivatieTxt;
    @FXML
    private TextArea feedbackTxt;
    
    private DomeinController dc;

    public MotivatieHistoriekController(DomeinController dc){
        this.dc=dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MotivatieHistoriek.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
    /**
     * Initializes the controller class.
     */
   

    @FXML
    private void ToonDetails(MouseEvent event) {
    }

    @FXML
    private void TerugNaarGroepsOverzicht(ActionEvent event) {
    }
    
}
