/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

/**
 *
 * @author BelgoBits
 */
public interface PasswordDao {

    public boolean hasPassword(int lectorId) throws Exception;

    public void generatePassword(int lectorId, String value) throws Exception;
}
