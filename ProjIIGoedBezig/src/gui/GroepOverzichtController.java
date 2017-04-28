/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.Activiteit;
import domein.Groep;
import domein.GroepController;
import domein.InlogController;
import domein.Motivatie;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
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
    private TextArea historiekTxtArea;
    @FXML
    private TextArea feedbackTxtArea;

    private GroepController gc;
    @FXML
    private Tab motievatieTab;
    @FXML
    private Tab actiesTab;
    @FXML
    private Button keurActieGoed;
    @FXML
    private TextArea actieDetailTxtArea;
    @FXML
    private Button keurActieAfBtn;
    @FXML
    private ListView<String> actiesListView;

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
//
//    private Motivatie getHuidigeMotivatie() {
//        String groep = groepListView.getSelectionModel().getSelectedItem();
//        return getHuidigeMotivatie(groep);
//    }
//
//    private Motivatie getHuidigeMotivatie(String groep) {
//        return gc.getSelectedGroep().getHuidigeMotivatie();
//    }

    @FXML
    private void keurMotivatieGoed(ActionEvent event) {
        gc.keur(true);
        gc.setFeedback(feedbackTxtArea.getText());
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText("Motivatie Goedgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        feedbackTxtArea.setDisable(true);
        gc.update();
        //method keur mag geen feedback meegeven

    }

    @FXML
    private void keurMotivatieAf(ActionEvent event) {
        gc.keur(false);
        gc.setFeedback(feedbackTxtArea.getText());
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText("Motivatie Afgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        feedbackTxtArea.setDisable(true);
        gc.update();
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
        toonMotivatie();
        toonActies();
        toonMotivatieHistoriek();
    }

    private void toonMotivatie() {
        motivatieTxtArea.setWrapText(true);
        motivatieTxtArea.setDisable(true); //motivatie field uneditable maken
        gc.setSelectedGroep(groepListView.getSelectionModel().getSelectedItem());
        Groep g = gc.getSelectedGroep();
        motivatieTxtArea.setText(gc.toonMotivatie());
//        groepen.stream().filter((g) -> (g.getNaam().equals(groepListView.getSelectionModel().getSelectedItem()))).forEachOrdered((g) -> {
//            motivatieTxtArea.setText(g.getHuidigeMotivatie().getTekst());
//        });
        if (!g.isVerstuurd() || g.isGoedgekeurd() || g.getHuidigeMotivatie().getFeedback() != null) {
            goedkeurenBtn.setDisable(true);
            afkeurenBtn.setDisable(true);
            motivatieTxtArea.setText(gc.toonMotivatie());

        } else {
            goedkeurenBtn.setDisable(false);
            afkeurenBtn.setDisable(false);
            if (g.isGoedgekeurd()) {
                motivatieStatusLbl.setVisible(true);
                motivatieStatusLbl.setText("Motivatie Goedgekeurd");
            } else {

                motivatieStatusLbl.setVisible(true);
                motivatieStatusLbl.setText("Motivatie Afgekeurd");
                if (g.getHuidigeMotivatie() == null) {
                    motivatieStatusLbl.setText("Motivatie is nog niet ingediend");
                } else {
                    if (g.getHuidigeMotivatie().getFeedback() == null) {
                        motivatieStatusLbl.setText("Motivatie nog niet gekeurd");
                    }
                    motivatieTxtArea.setText(gc.toonMotivatie());
                }
            }
        }
    }

    @FXML
    private void keurActieAf(ActionEvent event) {
    }

    @FXML
    private void keurActieGoed(ActionEvent event) {
    }

    private void toonActies() {
        List<Activiteit> acties = gc.getSelectedGroep().getActies();
        List<String> actienamen = new ArrayList<>();
        for(Activiteit a : acties){
            actienamen.add(a.getTitel());
        }
//        acties.forEach((v) -> {
//            actienamen.add(v.getTitel());
//        });
        actiesListView.setItems(FXCollections.observableArrayList(actienamen));
    }

    private void toonMotivatieHistoriek() {
        historiekTxtArea.setWrapText(true);
        List<Motivatie> motivaties = gc.getSelectedGroep().getMotivaties();
        StringBuilder historiek = new StringBuilder();

        for (Motivatie m : motivaties) {
            if (m.isVerstuurd() && m.getFeedback() != null) {
                historiek.append(m.toString()).append("\n");
            }
        }
        //  motivaties.stream().forEach(m -> historiek.append(m.toString()).append("%n"));
        historiekTxtArea.setText(historiek.toString());
    }

    @FXML
    private void toonActieDetail(MouseEvent event) {
    }

}
