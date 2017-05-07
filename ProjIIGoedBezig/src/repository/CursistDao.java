/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import domein.Cursist;
import java.util.List;

/**
 *
 * @author kenne
 */
public interface CursistDao {
        public List<Cursist> getCursistenByGroep(int groepId);
}
