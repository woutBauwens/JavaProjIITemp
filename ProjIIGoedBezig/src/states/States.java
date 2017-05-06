/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package states;

/**
 *
 * @author kenne
 */
public enum States
    {
        empty{@Override
        public String toString(){return "empty";}},
        written{@Override
        public String toString(){return "written";}},
        pending{@Override
        public String toString(){return "pending";}},
        approved{@Override
        public String toString(){return "approved";}},
        denied{@Override
        public String toString(){return "denied";}},
        labeled{@Override
        public String toString(){return "labeled";}},
        actiegoedgekeurd{@Override
        public String toString(){return "actiegoedgekeurd";}},
        actiepending{@Override
        public String toString(){return "actiepending";}}
    };