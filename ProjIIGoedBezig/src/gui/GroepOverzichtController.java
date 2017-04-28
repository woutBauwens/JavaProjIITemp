/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Groep;
import domein.GroepController;
import domein.InlogController;
import domein.Motivatie;
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
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import repository.LoginDaoJpa;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class GroepOverzichtController extends GridPane {

    private List<Groep> groepen;
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
    
    private GroepController gc;

    public GroepOverzichtController(GroepController gc) {
        this.gc = gc;
        this.groepen = gc.getGroepenByLector();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroepOverzicht.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        List<String> groepsnamen = new ArrayList<>();
        for (Groep g : groepen) {
            groepsnamen.add(g.getNaam());
        }
        groepListView.setItems(FXCollections.observableArrayList(groepsnamen));
        motivatieStatusLbl.setVisible(false);
        //for loop
    }

    private Motivatie getHuidigeMotivatie() {
        String groep = groepListView.getSelectionModel().getSelectedItem();
        return getHuidigeMotivatie(groep);
    }

    private Motivatie getHuidigeMotivatie(String groep) {
        return getHuidigeGroep(groep).getHuidigeMotivatie();
    }

    private Groep getHuidigeGroep() {
        return getHuidigeGroep(groepListView.getSelectionModel().getSelectedItem());
    }

    private Groep getHuidigeGroep(String groep) {
        for (Groep g : groepen) {
            if (g.getNaam().equals(groep)) {
                return g;
            }
        }
        return null;
        //    return groepen.stream().filter(g -> g.getNaam().equals(groep)).findAny().get();
    }

    private void HistoriekTonen(ActionEvent event) {
        MotivatieHistoriekController MHC = new MotivatieHistoriekController(null);

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
                        getHuidigeMotivatie(response);
                    }
                });
    }

    @FXML
    private void motivatieTonen(ActionEvent event) {
    }

    @FXML
    private void actiesTonen(ActionEvent event) {
        ActiesOverzichtController AOC = new ActiesOverzichtController(null);

        Stage stage = (Stage) (this.getScene().getWindow());
        Scene scene = new Scene(AOC);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void keurMotivatieGoed(ActionEvent event) {
        getHuidigeGroep().keur("", true);
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText("Motivatie Goedgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        //method keur mag geen feedback meegeven

    }

    @FXML
    private void keurMotivatieAf(ActionEvent event) {
        //    dc.getSelectedGroep().keur("", false);
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText("Motivatie Afgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        //method keur mag geen feedback meegeven
    }

    @FXML
    private void logOut(ActionEvent event) {
        InlogController dc = new InlogController(new LoginDaoJpa());//lege domeincontroller om mee te geven aan loginscherm
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
        groepen.stream().filter((g) -> (g.getNaam().equals(groepListView.getSelectionModel().getSelectedItem()))).forEachOrdered((g) -> {
            motivatieTxtArea.setText(g.getHuidigeMotivatie().getTekst());
        });
        if (getHuidigeGroep().isVerstuurd() || getHuidigeGroep().isGoedgekeurd()) {
            goedkeurenBtn.setDisable(true);
            afkeurenBtn.setDisable(true);
                                motivatieTxtArea.setText(getHuidigeMotivatie().getTekst());

        } else {
            goedkeurenBtn.setDisable(false);
            afkeurenBtn.setDisable(false);
            if (getHuidigeGroep().isGoedgekeurd()) {
                motivatieStatusLbl.setVisible(true);
                motivatieStatusLbl.setText("Motivatie Goedgekeurd");
            } else {

                motivatieStatusLbl.setVisible(true);
                motivatieStatusLbl.setText("Motivatie Afgekeurd");
                if (getHuidigeMotivatie() == null) {
                    motivatieStatusLbl.setText("Motivatie is nog niet ingediend");
                } else {
                    if (getHuidigeMotivatie().getFeedback() == null) {
                        motivatieStatusLbl.setText("Motivatie nog niet gekeurd");
                    }
                    motivatieTxtArea.setText(getHuidigeMotivatie().getTekst());
                }
            }
        }
    }

}
