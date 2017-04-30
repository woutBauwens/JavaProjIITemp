/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.ContactPersoon;

/**
 *
 * @author BelgoBits
 */
public interface PasswordDao {

    public boolean needsPassword(ContactPersoon lector) throws Exception;

    public void generatePassword(int lectorId, String value) throws Exception;
}
