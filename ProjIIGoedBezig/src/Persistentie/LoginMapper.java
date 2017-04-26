/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistentie;

import domein.ContactPersoon;
import domein.Lector;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author kenne
 */
public class LoginMapper {

    private final EntityManager em;

    public LoginMapper() {
        em = SQLConnection.getManager();
    }

    public boolean checkLogin(String email, String password) throws Exception {
        ContactPersoon CPersoon;
        try {
            Query query = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email", ContactPersoon.class).setParameter("email", email);
            query.setFirstResult(0);
            query.setMaxResults(1);
            CPersoon = (ContactPersoon) query.getSingleResult();

//                   new Lector(
//                    em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email",
//                            ContactPersoon.class).setParameter("email", email).getSingleResult().getId());
        } catch (Exception ex) {
            return false; //gooit nullpointerexception for some reason

        }
        int UserId = CPersoon.getId();

        LoginUser user = em.createQuery("SELECT u FROM LoginUser u WHERE u.UserId = :id",
                LoginUser.class).setParameter("id", CPersoon.getId()).getSingleResult();
        return user.getPassword().equals(password);//true or false

    }

    public Lector getLector(String email) {
        Lector lector = (Lector) em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email", ContactPersoon.class).setParameter("email", email).getSingleResult();
        return lector;
    }

    public Lector getLectorAdhvUser(LoginUser user) {
       //
       Lector l = (Lector) em.createQuery("SELECT c from ContactPersoon c WHERE c.ContactPersoonId = :userId",ContactPersoon.class).setParameter("userId",user.getUserId()).getSingleResult();
       return l;
    }

}
