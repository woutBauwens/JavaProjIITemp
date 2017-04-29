/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;


import java.sql.*;
import javax.persistence.EntityManager;
import util.JPAUtil;

/** 
 *
 * @author BelgoBits
 */
public class SQLConnection {
    
    private static Connection con;
    private final String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GBDB;user=Login;password=1234";

    private static EntityManager em;

    
    private SQLConnection() throws SQLException, ClassNotFoundException{
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(url);
        em = JPAUtil.getEntityManagerFactory().createEntityManager();
        System.out.println("You are now connected to the server.");
     //   PasswordGenerator.generate();
    }
    
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(con == null)
            new SQLConnection();
        
        return con;
    }
    
    public static EntityManager getManager(){
        return em;
    }
}
