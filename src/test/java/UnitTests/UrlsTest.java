package UnitTests;

import logic.Urls;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UrlsTest {

    Urls getUrl = new Urls();

    @Test
    public void testCreateFullUrlString() {
        String actualUrl = getUrl.createFullUrlString("/signup");
        String expectedUrl = "/Fitness-1.0-SNAPSHOT/jsp/signup.jsp";
        assertEquals(expectedUrl, actualUrl);
    }

}
