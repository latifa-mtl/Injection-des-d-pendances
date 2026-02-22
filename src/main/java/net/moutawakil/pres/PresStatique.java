package net.moutawakil.pres;

import net.moutawakil.dao.DaoImpl;
import net.moutawakil.metier.MetierImpl;

/**
 * @author admin
 **/
public class PresStatique {
    public static void main(String[] args) {
//        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl(new DaoImpl());
        System.out.println("RES=" + metier.calcul());
    }
}
