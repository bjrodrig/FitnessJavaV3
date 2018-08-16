package session;

import controller.FoodDatabaseServlet;
import entity.FooddatabaseEntity;
import entity.FooddiaryEntity;
import entity.UserEntity;
import org.hibernate.annotations.Target;

import javax.ejb.Stateless;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.lang.annotation.ElementType;
import java.util.Date;
import java.util.List;

@Stateless
public class FooddiaryQueries {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "FitnessPU");
    EntityManager em = emf.createEntityManager();

    public void createFoodDiaryEntry(Date today, String meal, Float caloriesPerItem,
            Float quantity, Float totalCalories, FooddatabaseEntity FooddatabaseItem, UserEntity username) {
        em = emf.createEntityManager();
        em.getTransaction().begin();
        FooddiaryEntity foodItem = new FooddiaryEntity();
        java.sql.Date sqlDateAdded = new java.sql.Date(today.getTime());
        foodItem.setDateAdded((java.sql.Date) sqlDateAdded);
        foodItem.setMeal(meal);
        foodItem.setCaloriesPerItem(caloriesPerItem);
        foodItem.setQuantity(quantity);
        foodItem.setTotalCalories(totalCalories);
        foodItem.setFoodItem(FooddatabaseItem);
        foodItem.setUsername(username);
        em.persist(foodItem);
        em.getTransaction().commit();
        em.close();
    }

    public List<FooddiaryEntity> retrieveFooddiaryItemsByUsernameAndDate(UserEntity user, Date dateAdded) {
        EntityManager em = emf.createEntityManager();
        /*em.getTransaction().begin();*/
        List query = (List) em.createNamedQuery("FooddiaryEntity.findByUsernameAndDate").setParameter(
                "username", user).setParameter("dateAdded", dateAdded).getResultList();
        /*em.getTransaction().commit();*/
        return query;
    }

    public List<FooddiaryEntity> retrieveFooddiaryItemsByUsernameDateMeal(UserEntity user, Date dateAdded, String
            meal) {
        EntityManager em = emf.createEntityManager();
        return (List<FooddiaryEntity>) em.createNamedQuery("FooddiaryEntity.findByUsernameDateMeal").setParameter(
                "username", user).setParameter("dateAdded", dateAdded).setParameter("meal", meal)
                .getResultList();
    }

    public Float retrieveSumOfCaloriesByUsernameAndDate(UserEntity user, Date dateAdded) {
        EntityManager em = emf.createEntityManager();
        List<FooddiaryEntity> foodDiaryItems = retrieveFooddiaryItemsByUsernameAndDate(user, dateAdded);
        Float totalCalories = 0.0f;
        for (int i=0; i < foodDiaryItems.size(); i++) {
            totalCalories += foodDiaryItems.get(i).getTotalCalories();
        }
        return totalCalories;
    }

    public Float retrieveSumOfCaloriesByUsernameDateAndMeal(UserEntity user, Date dateAdded, String meal) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Float> totalCaloriesList = em.createNamedQuery("FooddiaryEntity" +
                ".findTotalCaloriesForUsernameDateMeal")
                .setParameter("username", user).setParameter("dateAdded", dateAdded).setParameter(
                        "meal", meal).getResultList();
        Float totalCalories = 0.0f;
        if (!totalCaloriesList.isEmpty()) {
            for (int i = 0; i < totalCaloriesList.size(); i++) {
                totalCalories += totalCaloriesList.get(i);
            }
        }
        em.getTransaction().commit();
        return totalCalories;
    }

    public void deleteFoodDiaryEntry(Integer id) {
        EntityManager em = emf.createEntityManager();
        FooddiaryEntity fooddiaryEntityInstance = em.find(FooddiaryEntity.class, id);
        em.getTransaction().begin();
        em.remove(fooddiaryEntityInstance);
        em.getTransaction().commit();
        em.close();
    }

}
