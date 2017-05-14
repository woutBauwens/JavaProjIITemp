/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author BelgoBits
 */

@Entity
public class Organisatie implements Serializable {
    
    @Id
    private int OrganisatieId;
    
    private String Email;
    private String EmailExtentie;
    private String Locatie;
    private String Naam;
    private boolean heeftLabel;
    
    private Organisatie(){
    }
}
