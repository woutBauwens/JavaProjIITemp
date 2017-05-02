/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.Activiteit;
import domein.ContactPersoon;
import domein.Groep;
import domein.Lector;
import domein.Motivatie;
import java.sql.*;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.LoginDao;
import repository.LoginDaoJpa;
import util.JPAUtil;

/**
 *
 * @author BelgoBits
 */
public class SQLConnection {

    private static Connection con;

    public static ContactPersoon refreshLector(ContactPersoon lector) {
        em.refresh(lector);
        return lector;
    }
    private final String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=GBDB;user=Login;password=1234";

    private static EntityManager em;

    protected SQLConnection() throws SQLException, ClassNotFoundException {
        connect();
        System.out.println("You are now connected to the server.");
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
            em.getTransaction().begin();
            em.refresh(em.find(lector.getClass(), lector.getId()));
            em.getTransaction().commit();
            return em.find(lector.getClass(), lector.getId());
        } catch (Exception e) {
            connect();
            return lector;
        }
    }
}
