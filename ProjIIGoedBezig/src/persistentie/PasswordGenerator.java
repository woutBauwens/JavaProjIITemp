/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.ContactPersoon;
import java.util.ArrayList;
import java.util.List;
import repository.GenericDao;
import repository.GenericDaoJpa;
import repository.PasswordDao;
import repository.PasswordDaoJpa;

/**
 *
 * @author BelgoBits
 */
public class PasswordGenerator {
    public static void generate() {
        GenericDao entities = new GenericDaoJpa(ContactPersoon.class);
        GenericDao users = new GenericDaoJpa(LoginUser.class);
        PasswordDao passEntities = new PasswordDaoJpa();
        List Contacten = entities.findAll();
        Contacten.forEach((c) -> {
            try {
                ContactPersoon contact = (ContactPersoon) c;
                if (passEntities.needsPassword(contact)) {
                    users.insert(new LoginUser(contact, String.valueOf(contact.getId()) + contact.passwordExtention()));
                }
            } catch (Exception e) {

            }
        });
    }
}
