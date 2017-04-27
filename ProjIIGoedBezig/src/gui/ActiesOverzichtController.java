/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.DomeinController;
import domein.Groep;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class ActiesOverzichtController extends AnchorPane {

    @FXML
    private Button logOutBtn;
    @FXML
    private Button motivatieBtn;
    @FXML
    private Button actiesBtn;
    @FXML
    private ListView<String> groepListView;
    @FXML
    private Button feedbackBtn;
    @FXML
    private Button goedkeurenBtn;
    @FXML
    private Button afkeurenBtn;
    @FXML
    private TextField ActieOmschrijvingTxtField;
    @FXML
    private TextField ActieDateTxtField;
    private DomeinController dc;
    @FXML
    private ListView<String> ActieListView;
    @FXML
    private Button KeurAlleActiesGoedBtn;

    /**
     * Initializes the controller class.
     */
    public ActiesOverzichtController(Groep g) {
      //  this.dc = dc;
     //   Groep g = dc.getSelectedGroep();
        List<Activiteit> acties = g.getActies();
        List<String> actienamen = new ArrayList<>();
        acties.forEach((v) -> {
            actienamen.add(v.getTitel());
        });
        ActieListView.setItems(FXCollections.observableArrayList(actienamen));
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
    private void KiesGroep(MouseEvent event) {
    }

    @FXML
    private void FeedBackSchrijven(ActionEvent event) {
    }

    @FXML
    private void KeurActieGoed(ActionEvent event) {
    }

    @FXML
    private void KeurActieAf(ActionEvent event) {
    }

    @FXML
    private void keurAlleActiesGoed(ActionEvent event) {
    }

}
