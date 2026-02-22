package net.moutawakil.ext;

import net.moutawakil.dao.IDao;
import org.springframework.stereotype.Component;

/**
 * @author admin
 **/

@Component("d2")
public class DaoImplV2 implements IDao {

    @Override
    public double getData() {
        System.out.println("Version 2");
        return 5;
    }
}
