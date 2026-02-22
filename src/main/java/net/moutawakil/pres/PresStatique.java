package net.moutawakil.pres;

import net.moutawakil.dao.DaoImpl;
import net.moutawakil.ext.DaoImplV2;
import net.moutawakil.metier.MetierImpl;

/**
 * @author admin
 **/
public class PresStatique {
    public static void main(String[] args) {
//        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl(new DaoImplV2());
        System.out.println("RES=" + metier.calcul());
    }
}
