/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class DraaiboekController implements Initializable {

    @FXML
    private ListView<?> actiesListview;
    @FXML
    private TableView<?> draaiboekTable;
    @FXML
    private TableColumn<?, ?> wieColumn;
    @FXML
    private TableColumn<?, ?> watColumn;
    @FXML
    private TableColumn<?, ?> wanneerColumn;
    @FXML
    private TableColumn<?, ?> realisatieColumn;
    @FXML
    private TableColumn<?, ?> groepSturingColumn;
    @FXML
    private TableColumn<?, ?> lectorSturingColumn;
    @FXML
    private Label draaiboeklbl;
    @FXML
    private TextArea feedbackTextArea;
    @FXML
    private Button verzendBtn;
    @FXML
    private Button terugBtn;
    @FXML
    private Button logoutBtn;
    @FXML
    private Label errorLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void toonActieDraaiboek(MouseEvent event) {
    }

    @FXML
    private void setFeedbackTaak(ActionEvent event) {
    }

    @FXML
    private void naarGroepOverzicht(ActionEvent event) {
    }

    @FXML
    private void logOut(ActionEvent event) {
    }


}
