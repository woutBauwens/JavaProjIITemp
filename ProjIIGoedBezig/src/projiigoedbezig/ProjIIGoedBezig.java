/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projiigoedbezig;

import Persistentie.LoginUser;
import Persistentie.SQLConnection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author BelgoBits
 */
public class ProjIIGoedBezig extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        connect();
        TextField username = new TextField("email");
        TextField password = new TextField("paswoord");
        Button btn = new Button();
        btn.setText("Login");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                try{
                    LoginUser user = new LoginUser(username.getText(), password.getText());
                    
                } catch (IllegalArgumentException e){
                    System.err.println(e.getMessage());
                }
            }
        });
        
        StackPane root = new StackPane();
        VBox login = new VBox(username, password, btn);
        root.getChildren().add(login);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    private void connect(){
        try{
            SQLConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Could not connect to the server: \n" + e.getMessage());
        } catch (ClassNotFoundException e){
            System.err.println(e.toString());
        }
    }
}
