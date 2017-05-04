/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import persistentiee.LoginUser;
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
            int indexnr = em.createNamedQuery("ContactPersoon.CheckLogin",ContactPersoon.class).setParameter("email", email).getSingleResult().getId();
            lector = em.find(ContactPersoon.class,indexnr);
                    } catch (Exception ex) {
           throw new Exception("Ongeldige Login");

        }
            return lector;
        
        
        
        
//        
//        
//        try {
//            int index = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email",
//                    ContactPersoon.class).setParameter("email", email).getSingleResult().getId();
//            lector = em.find(ContactPersoon.class, index);
//        } catch (Exception ex) {
//           throw new Exception("Ongeldige Login");
//
//        }
        
////        try {
////                List<ContactPersoon> lectorMMapped = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email",
////                        ContactPersoon.class).setParameter("email", email).getResultList();
////                int index = em.createQuery("SELECT c from ContactPersoon c WHERE c.EmailContactPersoon = :email",
////                        ContactPersoon.class).setParameter("email", email).getResultList().get(0).getId();
////                lector = em.find(ContactPersoon.class, index);
////                for(ContactPersoon c: lectorMMapped){
////                    lector.getGroepen().addAll(c.getGroepen());
////                }
////            } catch (Exception e) {
////                return null; //gooit nullpointerexception for some reason
////            }
//            // LoginUser user = em.find(LoginUser.class, contact.getId());
////        LoginUser user = em.createQuery("SELECT u FROM LoginUser u WHERE u.UserId = :id",
////                LoginUser.class).setParameter("id", index).getSingleResult();
//            //return user.getPassword().equals(password);//true or false
//            return lector;
    }

    public static ContactPersoon refresh() {
        em.getTransaction().begin();
        em.refresh(em.find(lector.getClass(), lector.getId()));
        em.getTransaction().commit();
        return em.find(lector.getClass(), lector.getId());
    }
}
