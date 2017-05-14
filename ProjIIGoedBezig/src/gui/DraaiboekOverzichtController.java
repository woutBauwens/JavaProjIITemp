/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.ContactPersoon;
import domein.DraaiboekController;
import domein.Groep;
import domein.GroepController;
import domein.InlogController;
import domein.Taak;
import java.io.IOException;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import repository.CursistDaoJpa;
import repository.GenericDaoJpa;
import repository.LoginDaoJpa;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class DraaiboekOverzichtController extends GridPane {

    private DraaiboekController dc;
    private ContactPersoon lector;
    @FXML
    private ListView<String> actiesListview;
    @FXML
    private TableView<Taak> draaiboekTable;
    @FXML
    private TableColumn<Taak, String> wieColumn;
    @FXML
    private TableColumn<Taak, String> watColumn;
    @FXML
    private TableColumn<Taak, String> wanneerColumn;
    @FXML
    private TableColumn<Taak, String> realisatieColumn;
    @FXML
    private TableColumn<Taak, String> groepSturingColumn;
    @FXML
    private TableColumn<Taak, String> lectorSturingColumn;
    private TextArea feedbackTextArea;
    @FXML
    private Button terugBtn;
    @FXML
    private Button logoutBtn;
    private Label errorLbl;
    @FXML
    private Label draaiboeklbl;
    private final ObservableList<Taak> data
            = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    public DraaiboekOverzichtController(DraaiboekController dc, ContactPersoon lector) {
        this.dc = dc;
        this.lector = lector;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DraaiboekOverzicht.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        vulViewOp();
        draaiboeklbl.setText("Draaiboek van " + dc.getSelectedGroep().getNaam());
    }

    @FXML
    private void toonActieDraaiboek() {
        draaiboekTable.getItems().clear();
        List<Taak> taken = dc.geefTaken(actiesListview.getSelectionModel().getSelectedItem());
        for (Taak t : taken) {
            wieColumn.setCellValueFactory(new PropertyValueFactory("wie"));
            watColumn.setCellValueFactory(new PropertyValueFactory("wat"));
            wanneerColumn.setCellValueFactory(new PropertyValueFactory("wanneer"));
            realisatieColumn.setCellValueFactory(new PropertyValueFactory("realisatie"));
            groepSturingColumn.setCellValueFactory(new PropertyValueFactory("groepBijsturing"));
            lectorSturingColumn.setCellValueFactory(new PropertyValueFactory("lectorBijsturing"));
            lectorSturingColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            lectorSturingColumn.setEditable(true);
            lectorSturingColumn.setOnEditCommit((TableColumn.CellEditEvent<Taak, String> t1) -> {
                t1.getTableView().getItems().get(t1.getTablePosition().getRow()).setLectorBijsturing(t1.getNewValue());
                dc.setFeedbackTaak(t, t.getLectorBijsturing());
            });
            data.add(t);
            draaiboekTable.setItems(data);
            draaiboekTable.setEditable(true);
        }

    }

    private void vulViewOp() {
        actiesListview.setItems(FXCollections.observableArrayList(dc.getActies()));
    }

    private void keurDraaiBoek(boolean keuring) {

        dc.keurDraaiboek(keuring, feedbackTextArea.getText() == null ? "" : feedbackTextArea.getText(), actiesListview.getSelectionModel().getSelectedItem());
        feedbackTextArea.clear();
        toonActieDraaiboek();

    }

    @FXML
    private void naarGroepOverzicht(ActionEvent event) {
        try {

            GroepController gc = new GroepController(new GenericDaoJpa(Groep.class), new CursistDaoJpa(), lector);
            GroepOverzichtController GOC = new GroepOverzichtController(gc);
            Stage stage = (Stage) (this.getScene().getWindow());
            stage.setTitle("Groepsoverzicht: ");
            Scene scene = new Scene(GOC);
            scene.getStylesheets().add("/gui/GiveADayStyle.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            errorLbl.setText(e.getMessage());
        }

    }

    @FXML
    private void logOut(ActionEvent event) {

        try {

            InlogController dc = new InlogController(new LoginDaoJpa());
            LoginController loginC = new LoginController(dc);

            Stage stage = (Stage) (this.getScene().getWindow());
            stage.setTitle("Login: ");
            Scene scene = new Scene(loginC);
            scene.getStylesheets().add("/gui/GiveADayStyle.css");

            stage.setScene(scene);
            stage.show();

        } catch (Exception e) {
            errorLbl.setText(e.getMessage());
        }

    }

    @FXML
    private void enableFeedback(MouseEvent event) {
    }
}
