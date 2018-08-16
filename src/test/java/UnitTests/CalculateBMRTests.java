package UnitTests;

import junit.framework.TestCase;
import logic.CalculateBMR;
import logic.ParseDates;
import logic.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static java.lang.Math.round;
import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.whenNew;


@RunWith(PowerMockRunner.class)
@PrepareForTest({CalculateBMR.class, Date.class})
public class CalculateBMRTests extends TestCase {

    private CalculateBMR bMRInputs;
    public ParseDates newDate = new ParseDates();
    Date birthDate = newDate.parseDate("1990-12-01");

    @Before
    public void setUp() throws Exception {
        Date today = newDate.parseDate("2016-05-30");
        whenNew(Date.class).withNoArguments().thenReturn(today);

        bMRInputs = new CalculateBMR(5, 1, 110F, birthDate,
                "F", "A");
    }

    @Test
    public void testConvertHeightToCm() {
        Float heightInCm = bMRInputs.convertHeightToCm(5, 1);
        Float expected = 154.94F;
        assertEquals(expected, heightInCm);
    }

    @Test
    public void testConvertWeightToKg() {
        Float actualWeightInKg = bMRInputs.convertWeightToKg(110F);
        Float expected = 50F;
        assertEquals(expected, actualWeightInKg);
    }

    @Test
    public void testCalculateAgeInYears() {
        Float actualAgeInYears = bMRInputs.calculateAgeInYears(birthDate);
        Float expected = 25.51F;
        assertEquals(expected, actualAgeInYears);
    }

    @Test
    public void testCalculateBMR() {
        Float BMR = bMRInputs.calculateBMR(154.94F, 50F, 25.51F,
                "F", "A");
        Float expected = 1828.7286F;
        assertEquals(expected, BMR);
    }

    @Test
    public void testCalculateBMRClass() {
        assertEquals(bMRInputs.getBMR(), 1828.7286F );
    }


}
