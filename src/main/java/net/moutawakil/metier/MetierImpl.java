package net.moutawakil.metier;

import net.moutawakil.dao.IDao;

/**
 * @author admin
 **/
public class MetierImpl implements IMetier {

    private IDao dao;

    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double t = dao.getData();
        double t2 = t * t;
        return t2;
    }
}
