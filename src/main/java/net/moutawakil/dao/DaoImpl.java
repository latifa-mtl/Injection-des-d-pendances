package net.moutawakil.dao;

import org.springframework.stereotype.Component;

/**
 * @author admin
 **/

@Component
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Version BDD");
        double t=34;
        return t;
    }
}
