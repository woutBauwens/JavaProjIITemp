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
import java.util.Collection;
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
import javafx.scene.control.TabPane;
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
    @FXML
    private Label errorLbl;
    @FXML
    private TabPane tabPane;

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
        errorLbl.setVisible(false);
        historiekTxtArea.clear();
        //for loop
    }

    @FXML
    private void keurMotivatieGoed(ActionEvent event) {
        verwerkMotivatieKeuring(true);
    }

    @FXML
    private void keurMotivatieAf(ActionEvent event) {
        verwerkMotivatieKeuring(false);
    }

    private void verwerkMotivatieKeuring(boolean b) {
        gc.keur(b);
        gc.setFeedback(feedbackTxtArea.getText());
//        motivatieStatusLbl.setVisible(true);
//        motivatieStatusLbl.setText(b ? "Motivatie Goedgekeurd" : "Motivatie Afgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        feedbackTxtArea.setEditable(false);
        
        gc.update();
        toonMotivatieHistoriek();
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
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            toonMotivatieHistoriek();
        }
        if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            toonActieHistoriek();
        }

    }

    private void toonMotivatie() {
        goedkeurenBtn.setDisable(false);
        afkeurenBtn.setDisable(false);
        motivatieTxtArea.setWrapText(true);
        motivatieTxtArea.setEditable(false); //motivatie field uneditable maken
        gc.setSelectedGroep(groepListView.getSelectionModel().getSelectedItem());
        Groep g = gc.getSelectedGroep();
        motivatieTxtArea.setText(gc.toonMotivatie());
//        groepen.stream().filter((g) -> (g.getNaam().equals(groepListView.getSelectionModel().getSelectedItem()))).forEachOrdered((g) -> {
//            motivatieTxtArea.setText(g.getHuidigeMotivatie().getTekst());
//        });
        if (g.isVerstuurd() && !g.isGoedgekeurd()) {
            feedbackTxtArea.setEditable(true);
            if (!feedbackTxtArea.getText().trim().equals("")) {
                goedkeurenBtn.setDisable(false);
                afkeurenBtn.setDisable(false);
//            if (g.isGoedgekeurd()) {
////                motivatieStatusLbl.setVisible(true);
////                motivatieStatusLbl.setText("Motivatie Goedgekeurd");
//            } else {

//                motivatieStatusLbl.setVisible(true);
//                motivatieStatusLbl.setText("Motivatie Afgekeurd");
//                if (g.getHuidigeMotivatie() == null) {
////                    motivatieStatusLbl.setText("Motivatie is nog niet ingediend");
//                } else {
//                    if ( g.getHuidigeMotivatie().getFeedback() == null) {
////                        motivatieStatusLbl.setText("Motivatie nog niet gekeurd");
//                    }
                //      String mot = gc.toonMotivatie();
                //      String mottext = (mot.substring(0, mot.indexOf("Feedback:")));
                //      String feedtext = (mot.substring(mot.indexOf("Feedback:")));
                motivatieTxtArea.setText(g.getHuidigeMotivatie().getTekst());
                //  feedbackTxtArea.setText(feedtext);
//                }
//            }

                String mot = gc.toonMotivatie();
                String mottext = (mot.substring(0, mot.indexOf("Feedback:")));
                String feedtext = (mot.substring(mot.indexOf("Feedback:")));

                motivatieTxtArea.setText(mottext);
                feedbackTxtArea.setText(feedtext);
            }
        } else {
            goedkeurenBtn.setDisable(true);
            afkeurenBtn.setDisable(true);
            feedbackTxtArea.setEditable(false);
            String mot = gc.toonMotivatie();
            if (mot.contains("Feedback:")) {
                motivatieTxtArea.setText(mot.substring(0, mot.indexOf("Feedback:")));
                feedbackTxtArea.setText(mot.substring(mot.indexOf("Feedback:")));
            } else {
                motivatieTxtArea.setText(mot);
            }

        }
    }

    @FXML
    private void keurActieAf(ActionEvent event) {
        geefFeedbackWindow(false);
        actieDetailTxtArea.clear();
    }

    @FXML
    private void keurActieGoed(ActionEvent event) {
        geefFeedbackWindow(true);
    }

    private void geefFeedbackWindow(boolean b) {

        String titel = actiesListView.getSelectionModel().getSelectedItem();

        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Geef Feedback voor de Actie");
        dialog.setHeaderText("Feedback:");
        dialog.setContentText("Feedback:");

        dialog.showAndWait()
                .ifPresent(response -> {
                    if (!response.isEmpty()) {
                        gc.setFeedbackActie(titel, response);
                        gc.keurActie(b, titel);
                    }
                });
        toonActies();
        toonActieHistoriek();
        if (b)//als actie goedgekeurd wordt de detailview refreshen, anders niet
        {
            toonActieDetail();
        }
        gc.update();

    }

    private void toonActies() {

        List<Activiteit> acties = gc.getSelectedGroep().getActies();
        List<String> actienamen = new ArrayList<>();
        for (Activiteit a : acties) {
            if (a.getGoedgekeurd() || a.getFeedback() == null) {
                actienamen.add(a.getTitel());
            }
        }
        actiesListView.setItems(FXCollections.observableArrayList(actienamen));
    }

    @FXML
    private void toonMotivatieHistoriek() {
        historiekTxtArea.setEditable(false);
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
    private void toonActieDetail() {
        //date, titel, omschr.
        actieDetailTxtArea.setEditable(false);
        actieDetailTxtArea.setText(gc.toonDetailActie(actiesListView.getSelectionModel().getSelectedItem()));
        List<Activiteit> acties = gc.getSelectedGroep().getActies();
        for (Activiteit a : acties) {
            if (a.getTitel().equals(actiesListView.getSelectionModel().getSelectedItem())) {
                if (a.getFeedback() != null) {
                    keurActieAfBtn.setDisable(true);
                    keurActieGoed.setDisable(true);
                } else {
                    keurActieAfBtn.setDisable(false);
                    keurActieGoed.setDisable(false);
                }
            }
        }

    }

    @FXML
    private void toonActieHistoriek() {

        historiekTxtArea.setEditable(false);
        historiekTxtArea.setWrapText(true);
//        List<Activiteit> acties = gc.getSelectedGroep().getActies();
//        StringBuilder historiek = new StringBuilder();
//        for (Activiteit a : acties) {
//            if (a.getFeedback() != null && !a.getGoedgekeurd()) {
//                historiek.append(a.toString()).append("\n");
//            }
//// voor activiteit column feedback in databse + 
//        }

        historiekTxtArea.setText(gc.getActieHistoriek());

    }

}
