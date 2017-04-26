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
    private int index;
    public LoginMapper() {
        em = SQLConnection.getManager();
    }

    public boolean checkLogin(String email, String password) throws Exception {
        ContactPersoon CPersoon;
        try {
               // index =(int) em.createQuery("SELECT c.ContactPersoonId FROM ContactPersoon c WHERE c.EmailContactPersoon = :email").setParameter("email", email).getSingleResult();
           
                    index= em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email",
                           ContactPersoon.class).setParameter("email", email).getSingleResult().getId();
           CPersoon = em.find(ContactPersoon.class, index);
        } catch (Exception ex) {
            return false; //gooit nullpointerexception for some reason

        }
        LoginUser user = em.find(LoginUser.class,CPersoon.getId());
//        LoginUser user = em.createQuery("SELECT u FROM LoginUser u WHERE u.UserId = :id",
//                LoginUser.class).setParameter("id", index).getSingleResult();
        return user.getPassword().equals(password);//true or false

    }

    public ContactPersoon getLector(String email) {
       ContactPersoon lector =  em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email", ContactPersoon.class).setParameter("email", email).getSingleResult();
        return lector;
    }

    public ContactPersoon getLectorAdhvUser(LoginUser user) {
        //
        ContactPersoon l = em.createQuery("SELECT c from ContactPersoon c WHERE c.ContactPersoonId = :userId", ContactPersoon.class).setParameter("userId", user.getUserId()).getSingleResult();
        return l;
    }

}
