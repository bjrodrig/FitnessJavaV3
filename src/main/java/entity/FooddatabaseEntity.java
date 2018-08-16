package entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

@Entity
@Table(name = "fooddatabase", schema = "fitnessjava")
@NamedQueries({
        @NamedQuery(name="FooddatabaseEntity.findAll",
                query="SELECT u FROM FooddatabaseEntity u"),
        @NamedQuery(name = "FooddatabaseEntity.findByFoodName",
                query="SELECT u FROM FooddatabaseEntity u WHERE u.foodName = :foodName"),
})
public class FooddatabaseEntity {
    private String foodName;
    private Float calories;
    private Float servingSize;
    private String servingUnit;
    private Collection<FooddiaryEntity> foodDiaryCollection;

    @Id
    @Column(name = "foodName", nullable = false, length = 45)
    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    @Basic
    @Column(name = "calories", nullable = true, precision = 0)
    public Float getCalories() {
        return calories;
    }

    public void setCalories(Float calories) {
        this.calories = calories;
    }

    @Basic
    @Column(name = "servingSize", nullable = true, precision = 0)
    public Float getServingSize() {
        return servingSize;
    }

    public void setServingSize(Float servingSize) {
        this.servingSize = servingSize;
    }

    @Basic
    @Column(name = "servingUnit", nullable = true, length = 45)
    public String getServingUnit() {
        return servingUnit;
    }

    public void setServingUnit(String servingUnit) {
        this.servingUnit = servingUnit;
    }

    @OneToMany(targetEntity=FooddiaryEntity.class, fetch=FetchType.LAZY, mappedBy = "foodItem")
    public Collection<FooddiaryEntity> getFoodDiaryCollection() {
        return foodDiaryCollection;
    }

    public void setFoodDiaryCollection(Collection<FooddiaryEntity> foodDiaryCollection) {
        this.foodDiaryCollection = foodDiaryCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FooddatabaseEntity that = (FooddatabaseEntity) o;

        if (foodName != null ? !foodName.equals(that.foodName) : that.foodName != null) return false;
        if (calories != null ? !calories.equals(that.calories) : that.calories != null) return false;
        if (servingSize != null ? !servingSize.equals(that.servingSize) : that.servingSize != null) return false;
        if (servingUnit != null ? !servingUnit.equals(that.servingUnit) : that.servingUnit != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = foodName != null ? foodName.hashCode() : 0;
        result = 31 * result + (calories != null ? calories.hashCode() : 0);
        result = 31 * result + (servingSize != null ? servingSize.hashCode() : 0);
        result = 31 * result + (servingUnit != null ? servingUnit.hashCode() : 0);
        return result;
    }
}
