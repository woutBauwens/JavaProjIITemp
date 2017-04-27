/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.ContactPersoon;

/**
 *
 * @author Jonas
 */
public interface LoginDao {
    public ContactPersoon CheckLogin(String email, String password) throws Exception ;
}
