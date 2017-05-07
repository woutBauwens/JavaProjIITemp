/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DraaiboekOverzichtController;
import domein.Taak;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class DraaiboekController extends GridPane {

    private DraaiboekOverzichtController dc;
    @FXML
    private ListView<String> actiesListview;
    @FXML
    private TableView<String> draaiboekTable;
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
    @FXML
    private TextArea feedbackTextArea;
    @FXML
    private Button goedkeurenBtn;
    @FXML
    private Button afkeurenBtn;

    /**
     * Initializes the controller class.
     */
    public DraaiboekController(DraaiboekOverzichtController dc) {
        this.dc = dc;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("GroepOverzicht.fxml"));
        loader.setRoot(this);
        loader.setController(this);

        vulViewOp();
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @FXML
    private void toonActieDraaiboek(MouseEvent event) {
//        String wie;
//        String wat;
//        String wanneer;
//        String realisatie;
//        String groepBijsturing;
//        String lectorBijsturing;
        Map<Integer, List<String>> taken = dc.getTaken(actiesListview.getSelectionModel().getSelectedItem());
        Set<Integer> keyset = taken.keySet();
        for (int s : keyset) {
            List<String> values = taken.get(s);
            wieColumn.setCellValueFactory(new PropertyValueFactory(values.get(0)));
            watColumn.setCellValueFactory(new PropertyValueFactory(values.get(1)));
            wanneerColumn.setCellValueFactory(new PropertyValueFactory(values.get(2)));
            realisatieColumn.setCellValueFactory(new PropertyValueFactory(values.get(3)));
            groepSturingColumn.setCellValueFactory(new PropertyValueFactory(values.get(4)));
            lectorSturingColumn.setCellValueFactory(new PropertyValueFactory(values.get(5)));
        }
        //  List<String> taken = dc.getTaken(actiesListview.getSelectionModel().getSelectedItem());
//        for (String s : taken) {
//            wieColumn.setCellValueFactory(new PropertyValueFactory(dc.getWie(s)));
//            watColumn.setCellValueFactory(new PropertyValueFactory(dc.getWat(s)));
//            wanneerColumn.setCellValueFactory(new PropertyValueFactory(dc.getWanneer(s)));
//            realisatieColumn.setCellValueFactory(new PropertyValueFactory(dc.getRealisatie(s)));
//            groepSturingColumn.setCellValueFactory(new PropertyValueFactory(dc.getGroepBijsturing(s)));
//            lectorSturingColumn.setCellValueFactory(new PropertyValueFactory(dc.getLectorBijsturing(s)));
//        }

    }

    @FXML
    private void keurDraaiboekGoed(ActionEvent event) {
    }

    @FXML
    private void keurDraaiboekAf(ActionEvent event) {
    }

    private void vulViewOp() {
        actiesListview.setItems(FXCollections.observableArrayList(dc.getActies()));
    }

}
