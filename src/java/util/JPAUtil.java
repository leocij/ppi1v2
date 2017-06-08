
package util;

import javax.faces.bean.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class JPAUtil {

    private static EntityManagerFactory emf;

    public static EntityManager getManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("ppi1v2PU");
        }
        return emf.createEntityManager();
    }

    public static void fecharEntityManager(EntityManager manager) {
        try {
            manager.close();
        } catch (Exception ex) {
           throw ex;
        }
    }
}
