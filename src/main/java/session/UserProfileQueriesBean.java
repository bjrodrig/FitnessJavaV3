package session;

import entity.UserEntity;
import entity.UserprofileEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Date;

public class UserProfileQueriesBean {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "FitnessPU");
    EntityManager em = emf.createEntityManager();

    public void createUserProfile(UserprofileEntity userprofile, UserEntity user, Integer heightFt, Integer heightIn,
             Float weight, String gender, String weightGoal, double BMR, Float calorieGoalPerDay, String lifestyle,
             Date birthDate, Float changeInPoundsPerWeek, String gainOrLoss, String gainOrLose,
             Float fiveWeekProjection) {
        em.getTransaction().begin();
        userprofile.setHeightFt(heightFt);
        userprofile.setHeightIn(heightIn);
        userprofile.setWeight((double) weight);
        userprofile.setGender(gender);
        userprofile.setWeightGoal(weightGoal);
        userprofile.setBmr((double) BMR);
        userprofile.setCalorieGoalPerDay((double) calorieGoalPerDay);
        userprofile.setLifestyle(lifestyle);
        java.sql.Date sqlBirthDate = new java.sql.Date(birthDate.getTime());
        userprofile.setBirthDate((java.sql.Date) sqlBirthDate);
        userprofile.setChangeInPoundsPerWeek((double) changeInPoundsPerWeek);
        userprofile.setGainOrLoss(gainOrLoss);
        userprofile.setGainOrLose(gainOrLose);
        userprofile.setFiveWeekProjection(fiveWeekProjection);
        userprofile.setUsername(user);
        em.persist(userprofile);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    public UserprofileEntity retrieveUserProfileFromUsername(UserEntity user) {
        em.getTransaction().begin();
        UserprofileEntity query = (UserprofileEntity) em.createNamedQuery("UserprofileEntity.findByUsername")
                .setParameter("username", user).getSingleResult();
        em.getTransaction().commit();
        return query;
    }
}
