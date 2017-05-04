/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import persistentiee.ConnectionReceiver;
import persistentiee.SQLConnection;
import domein.InlogController;
import gui.LoginController;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.LoginDaoJpa;

/**
 *
 * @author kenne
 */
public class StartUp extends Application {

    
    @Override
    public void start(Stage primaryStage) {
        connect();
        InlogController dc = new InlogController(new LoginDaoJpa());
        LoginController root = new LoginController(dc);
        Scene scene = new Scene(root);
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
        } catch (Exception ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
