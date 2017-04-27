/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.GroepMapper;
import Persistentie.LoginMapper;
import Persistentie.LoginUser;
import java.util.List;

/**
 *
 * @author kenne
 */
public class DomeinController {

    private ContactPersoon lector;
    private Groep selectedGroep;
    private LoginMapper loginMapper;
    private GroepMapper groepMapper;
    private LoginUser user;

    public DomeinController() {
        loginMapper = new LoginMapper();
        groepMapper = new GroepMapper();
    }




//   
//    public void setUser(LoginUser user) {
//        //hierin user omzetten naar Lector?
//        this.user = user;
//        setLector(loginMapper.getLectorAdhvUser(user));
//    }






}
