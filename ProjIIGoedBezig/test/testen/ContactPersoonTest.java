/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testen;

import domein.ContactPersoon;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 *
 * @author kenne
 */
public class ContactPersoonTest {
    @Mock
    ContactPersoon cp;
    
    @Test
    public void passwordExtentionMaaktJuistePasswoord(){
        Mockito.when(cp.getId()).thenReturn(2);
        //pointless om te testen wegens  == tostring?
    }
}
