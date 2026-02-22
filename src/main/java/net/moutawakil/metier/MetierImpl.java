package net.moutawakil.metier;

import net.moutawakil.dao.DaoImpl;
import net.moutawakil.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * @author admin
 **/

@Component
public class MetierImpl implements IMetier {

    private IDao dao;

    //public MetierImpl() {}

    public MetierImpl(@Qualifier("d2") IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double t = dao.getData();
        double t2 = t * t;
        return t2;
    }

    public void setDao(IDao dao) {
        this.dao = dao;
    }
}
