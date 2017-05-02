/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BelgoBits
 */
public class ConnectionReceiver extends SQLConnection implements Runnable {

    public ConnectionReceiver() throws Exception {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                connect();
            } catch (ClassNotFoundException | SQLException | InterruptedException ex) {
                Logger.getLogger(SQLConnection.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println("Connecting to server.");
                run();
            }
        }
    }
}
