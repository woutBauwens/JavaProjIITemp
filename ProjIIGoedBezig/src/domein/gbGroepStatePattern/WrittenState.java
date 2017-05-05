/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein.gbGroepStatePattern;

import java.util.function.Supplier;

/**
 *
 * @author BelgoBits
 */
public class WrittenState extends State {

    @Override
    public Supplier getSupplier(String name) {
        if (this.name.equals(name)) {
            return EmptyState::new;
        } else {
            State s = new WrittenState();
            return s.getSupplier(name);
        }
    }
}
