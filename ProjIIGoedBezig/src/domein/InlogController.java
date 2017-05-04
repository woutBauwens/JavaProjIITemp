/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.LoginUser;
import repository.LoginDaoJpa;

/**
 *
 * @author Jonas
 */
public class InlogController {

    private ContactPersoon lector;
    private LoginDaoJpa loginRepo;
    
        public InlogController(LoginDaoJpa jp) {
        loginRepo = jp;
    }


    public boolean checkLogin(String email, String password) throws Exception {
        lector = loginRepo.CheckLogin(email, password);
        LoginUser user = loginRepo.get(lector.getId());
        return user.getPassword().equals(password);
    }

    public void setLector(ContactPersoon lector) {
        this.lector = lector;
    }

    public ContactPersoon getLector() {
        return lector;
    }

}
