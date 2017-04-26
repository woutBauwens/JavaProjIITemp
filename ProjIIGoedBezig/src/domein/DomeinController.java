/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Persistentie.LoginMapper;
import Persistentie.LoginUser;
import Persistentie.MotivatieMapper;

/**
 *
 * @author kenne
 */
public class DomeinController {

    private Lector lector;
    private Groep selectedGroep;
    private LoginMapper loginMapper;
    private MotivatieMapper motivatieMapper;
    private LoginUser user;

    public DomeinController() {
        loginMapper = new LoginMapper();
        motivatieMapper = new MotivatieMapper();
    }

    public void setLector(Lector lector) {
        this.lector = lector;
    }

    public Lector getLector() {
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

    public String toonMotivatie(String groepsnaam) {

        Groep g = motivatieMapper.geefGroep(groepsnaam);
        selectedGroep = g;
        return g.getHuidigeMotivatie().getTekst();
    }

    public void setFeedback(String response) {
        selectedGroep.getHuidigeMotivatie().setFeedback(response);
        motivatieMapper.bewaarFeedback(selectedGroep,response);
    }

}
