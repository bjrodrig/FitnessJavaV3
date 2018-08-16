package logic;

import java.util.Date;
import logic.ParseDates;

import static java.lang.StrictMath.round;

public class CalculateBMR {

    private Float height;
    private Float weight;
    private Float age;
    private Float BMR;
    public DateTime dateTime;

    public CalculateBMR(Integer heightFt, Integer heightIn, Float weight, Date birthDate,
                 String gender, String lifestyle) {
        this.height = convertHeightToCm(heightFt, heightIn);
        this.weight = convertWeightToKg(weight);
        this.age = calculateAgeInYears(birthDate);
        this.BMR = calculateBMR(this.height, this.weight, this.age, gender, lifestyle);
    }

    public Float getBMR() {
        return BMR;
    }

    public Float convertHeightToCm(Integer heightFt, Integer heightIn) {
        Integer totalHeight = heightFt * 12 + heightIn;
        return 2.54F * (float) totalHeight;
    }

    public Float convertWeightToKg(Float weight) {
        return (float) (weight / 2.2);
    }

    public Float calculateAgeInYears(Date birthDate) {
        Date today = new Date();
        long diffInMillies = today.getTime() - birthDate.getTime();
        // convert to age in years
        long ageInSeconds = diffInMillies / 1000;
        double ageInYears = round(ageInSeconds / (60.0 * 60.0 * 24.0 * 365.0) * 100.0) / 100.0;
        return (float) ageInYears;
    }

    public Float calculateBMR(Float height, Float weight, Float age, String gender, String lifestyle) {
        Float BMR = (float) (10 * weight + 6.25 * height - 5 * age);
        if (gender.equals("M")) {
            BMR += 5;
        }
        else {
            BMR -= 161;
        }
        Float BMRFactor = 0F;
        switch (lifestyle) {
            case "S": BMRFactor = 1.2F;
                    break;
            case "LA": BMRFactor =  1.375F;
                    break;
            case "A": BMRFactor = 1.55F;
                    break;
            case "VA": BMRFactor = 1.725F;
                    break;
        }
        return BMR * BMRFactor;
    }
}
