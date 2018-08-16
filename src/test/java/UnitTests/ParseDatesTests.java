package UnitTests;

import logic.CalculateBMR;
import logic.ParseDates;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.powermock.api.mockito.PowerMockito.whenNew;

@RunWith(PowerMockRunner.class)
@PrepareForTest({ParseDates.class})
public class ParseDatesTests {

    ParseDates getDate = new ParseDates();
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Before
    public void setUp() throws Exception {
        Date today = df.parse("2016-05-30");
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        whenNew(GregorianCalendar.class).withNoArguments().thenReturn((GregorianCalendar) cal);
    }

    @Test
    public void testParseDate() throws ParseException {
        Date actualDate = getDate.parseDate("2016-12-01");
        Date expectedDate = df.parse("2016-12-01");
        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testReturnDateFiveWeeksFromNow() {
        String actualDateString = getDate.returnDateFiveWeeksFromNow();
        String expected = "July 04, 2016";
        assertEquals(expected, actualDateString);
    }
}
