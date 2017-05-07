/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ActieController;
import domein.Activiteit;
import domein.Groep;
import domein.GroepController;
import domein.InlogController;
import domein.Motivatie;
import domein.MotivatieController;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
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
import repository.LoginDaoJpa;
import states.States;

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

    
    
    /*
        JPA en eclipseLink v2.1 zijn niet compatibel met lambda expressies
        Dit probleem is opgelost in v2.6 maar deze versie van JPA is niet functioneel met de manier hoe wij met JPA werken.
        Een manier om dit te omzeilen is door te werken met sublijsten van de volledige lijsten.
        Omdat er liever met streams gewerkt worden zal er altijd een tussenstab subList(...) tussen de streams zitten.
        
        Meer informatie op:
        http://stackoverflow.com/questions/31939827/why-does-this-stream-return-no-element
    */
    public GroepOverzichtController(GroepController gc) {
        this.gc = gc;
        mc = new MotivatieController();
        ac = new ActieController();
        List<Groep> groepen = gc.getGroepenByLector();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroepOverzicht.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        List<String> groepsnamen = groepen.stream().map(Groep::getNaam).collect(Collectors.toList());

        LectorLabel.setText(LectorLabel.getText() + gc.getLector().toString());
        groepListView.setItems(FXCollections.observableArrayList(groepsnamen));
        errorLbl.setVisible(false);
        actieDetailTxtArea.setEditable(false);
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
        mc.setKeuring(b);
        mc.setFeedback(feedbackTxtArea.getText());
        motivatieStatusLbl.setVisible(true);
        motivatieStatusLbl.setText(mc.geefMotivatieStatus());
        goedkeurenBtn.setDisable(true);
        afkeurenBtn.setDisable(true);
        feedbackTxtArea.setEditable(false);
        //eventueel feedback area clearen
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

        toonMotivatie();
        toonActies();
        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
            toonMotivatieHistoriek();
        }
        if (tabPane.getSelectionModel().getSelectedIndex() == 1) {
            toonActieHistoriek();
        }
        actiesTab.setDisable(gc.actiesToegankelijk());

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
                        ac.setFeedbackActie(titel, ta.getText());
                        ac.keurActie(b, titel);
                    } else {
                        ac.setFeedbackActie(titel, "Geen feedback");
                        ac.keurActie(b, titel);
                    }
                });
        toonActies();
        toonActieHistoriek();
        gc.update();
        actieDetailTxtArea.clear();

    }

    private void toonActies() {
        if (!gc.actiesToegankelijk()) {

            keurActieAfBtn.setDisable(true);
            keurActieGoed.setDisable(true);

//            List<Activiteit> acties = ac.getActies();
//            List<String> actienamen = new ArrayList<>();
//            acties.stream().filter(a -> a.getFeedback() == null).forEach(a -> actienamen.add(a.getTitel()));
//            for (Activiteit a : acties) {
//                if (a.getFeedback() == null) {
//                    actienamen.add(a.getTitel());
//                }
            //          }
            actiesListView.setItems(FXCollections.observableArrayList(ac.getActieNamenLijst()));
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

//    private void setMotivatieHistoriek() {
////        List<String> motivaties = mc.getMotivaties();
////        Collections.reverse(motivaties);
//        //list gereversed ipv telkens historiek te overschrijven en er opnieuw aan te plakken, same effect?
//
//        //     StringBuilder historiek = new StringBuilder();
////        motivaties.stream().filter(m -> m.isVerstuurd() && m.getFeedback() != null)
////                .forEach(m -> historiek.append("\n" + m.toString()));
//        historiek = mc.getMotivaties();
//
//        //als motivatie versturd is en geen feedback heeft => motivatie in historiek weergeven
////        
////        for (Motivatie m : motivaties) {
////            if (m.isVerstuurd() && m.getFeedback() != null) {
////                StringBuilder mot = new StringBuilder(m.toString());
////                historiek = mot.append("\n" + historiek);
////                //    historiek.append(m.toString()).append("\n");
////            }
////        }
//    }

    @FXML
    private void toonActieDetail() {
        String actie = actiesListView.getSelectionModel().getSelectedItem();
        if (actie != null) {
            keurActieAfBtn.setDisable(false);
            keurActieGoed.setDisable(false);
            actieDetailTxtArea.setEditable(false);
            actieDetailTxtArea.setText(ac.toonDetailActie(actie));
//            List<Activiteit> acties = ac.getActies();
            //state in .net is actiesgoedgekeurd of pending => alles tegelijk goed/afkeuren in .net.
            //hier alles apart goed of afkeuren => kunnen actiestates niet echt gebruiken...
            keurActieAfBtn.setDisable(ac.Actiekeurbaar(actie));
            keurActieGoed.setDisable(ac.Actiekeurbaar(actie));
            
//            acties.stream().filter(a -> a.getTitel().equals(actie))
//                    .forEach(a -> {
//                        if (a.getFeedback() != null) {
//                            keurActieAfBtn.setDisable(true);
//                            keurActieGoed.setDisable(true);
//                        } else {
//                            keurActieAfBtn.setDisable(false);
//                            keurActieGoed.setDisable(false);
//                        }
//                    });
            //buttons disablen / enablen afhankelijk van of de actie al gekeurd is geweest(dus voorzien van feedback)

//            for (Activiteit a : acties) {
//                if (a.getTitel().equals(actie)) {
//                    if (a.getFeedback() != null) {
//                        keurActieAfBtn.setDisable(true);
//                        keurActieGoed.setDisable(true);
//                    } else {
//                        keurActieAfBtn.setDisable(false);
//                        keurActieGoed.setDisable(false);
//                    }
//                }
//            }
        }
    }

    @FXML
    private void toonActieHistoriek() {
        if (!gc.actiesToegankelijk()) {
            historiekTxtArea.setEditable(false);
            historiekTxtArea.setWrapText(true);
            historiekTxtArea.setText(ac.getActieHistoriek());
        }
    }

}
