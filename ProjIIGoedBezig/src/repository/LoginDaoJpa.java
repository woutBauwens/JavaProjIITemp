/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import Persistentie.LoginUser;
import domein.ContactPersoon;

/**
 *
 * @author Jonas
 */
public class LoginDaoJpa extends GenericDaoJpa<LoginUser> implements LoginDao {

    public LoginDaoJpa(Class<LoginUser> type) {
        super(type);
    }

    @Override
    public ContactPersoon CheckLogin(String email, String password) throws Exception {
        ContactPersoon contact;
        try {
            int index = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email",
                    ContactPersoon.class).setParameter("email", email).getSingleResult().getId();
            contact= em.find(ContactPersoon.class, index);
        } catch (Exception ex) {
            return null; //gooit nullpointerexception for some reason

        }
       // LoginUser user = em.find(LoginUser.class, contact.getId());
//        LoginUser user = em.createQuery("SELECT u FROM LoginUser u WHERE u.UserId = :id",
//                LoginUser.class).setParameter("id", index).getSingleResult();
        //return user.getPassword().equals(password);//true or false
        return contact;
    }

}
