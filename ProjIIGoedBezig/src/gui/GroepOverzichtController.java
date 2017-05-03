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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.Window;
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
    @FXML
    private Label errorLbl;
    @FXML
    private TabPane tabPane;

    private StringBuilder historiek;

    public GroepOverzichtController(GroepController gc) {
        this.gc = gc;
        this.groepen = gc.getGroepenByLector();
        historiek = new StringBuilder();
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
        tabPane.setDisable(true);
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
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText(b ? "Motivatie Goedgekeurd" : "Motivatie Afgekeurd");
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        feedbackTxtArea.setEditable(false);

        gc.update();
        setMotivatieHistoriek();
        toonMotivatieHistoriek();
    }

    @FXML
    private void logOut(ActionEvent event) {
        InlogController dc = new InlogController(new LoginDaoJpa());
        LoginController loginC = new LoginController(dc);

        Stage stage = (Stage) (this.getScene().getWindow());
        stage.setTitle("Login: ");
        Scene scene = new Scene(loginC);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void kiesGroep(MouseEvent event) {
        gc.setSelectedGroep(groepListView.getSelectionModel().getSelectedItem());
        tabPane.setDisable(false);

        toonMotivatie();
        toonActies();
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            toonMotivatieHistoriek();
        }
        if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            toonActieHistoriek();
        }
        if (!gc.getSelectedGroep().isGoedgekeurd()) {
            actiesTab.setDisable(true);
        } else {
            actiesTab.setDisable(false);
        }

    }

    private void toonMotivatie() {
        if (gc.getSelectedGroep() != null) {
            goedkeurenBtn.setDisable(false);
            afkeurenBtn.setDisable(false);
            motivatieTxtArea.setWrapText(true);
            motivatieTxtArea.setEditable(false); //motivatie field uneditable maken
            gc.setSelectedGroep(groepListView.getSelectionModel().getSelectedItem());
            Groep groep = gc.getSelectedGroep();
            motivatieTxtArea.setText(gc.toonMotivatie());
            if (groep.isVerstuurd() && !groep.isGoedgekeurd()) {// || groep.getHuidigeMotivatie().getFeedback() != null) {
                goedkeurenBtn.setDisable(false);
                afkeurenBtn.setDisable(false);
                feedbackTxtArea.setEditable(true);
                motivatieTxtArea.setText(groep.getHuidigeMotivatie().getTekst());
            } else {
                goedkeurenBtn.setDisable(true);
                afkeurenBtn.setDisable(true);
                feedbackTxtArea.setEditable(false);
                motivatieStatusLbl.setText(groep.isGoedgekeurd() ? "Motivatie goedgekeurd" : "Motivatie afgekeurd");
            }
        }
    }

    @FXML
    private void keurActieAf(ActionEvent event) {
        geefFeedbackWindow(false);
    }

    @FXML
    private void keurActieGoed(ActionEvent event) {
        geefFeedbackWindow(true);
    }

    private void geefFeedbackWindow(boolean b) {
        actieDetailTxtArea.clear();
        keurActieAfBtn.setDisable(true);
        keurActieGoed.setDisable(true);
        String titel = actiesListView.getSelectionModel().getSelectedItem();
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Geef Feedback voor de Actie");
        dialog.setHeaderText("Feedback:");
        dialog.getEditor().visibleProperty().set(false);
        TextArea ta = new TextArea();
        dialog.getDialogPane().setContent(ta);
        
        dialog.showAndWait()
                .ifPresent(response -> {
                    if (!ta.getText().isEmpty()) {
                        gc.setFeedbackActie(titel, ta.getText());
                        gc.keurActie(b, titel);
                    } else {
                        gc.setFeedbackActie(titel, "Geen feedback");
                        gc.keurActie(b, titel);
                    }
                });
        toonActies();
        toonActieHistoriek();
        gc.update();
        actieDetailTxtArea.clear();

    }

    private void toonActies() {
        if (gc.getSelectedGroep() != null || gc.getSelectedGroep().isGoedgekeurd()) {

            keurActieAfBtn.setDisable(true);
            keurActieGoed.setDisable(true);

            List<Activiteit> acties = gc.getSelectedGroep().getActies();
            List<String> actienamen = new ArrayList<>();
            for (Activiteit a : acties) {
                if (a.getFeedback() == null) {
                    actienamen.add(a.getTitel());

                }

            }
            actiesListView.setItems(FXCollections.observableArrayList(actienamen));
        }
    }

    @FXML
    private void toonMotivatieHistoriek() {
        if (gc.getSelectedGroep() != null) {
            historiekTxtArea.setEditable(false);
            historiekTxtArea.setWrapText(true);

            historiekTxtArea.setText(historiek.toString());
        }
    }

    private void setMotivatieHistoriek() {
        List<Motivatie> motivaties = gc.getSelectedGroep().getMotivaties();
        //     StringBuilder historiek = new StringBuilder();

        for (Motivatie m : motivaties) {
            if (m.isVerstuurd() && m.getFeedback() != null) {
                StringBuilder mot = new StringBuilder(m.toString());
                historiek = mot.append("\n" + historiek);
                //    historiek.append(m.toString()).append("\n");
            }
        }
    }

    @FXML
    private void toonActieDetail() {
        String actie = actiesListView.getSelectionModel().getSelectedItem();
        if (actie != null) {
            keurActieAfBtn.setDisable(false);
            keurActieGoed.setDisable(false);
            actieDetailTxtArea.setEditable(false);
            actieDetailTxtArea.setText(gc.toonDetailActie(actie));
            List<Activiteit> acties = gc.getSelectedGroep().getActies();
            for (Activiteit a : acties) {
                if (a.getTitel().equals(actie)) {
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
    }

    @FXML
    private void toonActieHistoriek() {
        if (gc.getSelectedGroep() != null || gc.getSelectedGroep().isGoedgekeurd()) {
            historiekTxtArea.setEditable(false);
            historiekTxtArea.setWrapText(true);
            historiekTxtArea.setText(gc.getActieHistoriek());
        }
    }

}
