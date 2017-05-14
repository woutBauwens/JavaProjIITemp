/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActieController;
import domein.DraaiboekController;
import domein.GroepController;
import domein.InlogController;
import domein.MotivatieController;
import domein.Taak;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
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
import repository.GenericDaoJpa;
import repository.LoginDaoJpa;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class GroepOverzichtController extends GridPane {

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
    private MotivatieController mc;
    private ActieController ac;
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
    @FXML
    private Label LectorLabel;

    private String historiek;
    @FXML
    private TextArea ledenTextArea;
    @FXML
    private Button draaiboekBtn;

    String actiefeedback;

    public GroepOverzichtController(GroepController gc) {
        this.gc = gc;
        mc = new MotivatieController();
        ac = new ActieController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroepOverzicht.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        LectorLabel.setText(LectorLabel.getText() + gc.getLector().toString());
        groepListView.setItems(FXCollections.observableArrayList(gc.getGroepsNamen()));
        errorLbl.setVisible(false);
        actieDetailTxtArea.setEditable(false);
        historiekTxtArea.clear();
        tabPane.setDisable(true);
        ledenTextArea.setEditable(false);
        draaiboekBtn.setDisable(true);
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
        mc.setKeuring(b);
        mc.setFeedback(feedbackTxtArea.getText());
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText(mc.geefMotivatieStatus());
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        feedbackTxtArea.setEditable(false);
        gc.update();
        toonMotivatieHistoriek();
    }

    @FXML
    private void logOut(ActionEvent event) {
        InlogController dc = new InlogController(new LoginDaoJpa());
        LoginController loginC = new LoginController(dc);

        Stage stage = (Stage) (this.getScene().getWindow());
        stage.setTitle("Login: ");
        Scene scene = new Scene(loginC);
        scene.getStylesheets().add("/gui/GiveADayStyle.css");

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void kiesGroep(MouseEvent event) {
        String selectedgroep = groepListView.getSelectionModel().getSelectedItem();
        setSelectedGroep(selectedgroep);
        tabPane.setDisable(false);
        historiekTxtArea.clear();
        feedbackTxtArea.clear();

        toonMotivatie();
        toonActies();
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            toonMotivatieHistoriek();
        }
        if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            toonActieHistoriek();
        }
        actiesTab.setDisable(gc.actiesToegankelijk());
        ledenTextArea.setText(gc.toonLeden());
        draaiboekBtn.setDisable(gc.draaiboekBeschikbaar());

    }

    private void setSelectedGroep(String groep) {
        gc.setSelectedGroep(groep);
        mc.setSelectedGroep(gc.getSelectedGroep());
        ac.setSelectedGroep(gc.getSelectedGroep());
    }

    private void toonMotivatie() {
        if (gc.getSelectedGroep() != null) {
            goedkeurenBtn.setDisable(false);
            afkeurenBtn.setDisable(false);
            motivatieTxtArea.setWrapText(true);
            motivatieTxtArea.setEditable(false); //motivatie field uneditable maken
            motivatieStatusLbl.setText(mc.geefMotivatieStatus());
            motivatieTxtArea.setText(mc.toonMotivatie());
            if (gc.MotivatieKeurbaar()) {
                feedbackTxtArea.setEditable(true);
            } else {
                goedkeurenBtn.setDisable(true);
                afkeurenBtn.setDisable(true);
                feedbackTxtArea.setEditable(false);

            }
        }
    }

    @FXML
    private void keurActieAf(ActionEvent event) {
        if (ac.actiesgekeurd()) {
            keurActieplan(false, actieDetailTxtArea.getText());
        } else {
            geefFeedbackWindow(false);
        }
    }

    @FXML
    private void keurActieGoed(ActionEvent event) {
        if (ac.actiesgekeurd()) {
            keurActieplan(true, actieDetailTxtArea.getText());
        } else {
            geefFeedbackWindow(true);
        }
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
        ta.promptTextProperty().setValue("Feedback is optioneel");
        dialog.getDialogPane().setContent(ta);

        dialog.showAndWait()
                .ifPresent(response -> {
                    actiefeedback = ta.getText().isEmpty() ? "Geen feedback" : ta.getText();
                    ac.keurActie(b, titel, actiefeedback);

                });

        gc.update();
        toonActies();
        toonActieHistoriek();

        actieDetailTxtArea.clear();
    }

    private void toonActies() {

        actieDetailTxtArea.clear();
        actiesListView.getItems().clear();
        if (!gc.actiesToegankelijk()) {
            keurActieAfBtn.setText("Afkeuren");
            keurActieGoed.setText("Goedkeuren");
            keurActieAfBtn.setDisable(true);
            keurActieGoed.setDisable(true);
            actieDetailTxtArea.clear();
            actieDetailTxtArea.setEditable(false);
            actiesListView.setItems(FXCollections.observableArrayList(ac.getActieNamenLijst()));
        }

        draaiboekBtn.setDisable(gc.draaiboekBeschikbaar());
        if (gc.getSelectedGroep().actiesGekeurd() && !gc.actieplanReedsGekeurd()) {
            actieButtonsSwitch();

        }
    }

    @FXML
    private void toonMotivatieHistoriek() {
        if (gc.getSelectedGroep() != null) {
            historiekTxtArea.setEditable(false);
            historiekTxtArea.setWrapText(true);
            historiekTxtArea.setText(mc.getMotivaties());
        }
    }

    @FXML
    private void toonActieDetail() {
        String actie = actiesListView.getSelectionModel().getSelectedItem();
        if (actie != null) {
            actieDetailTxtArea.setEditable(false);
            actieDetailTxtArea.setText(ac.toonDetailActie(actie));
            keurActieAfBtn.setDisable(ac.Actiekeurbaar(actie));
            keurActieGoed.setDisable(ac.Actiekeurbaar(actie));
        }
    }

    @FXML
    private void toonActieHistoriek() {
        historiekTxtArea.setEditable(false);
        historiekTxtArea.setWrapText(true);
        historiekTxtArea.setText(ac.getActieHistoriek());
    }

    @FXML
    private void toonDraaiboek(ActionEvent event) throws Exception {

        try {
            if (gc.getSelectedGroep() != null) {
                DraaiboekOverzichtController dc = new DraaiboekOverzichtController(new DraaiboekController(gc.getSelectedGroep(),
                        new GenericDaoJpa(Taak.class)),
                        gc.getLector());
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.setTitle("Draaiboek: ");
                Scene scene;
                scene = new Scene(dc);
                scene.getStylesheets().add("/gui/GiveADayStyle.css");

                stage.setScene(scene);
                stage.show();

            } else {
                throw new Exception("draaiboek is nog niet beschikbaar");
            }

        } catch (Exception e) {
            errorLbl.setText(e.getMessage());
        }

    }

    private void keurActieplan(boolean b, String feedback) {
        try {
            gc.keurActieplan(b, feedback);
            actieDetailTxtArea.setEditable(false);
            actieDetailTxtArea.clear();
            keurActieGoed.setDisable(true);
            keurActieAfBtn.setDisable(true);
            gc.update();
            toonActieHistoriek();
        } catch (IllegalArgumentException e) {
            errorLbl.setText("U moet een feedback ingeven");
        }
        
    }

    private void actieButtonsSwitch() {
        keurActieAfBtn.setText("Actieplan afkeuren");
        keurActieGoed.setText("Actieplan goedkeuren");

        actieDetailTxtArea.setEditable(true);
        actieDetailTxtArea.promptTextProperty().setValue("Geef hier je feedback op het globale actieplan in");
        keurActieAfBtn.setDisable(false);
        keurActieGoed.setDisable(false);
    }
}
