/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class GroepOverzichtController extends AnchorPane {

    private final DomeinController dc;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button motivatieBtn;
    @FXML
    private Button actiesBtn;
    @FXML
    private ListView<?> groepListView;
    @FXML
    private Button historiekBtn;
    @FXML
    private Button feedbackBtn;
    @FXML
    private Button goedkeurenBtn;
    @FXML
    private Button afkeurenBtn;
    @FXML
    private ScrollPane motivatieScrlPane;
    
    public GroepOverzichtController(DomeinController dc) {
        this.dc =dc;
    }

    @FXML
    private void LogOut(ActionEvent event) {
    }

    @FXML
    private void MotivatieTonen(ActionEvent event) {
    }

    @FXML
    private void ActiesTonen(ActionEvent event) {
    }

    @FXML
    private void HistoriekTonen(ActionEvent event) {
    }

    @FXML
    private void FeedBackSchrijven(ActionEvent event) {
    }

    @FXML
    private void KeurMotivatieGoed(ActionEvent event) {
    }

    @FXML
    private void KeurMotivatieAf(ActionEvent event) {
    }

   
    
}
