package session;

import entity.FooddatabaseEntity;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

@Stateless
public class FoodDatabaseQueries {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "FitnessPU");
    EntityManager em = emf.createEntityManager();

    public void createFoodDatabaseItem(String foodName, Float servingSize, String
            servingUnit, Float calories) {
        EntityManager em = emf.createEntityManager();
        FooddatabaseEntity foodItem = new FooddatabaseEntity();
        em.getTransaction().begin();
        foodItem.setFoodName(foodName);
        foodItem.setServingSize(servingSize);
        foodItem.setServingUnit(servingUnit);
        foodItem.setCalories(calories);
        em.persist(foodItem);
        em.getTransaction().commit();
    }

    public List<FooddatabaseEntity> returnAllFoodDatabaseItems() {
        em.getTransaction().begin();
        List query =  (List) em.createNamedQuery("FooddatabaseEntity.findAll").getResultList();
        em.getTransaction().commit();
        return query;
    }

    @PermitAll
    public List validateFoodName(String foodName) {
        em.getTransaction().begin();
        List query
                = (List) em.createNamedQuery("FooddatabaseEntity.findByFoodName").setParameter(
                "foodName", foodName).getResultList();
        em.getTransaction().commit();
        return query;
    }

    public FooddatabaseEntity retrieveFoodItem(String foodName) {
        em.getTransaction().begin();
        FooddatabaseEntity query =  (FooddatabaseEntity) em.createNamedQuery("FooddatabaseEntity.findByFoodName")
                    .setParameter("foodName", foodName).getSingleResult();
        em.getTransaction().commit();
        return query;
    }


}
