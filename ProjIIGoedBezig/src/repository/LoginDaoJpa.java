/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import persistentie.LoginUser;
import domein.ContactPersoon;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

/**
 *
 * @author Jonas
 */
public class LoginDaoJpa extends GenericDaoJpa<LoginUser> implements LoginDao {

    private static ContactPersoon lector;

    public LoginDaoJpa() {
        super(LoginUser.class);
    }

    @Override
    public ContactPersoon CheckLogin(String email, String password) throws Exception {

        try {
            int indexnr = em.createNamedQuery("ContactPersoon.CheckLogin", ContactPersoon.class).setParameter("email", email).getSingleResult().getId();
            lector = em.find(ContactPersoon.class, indexnr);
        } catch (Exception ex) {
            throw new Exception("Ongeldige Login");

        }
        return lector;
    }

    public static ContactPersoon refresh() {
        em.getTransaction().begin();
        em.refresh(em.find(lector.getClass(), lector.getId()));
        em.getTransaction().commit();
        return em.find(lector.getClass(), lector.getId());
    }
}
