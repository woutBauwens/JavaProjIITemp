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
 * @author BelgoBits
 */
public class PasswordDaoJpa extends GenericDaoJpa<LoginUser> implements PasswordDao {

    public PasswordDaoJpa() {
        super(LoginUser.class);
    }

    @Override
    public boolean hasPassword(ContactPersoon lector) throws Exception {
        try {
            if (lector.isLector()) {
                LoginUser index = em.createQuery("SELECT u from LoginUser u WHERE u.lector = :id",
                        LoginUser.class).setParameter("id", lector).getSingleResult();
                LoginUser u = em.find(LoginUser.class, lector.getId());
                if (index != null && u != null) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception ex) {
            return false; //gooit nullpointerexception for some reason
        }
    }

    @Override
    public void generatePassword(int lectorId, String value) throws Exception {
        String pass = String.valueOf(lectorId) + value;
        
        em.createNativeQuery("INSERT INTO LoginUser Value (:pass, :id)").setParameter("pass", pass).setParameter("id", lectorId).executeUpdate();
    }

}
