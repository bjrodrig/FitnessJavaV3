package UnitTests;

import logic.CalculateNetCalories;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class CalculateNetCaloriesTests {

    CalculateNetCalories calc = new CalculateNetCalories();

    @Test
    public void testCalculateNetCalories() {

        Float actualChangeInCalories = calc.calculateNetCalories(1828.7286F, "L1",
                "F");
        Float expected = 1328.7286F;
        assertEquals(expected, actualChangeInCalories);
    }

    /*
    Test that minimum for females is properly set
     */
    @Test
    public void testCalculateNetCaloriesFemaleMin() {
        Float actualChangeInCalories = calc.calculateNetCalories(1828.7286F, "L2",
                "F");
        Float expected = 1200F;
        assertEquals(expected, actualChangeInCalories);
    }

    /*
    Test that minimum for males is properly set
     */
    @Test
    public void testCalculateNetCaloriesMaleMin() {
        Float actualChangeInCalories = calc.calculateNetCalories(1828.7268F, "L1",
                "M");
        Float expected = 1500F;
        assertEquals(expected, actualChangeInCalories);
    }
}
