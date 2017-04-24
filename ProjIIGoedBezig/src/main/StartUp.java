/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Persistentie.SQLConnection;
import gui.LoginController;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author kenne
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) {
        LoginController root = new LoginController();
        Scene scene = new Scene(root);
        connect();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Log in:");
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private void connect() {
        try {
            SQLConnection.getConnection();
        } catch (SQLException e) {
            System.err.println("Could not connect to the server: \n" + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println(e.toString());
        }
    }

}
