/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;


import java.sql.*;
import javax.xml.transform.Result;

/** 
 *
 * @author BelgoBits
 */
public class SQLConnection {
    
    private static Connection con;
    private final String url = "jdbc:sqlserver://LocalHost:1433;DatabaseName=GBDB;user=Login;password=1234";
    
    private SQLConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(url);
        System.out.println("You are now connected to the server.");
    }
    
    public static Connection connect() throws SQLException, ClassNotFoundException{
        if(con == null)
            new SQLConnection();
        
        return con;
    }
}
