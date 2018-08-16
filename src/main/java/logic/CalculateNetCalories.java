package logic;

public class CalculateNetCalories {

    public Float calculateNetCalories(float BMR, String weightGoals, String gender) {
        Float changeInCaloriesNeeded = 0F;
        switch (weightGoals) {
            case "L2": changeInCaloriesNeeded = -1000F;
                break;
            case "L1": changeInCaloriesNeeded =  -500F;
                break;
            case "L.5": changeInCaloriesNeeded = -250F;
                break;
            case "M": changeInCaloriesNeeded = 0F;
                break;
            case "G.5": changeInCaloriesNeeded = 250F;
                break;
            case "G1": changeInCaloriesNeeded = 500F;
                break;
        }
        /* net calorie intake a day to meet weight goals. BMR is calories to maintain
        weight, thus, net calorie intake to meet goal is BMR + calories to cut/add a day
        to meet weight goal.
         */
        Float netCalories = BMR + changeInCaloriesNeeded;
        /* for health reasons, men should not eat less than 1500 calories a day
        and women should not eat less than 1200 calories a day.
         */
        if (gender.equals("M")) {
            netCalories = java.lang.Math.max(netCalories, 1500F);
        } else {
            netCalories = java.lang.Math.max(netCalories, 1200F);
        }
        return netCalories;
    }
}
