/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import Persistentie.LoginUser;

/**
 *
 * @author BelgoBits
 */
public class PasswordDaoJpa extends GenericDaoJpa<LoginUser> implements PasswordDao {

    public PasswordDaoJpa() {
        super(LoginUser.class);
    }
    
    @Override
    public boolean hasPassword(int lectorId) throws Exception {
        try {
            LoginUser index = em.createQuery("SELECT u from LoginUser u WHERE u.lector = :id",
                    LoginUser.class).setParameter("id", lectorId).getSingleResult();
            LoginUser u = em.find(LoginUser.class, lectorId);
            if(index != null && u != null)
                return true;
            else
                return false;
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
