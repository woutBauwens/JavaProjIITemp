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

    public void setLector(ContactPersoon lector) {
        this.lector = lector;
    }

    public ContactPersoon getLector() {
        return lector;
    }
    public Groep getSelectedGroep(){
        return selectedGroep;
    }

    public boolean checkLogin(String email, String password) throws Exception {
        if (loginMapper.checkLogin(email, password)) {
            setLector(loginMapper.getLector(email));
        }
        return loginMapper.checkLogin(email, password);
    }

    public void setUser(LoginUser user) {
        //hierin user omzetten naar Lector?
        this.user = user;
        setLector(loginMapper.getLectorAdhvUser(user));
    }

    public String toonMotivatie(Groep g) {

       return g.getHuidigeMotivatie().getTekst();
      
    }

    public void setFeedback(String response) {
        selectedGroep.getHuidigeMotivatie().setFeedback(response);
      groepMapper.bewaarFeedback(selectedGroep,response);
    }

    public List<Groep> getGroepenByLector() {
        return lector.getLector().getGroepenByLector();
    }

}
