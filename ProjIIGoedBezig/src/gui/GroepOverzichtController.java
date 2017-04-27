/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Groep;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class GroepOverzichtController extends GridPane {

    private final DomeinController dc;
    @FXML
    private Button logOutBtn;
    @FXML
    private Button actiesBtn;
    @FXML
    private ListView<String> groepListView;
    @FXML
    private Button goedkeurenBtn;
    @FXML
    private Button afkeurenBtn;
    @FXML
    private TextArea motivatieTxtArea;
    @FXML
    private Label motivatieStatusLbl;
    @FXML
    private Button motivatie;
    @FXML
    private TextArea historiekTxtArea;
    @FXML
    private TextArea feedbackTxtArea;

    public GroepOverzichtController(DomeinController dc) {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroepOverzicht.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        List<String> groepsnamen = new ArrayList<>();
        dc.getGroepenByLector().stream().forEach(g -> groepsnamen.add(g.getNaam()));
        groepListView.setItems(FXCollections.observableArrayList(groepsnamen));
        motivatieStatusLbl.setVisible(false);
        //for loop
    }

    private void KiesGroep(MouseEvent event) {
        //  if (event.getClickCount() == 2) {

        //  motivatieTxtArea.setText(dc.toonMotivatie(groepListView.getSelectionModel().getSelectedItem()));
        //  }
    }

    private void HistoriekTonen(ActionEvent event) {
        MotivatieHistoriekController MHC = new MotivatieHistoriekController(dc);

        Stage stage = (Stage) (this.getScene().getWindow());
        Scene scene = new Scene(MHC);

        stage.setScene(scene);
        stage.show();
    }

    private void FeedBackSchrijven(ActionEvent event) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Feedback");
        dialog.setHeaderText("Geef je feedback op de motivatie");
        dialog.setContentText("Feedback");

        dialog.showAndWait()
                .ifPresent(response -> {
                    if (!response.isEmpty()) {
                        //dc logica feedback toevoegen + persisteren
                        dc.setFeedback(response);
                    }
                });
    }


    @FXML
    private void motivatieTonen(ActionEvent event) {
    }

    @FXML
    private void actiesTonen(ActionEvent event) {
        ActiesOverzichtController AOC = new ActiesOverzichtController(dc);

        Stage stage = (Stage) (this.getScene().getWindow());
        Scene scene = new Scene(AOC);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void keurMotivatieGoed(ActionEvent event) {
        dc.getSelectedGroep().keur("", true);
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText("Motivatie Goedgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        //method keur mag geen feedback meegeven
       
    }

    @FXML
    private void keurMotivatieAf(ActionEvent event) {
        dc.getSelectedGroep().keur("", false);
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText("Motivatie Afgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        //method keur mag geen feedback meegeven
    }

    @FXML
    private void logOut(ActionEvent event) {
        DomeinController dc = new DomeinController();//lege domeincontroller om mee te geven aan loginscherm
        LoginController loginC = new LoginController(dc);

        Stage stage = (Stage) (this.getScene().getWindow());
        Scene scene = new Scene(loginC);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void kiesGroep(MouseEvent event) {
        motivatieTxtArea.setWrapText(true);
        motivatieTxtArea.setDisable(true); //motivatie field uneditable maken
        List<Groep> groepen = dc.getGroepenByLector();
        for (Groep g : groepen) {
            if (g.getNaam().equals(groepListView.getSelectionModel().getSelectedItem())) {
                motivatieTxtArea.setText(dc.toonMotivatie(g));
            }
        }
        if (!dc.getSelectedGroep().isVerstuurd() || dc.getSelectedGroep().isGoedgekeurd()) {
            goedkeurenBtn.setDisable(true);
            afkeurenBtn.setDisable(true);
        } else {
            goedkeurenBtn.setDisable(false);
            afkeurenBtn.setDisable(false);
            if (dc.getSelectedGroep().isGoedgekeurd()) {
                motivatieStatusLbl.setVisible(true);
                motivatieStatusLbl.setText("Motivatie Goedgekeurd");
            } else {

                motivatieStatusLbl.setVisible(true);
                motivatieStatusLbl.setText("Motivatie Afgekeurd");
                if (dc.getSelectedGroep().getHuidigeMotivatie().getFeedback().equals(null)) {
                    motivatieStatusLbl.setText("Motivatie nog niet gekeurd");
                }
            }
        }
    }

}
