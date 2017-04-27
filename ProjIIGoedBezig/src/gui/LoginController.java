/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import Persistentie.LoginUser;
import domein.DomeinController;
import domein.Lector;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kenne
 */
public class LoginController extends Pane {

    @FXML
    private TextField userNameTxtField;
    @FXML
    private TextField passwordTxtField;
    @FXML
    private Button logInButton;
    @FXML
    private Label errorLbl;

    private final DomeinController dc;

    public LoginController(DomeinController dc) {
        this.dc = dc;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        try {
            loader.load();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }


    @FXML
    private void LogIn(ActionEvent event) {
        String email = userNameTxtField.getText();
        String pass = passwordTxtField.getText();
        try {
            if (dc.checkLogin(userNameTxtField.getText(), passwordTxtField.getText())) {

                GroepOverzichtController GOC = new GroepOverzichtController(dc);
                LoginUser user = new LoginUser(dc.getLector());
                dc.setUser(user);
                Stage stage = (Stage) (this.getScene().getWindow());
                Scene scene = new Scene(GOC);

                stage.setScene(scene);
                stage.show();

            } else {
                throw new Exception("Ongeldige Login");
            }
            //  LoginUser user = new LoginUser(userNameTxtField.getText(), passwordTxtField.getText());

            //OverViewScreen(user);//controller volgende scherm
        } catch (Exception e) {
            errorLbl.setText(e.getMessage());
        }
        //dc.setUser(); lector setten zodat kan meegegeven worden et dc naar volgend scherm
        // GroepOverzichtController GOC = new GroepOverzichtController(dc);
    }

}
