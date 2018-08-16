package entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "userprofile", schema = "fitnessjava")
@NamedQueries({
        @NamedQuery(name = "UserprofileEntity.findByUsername", query="SELECT u FROM UserprofileEntity u WHERE " +
                "u.username = :username")
})
public class UserprofileEntity {
    private int id;
    private Integer heightFt;
    private Integer heightIn;
    private Double weight;
    private String gender;
    private String weightGoal;
    private Double bmr;
    private Double calorieGoalPerDay;
    private String lifestyle;
    private Date birthDate;
    private Double changeInPoundsPerWeek;
    private String gainOrLoss;
    private String gainOrLose;
    private Float fiveWeekProjection;
    private UserEntity username;
    private Collection<FooddiaryEntity> foodDiaryEntityCollection;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "heightFt", nullable = true)
    public Integer getHeightFt() {
        return heightFt;
    }

    public void setHeightFt(Integer heightFt) {
        this.heightFt = heightFt;
    }

    @Basic
    @Column(name = "heightIn", nullable = true)
    public Integer getHeightIn() {
        return heightIn;
    }

    public void setHeightIn(Integer heightIn) {
        this.heightIn = heightIn;
    }

    @Basic
    @Column(name = "weight", nullable = true, precision = 0)
    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = 1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "weightGoal", nullable = true, length = 45)
    public String getWeightGoal() {
        return weightGoal;
    }

    public void setWeightGoal(String weightGoal) {
        this.weightGoal = weightGoal;
    }

    @Basic
    @Column(name = "BMR", nullable = true, precision = 0)
    public Double getBmr() {
        return bmr;
    }

    public void setBmr(Double bmr) {
        this.bmr = bmr;
    }

    @Basic
    @Column(name = "calorieGoalPerDay", nullable = true, precision = 0)
    public Double getCalorieGoalPerDay() {
        return calorieGoalPerDay;
    }

    public void setCalorieGoalPerDay(Double calorieGoalPerDay) {
        this.calorieGoalPerDay = calorieGoalPerDay;
    }

    @Basic
    @Column(name = "lifestyle", nullable = true, length = 45)
    public String getLifestyle() {
        return lifestyle;
    }

    public void setLifestyle(String lifestyle) {
        this.lifestyle = lifestyle;
    }

    @Basic
    @Column(name = "birthDate", nullable = true)
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Basic
    @Column(name = "changeInPoundsPerWeek", nullable = true, precision = 0)
    public Double getChangeInPoundsPerWeek() {
        return changeInPoundsPerWeek;
    }

    public void setChangeInPoundsPerWeek(Double changeInPoundsPerWeek) {
        this.changeInPoundsPerWeek = changeInPoundsPerWeek;
    }

    @Basic
    @Column(name = "gainOrLoss", nullable = true, length = 45)
    public String getGainOrLoss() {
        return gainOrLoss;
    }

    public void setGainOrLoss(String gainOrLoss) {
        this.gainOrLoss = gainOrLoss;
    }

    @Basic
    @Column(name = "gainOrLose", nullable = true, length = 45)
    public String getGainOrLose() {
        return gainOrLose;
    }

    public void setGainOrLose(String gainOrLose) {
        this.gainOrLose = gainOrLose;
    }

    @Basic
    @Column(name = "fiveWeekProjection", nullable = true, length = 45)
    public Float getFiveWeekProjection() {
        return fiveWeekProjection;
    }

    public void setFiveWeekProjection(Float fiveWeekProjection) {
        this.fiveWeekProjection = fiveWeekProjection;
    }


    @OneToOne()
    @JoinColumn(name = "username", referencedColumnName = "user", unique=true, nullable = false)
    public UserEntity getUsername() { return username; }

    public void setUsername(UserEntity username) {
        this.username = username;
    }

    @OneToMany(targetEntity=FooddiaryEntity.class, mappedBy = "username")
    public Collection<FooddiaryEntity> getFoodDiaryEntityCollection() {
        return foodDiaryEntityCollection;
    }

    public void setFoodDiaryEntityCollection(Collection<FooddiaryEntity> foodDiaryEntityCollection) {
        this.foodDiaryEntityCollection = foodDiaryEntityCollection;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserprofileEntity that = (UserprofileEntity) o;

        if (id != that.id) return false;
        if (heightFt != null ? !heightFt.equals(that.heightFt) : that.heightFt != null) return false;
        if (heightIn != null ? !heightIn.equals(that.heightIn) : that.heightIn != null) return false;
        if (weight != null ? !weight.equals(that.weight) : that.weight != null) return false;
        if (gender != null ? !gender.equals(that.gender) : that.gender != null) return false;
        if (weightGoal != null ? !weightGoal.equals(that.weightGoal) : that.weightGoal != null) return false;
        if (bmr != null ? !bmr.equals(that.bmr) : that.bmr != null) return false;
        if (calorieGoalPerDay != null ? !calorieGoalPerDay.equals(that.calorieGoalPerDay) : that.calorieGoalPerDay != null)
            return false;
        if (lifestyle != null ? !lifestyle.equals(that.lifestyle) : that.lifestyle != null) return false;
        if (birthDate != null ? !birthDate.equals(that.birthDate) : that.birthDate != null) return false;
        if (changeInPoundsPerWeek != null ? !changeInPoundsPerWeek.equals(that.changeInPoundsPerWeek) : that.changeInPoundsPerWeek != null)
            return false;
        if (gainOrLoss != null ? !gainOrLoss.equals(that.gainOrLoss) : that.gainOrLoss != null) return false;
        if (gainOrLose != null ? !gainOrLose.equals(that.gainOrLose) : that.gainOrLose != null) return false;
        if (fiveWeekProjection != null ? !fiveWeekProjection.equals(that.fiveWeekProjection) : that.fiveWeekProjection != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (heightFt != null ? heightFt.hashCode() : 0);
        result = 31 * result + (heightIn != null ? heightIn.hashCode() : 0);
        result = 31 * result + (weight != null ? weight.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (weightGoal != null ? weightGoal.hashCode() : 0);
        result = 31 * result + (bmr != null ? bmr.hashCode() : 0);
        result = 31 * result + (calorieGoalPerDay != null ? calorieGoalPerDay.hashCode() : 0);
        result = 31 * result + (lifestyle != null ? lifestyle.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + (changeInPoundsPerWeek != null ? changeInPoundsPerWeek.hashCode() : 0);
        result = 31 * result + (gainOrLoss != null ? gainOrLoss.hashCode() : 0);
        result = 31 * result + (gainOrLose != null ? gainOrLose.hashCode() : 0);
        result = 31 * result + (fiveWeekProjection != null ? fiveWeekProjection.hashCode() : 0);
        return result;
    }
}
