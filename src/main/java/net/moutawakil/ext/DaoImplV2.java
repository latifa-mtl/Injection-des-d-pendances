package net.moutawakil.ext;

import net.moutawakil.dao.IDao;

/**
 * @author admin
 **/
public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version 2");
        return 5;
    }
}
