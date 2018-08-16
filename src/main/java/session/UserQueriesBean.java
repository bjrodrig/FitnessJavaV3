package session;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import entity.UserEntity;

import java.util.List;

@Stateless
public class UserQueriesBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "FitnessPU");
    EntityManager em = emf.createEntityManager();

    public void createUser(UserEntity user, String username, String password) {
        em.getTransaction().begin();
        user.setUser(username);
        user.setPassword(password);
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @PermitAll
    public List validateUser(String username) {
        em.getTransaction().begin();
        List query
                = (List) em.createNamedQuery("UserEntity.findByUsername").setParameter(
                        "user", username).getResultList();
        em.getTransaction().commit();
        return query;
    }

    public List validateUserLogin(String username, String password) {
        em.getTransaction().begin();
        List query
                = (List) em.createNamedQuery("UserEntity.findByUsernameAndPassword").setParameter(
                "user", username).setParameter("password", password).getResultList();
        em.getTransaction().commit();
        return query;
    }

}
