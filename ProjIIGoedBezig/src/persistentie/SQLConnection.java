/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.ContactPersoon;
import java.sql.*;
import javax.persistence.EntityManager;
import repository.LoginDaoJpa;
import util.JPAUtil;

/**
 *
 * @author BelgoBits
 */
public class SQLConnection {

    private static Connection con;
    private static EntityManager em;

    public static ContactPersoon refreshLector(ContactPersoon lector) {
        em.refresh(lector);
        return lector;
    }
    private final String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GBDB;user=Login;password=1234";

    protected SQLConnection() throws SQLException, ClassNotFoundException {
        connect();
        PasswordGenerator.generate();
    }

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (con == null) {
            new SQLConnection();
        }

        return con;
    }

    public static EntityManager getManager() {
        return em;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        con = DriverManager.getConnection(url);
        em = JPAUtil.getEntityManagerFactory().createEntityManager();
    }

    protected ContactPersoon refresh(ContactPersoon lector) throws SQLException, ClassNotFoundException {
        try {
            return LoginDaoJpa.refresh();
        } catch (Exception e) {
            connect();
            return lector;
        }
    }
}
