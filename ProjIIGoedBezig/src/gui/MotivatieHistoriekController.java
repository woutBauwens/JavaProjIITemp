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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class MotivatieHistoriekController extends GridPane {

    @FXML
    private ListView<String> motivatieHistoriekListV;
    @FXML
    private Button okBtn;
    @FXML
    private TextArea motivatieTxt;
    @FXML
    private TextArea feedbackTxt;

    private DomeinController dc;

    public MotivatieHistoriekController(DomeinController dc) {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MotivatieHistoriek.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Initializes the controller class
     * 
     */

    @FXML
    private void ToonDetails(MouseEvent event) {
        //klikken op 1 van de data in Listview toont Motivatie en historiek
    }

    @FXML
    private void TerugNaarGroepsOverzicht(ActionEvent event) {
//        GroepOverzichtController GOC = new GroepOverzichtController(new ArrayList<Groep>());
//        Stage stage = (Stage) (this.getScene().getWindow());
//        Scene scene = new Scene(GOC);
//
//        stage.setScene(scene);
//        stage.show();
    }

}
