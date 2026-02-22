package net.moutawakil.dao;

/**
 * @author admin
 **/
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("Version BDD");
        double t=34;
        return t;
    }
}
