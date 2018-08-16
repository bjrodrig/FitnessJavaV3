package entity;

import com.sun.scenario.effect.Merge;

import javax.inject.Named;
import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "fooddiary", schema = "fitnessjava")
@NamedQueries({
        @NamedQuery(name = "FooddiaryEntity.findByUsernameAndDate", query="SELECT u FROM FooddiaryEntity u WHERE " +
                "u.username = :username and u.dateAdded =:dateAdded"),
        @NamedQuery(name = "FooddiaryEntity.findByUsernameDateMeal", query="SELECT u FROM FooddiaryEntity u WHERE " +
                "u.username = :username and u.dateAdded =:dateAdded and u.meal =:meal"),
        @NamedQuery(name = "FooddiaryEntity.findTotalCaloriesForUsernameDateMeal", query=
                "SELECT u.totalCalories FROM FooddiaryEntity u WHERE u.username = :username and " +
                "u.dateAdded =:dateAdded and u.meal =:meal"),
})
public class FooddiaryEntity {
    private Date dateAdded;
    private int id;
    private String meal;
    private Float caloriesPerItem;
    private Float quantity;
    private Float totalCalories;
    private FooddatabaseEntity foodItem;
    private UserEntity username;

    @Basic
    @Column(name = "dateAdded", nullable = true)
    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    @Id
    @Column(name = "id", nullable = false, length = 45)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "meal", nullable = true, length = 45)
    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    @Basic
    @Column(name = "caloriesPerItem", nullable = true, precision = 0)
    public Float getCaloriesPerItem() {
        return caloriesPerItem;
    }

    public void setCaloriesPerItem(Float caloriesPerItem) {
        this.caloriesPerItem = caloriesPerItem;
    }

    @Basic
    @Column(name = "quantity", nullable = true, precision = 0)
    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "totalCalories", nullable = true, length = 45)
    public Float getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories(Float totalCalories) {
        this.totalCalories = totalCalories;
    }

    @JoinColumn(name = "foodItem", referencedColumnName = "foodName", insertable=true, updatable = true)
    @ManyToOne(fetch=FetchType.LAZY)
    public FooddatabaseEntity getFoodItem() { return foodItem; }

    public void setFoodItem(FooddatabaseEntity foodItem) {
        this.foodItem = foodItem;
    }

    @JoinColumn(name = "username", referencedColumnName = "user", insertable=true, updatable = true)
    @ManyToOne(fetch=FetchType.LAZY)
    public UserEntity getUsername() {
        return username;
    }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FooddiaryEntity that = (FooddiaryEntity) o;

        if (dateAdded != null ? !dateAdded.equals(that.dateAdded) : that.dateAdded != null) return false;
        if (id != that.id) return false;
        if (meal != null ? !meal.equals(that.meal) : that.meal != null) return false;
        if (caloriesPerItem != null ? !caloriesPerItem.equals(that.caloriesPerItem) : that.caloriesPerItem != null)
            return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;
        if (totalCalories != null ? !totalCalories.equals(that.totalCalories) : that.totalCalories != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = dateAdded != null ? dateAdded.hashCode() : 0;
        result = 31 * result + (meal != null ? meal.hashCode() : 0);
        result = 31 * result + (caloriesPerItem != null ? caloriesPerItem.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        result = 31 * result + (totalCalories != null ? totalCalories.hashCode() : 0);
        return result;
    }
}
