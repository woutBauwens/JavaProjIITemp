package gui;

import domein.ContactPersoon;
import domein.GroepController;

import domein.InlogController;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import repository.*;

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

    private final InlogController dc;

    public LoginController(InlogController dc) {
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
        try {
            if (dc.checkLogin(userNameTxtField.getText(), passwordTxtField.getText())) {
                ContactPersoon lector = dc.getLector();
                GroepController gc = new GroepController(new GroepDaoJpa(), lector);
                GroepOverzichtController GOC = new GroepOverzichtController(gc);
                Stage stage = (Stage) (this.getScene().getWindow());
                stage.setTitle("Groepsoverzicht: ");
                Scene scene = new Scene(GOC);
                scene.getStylesheets().add("/gui/GiveADayStyle.css");
                
                stage.setScene(scene);
                stage.show();

            } else {
                throw new Exception("Het paswoord komt niet overeen met het email adress");
            }

        } catch (NullPointerException n) {
            errorLbl.setText("U heeft een ongeldig email adress ingevuld.");
        } catch (Exception e) {
            errorLbl.setText(e.getMessage());
        }
        //dc.setUser(); lector setten zodat kan meegegeven worden et dc naar volgend scherm
        // GroepOverzichtController GOC = new GroepOverzichtController(dc);
    }

}
