package SeleniumTests;

import javafx.scene.control.RadioButton;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class TestSignUpPageTwo {


    public void navigateToSignUpPgTwo(WebDriver chromeDriver) {
        //get to sign_up_pg2
        String url = "http://127.0.0.1:8080/Fitness-1.0-SNAPSHOT/index.jsp";
        chromeDriver.navigate().to(url);

        // go to sign up page
        WebElement element = chromeDriver.findElement(By.id("signup_link"));
        element.click();

        // wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(chromeDriver, 10)).until(new ExpectedCondition<WebElement>() {
            public WebElement apply(WebDriver d) {
                return d.findElement(By.id("signup_title"));
            }
        });
        chromeDriver.findElement(By.id("username")).sendKeys("barbara2");
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello123");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public String returnLastValidDateString() {
        Date today = new Date();
        Integer dayCount = -365 * 5;
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DATE, dayCount);
        Date lastValidDate = c.getTime();
        SimpleDateFormat longDateFormat = new SimpleDateFormat("MMMM d, yyyy");

        String lastValidDateString = longDateFormat.format(lastValidDate);
        return lastValidDateString;
    }

    public void clearDateField(String id, WebDriver chromeDriver) {
        chromeDriver.findElement(By.id(id)).sendKeys("00000000");
        for(int i = 0; i < 3; i++) {
            chromeDriver.findElement(By.id(id)).sendKeys(Keys.DELETE);
            chromeDriver.findElement(By.id(id)).sendKeys(Keys.ARROW_LEFT);
        }
    }

    @Test
    public void testSignUpPgTwo() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barodriguez\\Desktop\\chromedriver.exe");
        WebDriver chromeDriver = new ChromeDriver();
        DesiredCapabilities capability = DesiredCapabilities.chrome();

        navigateToSignUpPgTwo(chromeDriver);
        testInvalidWeight(chromeDriver);
        testValidWeight(chromeDriver);
        testBirthDateBeforeJan11900(chromeDriver);
        testBirthDateAfterLastValidDate(chromeDriver);
        testSubmitFormWithGoodWeightinvalidBirthdate(chromeDriver);
        testSubmitFormBadWeightBadBirthdate(chromeDriver);
        testSubmitFormBadWeightGoodBirthday(chromeDriver);
        testSubmitFormNoErrors(chromeDriver);
    }

    public void testInvalidWeight(WebDriver chromeDriver) {
        System.out.println("testInvalidWeight");
        chromeDriver.findElement(By.id("signup_weight")).sendKeys("49");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_weight_error"));
        String message = messageElement.getText();
        String successMsg = "Weight must be at least 50 pounds.";
        assertEquals(message, successMsg);
    }

    public void testValidWeight(WebDriver chromeDriver) {
        System.out.println("testValidWeight");
        chromeDriver.findElement(By.id("signup_weight")).clear();
        chromeDriver.findElement(By.id("signup_weight")).sendKeys("50");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_weight_error"));
        String message = messageElement.getText();
        String successMsg = "";
        assertEquals(message, successMsg);
    }

    public void testBirthDateBeforeJan11900(WebDriver chromeDriver) {
        System.out.println("testBirthDateBeforeJan11900");
        chromeDriver.findElement(By.id("signup_birthdate")).sendKeys("12011899");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_birthdate_error"));
        String message = messageElement.getText();

        //need to calculate last valid date which is 5 years before today's date
        String lastValidDateString = returnLastValidDateString();

        String successMsg = "Must be a valid birthdate between January 1, 1900 and " + lastValidDateString + ".";
        assertEquals(message, successMsg);
    }

    public void testBirthDateAfterLastValidDate(WebDriver chromeDriver) {
        System.out.println("testBirthDateAfterLastValidDate");

        //get today, which is past valid date and convert to format 01012018
        Date today = new Date();
        SimpleDateFormat shortDateFormat = new SimpleDateFormat("MM-dd-yyyy");
        String todayString = shortDateFormat.format(today);
        todayString = todayString.replace("-", "");

        //update birth date field
        clearDateField("signup_birthdate", chromeDriver);
        chromeDriver.findElement(By.id("signup_birthdate")).sendKeys(todayString);

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_birthdate_error"));
        String message = messageElement.getText();

        //need to calculate last valid date which is 5 years before today's date
        String lastValidDateString = returnLastValidDateString();

        String successMsg = "Must be a valid birthdate between January 1, 1900 and " + lastValidDateString + ".";
        assertEquals(message, successMsg);
    }

    public void testSubmitFormWithGoodWeightinvalidBirthdate(WebDriver chromeDriver) {
        System.out.println("testSubmitFormWithGoodWeightinvalidBirthdate");

        //setup fields that don't have validations (except required)
        chromeDriver.findElement(By.name("heightFt")).sendKeys("5");
        chromeDriver.findElement(By.name("heightIn")).sendKeys("0");
        chromeDriver.findElement(By.id("signup_female")).click();
        chromeDriver.findElement(By.id("signup_active")).click();
        chromeDriver.findElement(By.name("weightGoals")).sendKeys("L.5");

        //setup good weight
        chromeDriver.findElement(By.id("signup_weight")).sendKeys("110");

        //setup bad birthdate
        clearDateField("signup_birthdate", chromeDriver);
        chromeDriver.findElement(By.id("signup_birthdate")).sendKeys("12011899");

        //submit form
        chromeDriver.findElement(By.id("signup_submit")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_pg2_form_errors"));
        String message = messageElement.getText();

        //need to calculate last valid date which is 5 years before today's date
        String lastValidDateString = returnLastValidDateString();

        //form errors will be displayed
        String form_errors = "Fix the following errors before resubmitting this form:\n";
        String birthday_error = "\u25BA Must be a valid birthdate between January 1, 1900 and " +
                lastValidDateString + ".";
        String successMsg = form_errors + birthday_error;
        assertEquals(message, successMsg);

        // test that clicking enter will not send you to next page with invalid birthdate
        messageElement = chromeDriver.findElement(By.id("signup_pg2_title"));
        message = messageElement.getText();
        successMsg = "Sign Up - Continued";

        assertEquals(message, successMsg);
        }

    public void testSubmitFormBadWeightBadBirthdate(WebDriver chromeDriver) {
        System.out.println("testSubmitFormBadWeightBadBirthdate");

        // change weight to be invalid
        chromeDriver.findElement(By.id("signup_weight")).clear();
        chromeDriver.findElement(By.id("signup_weight")).sendKeys("49");

        // submit form
        chromeDriver.findElement(By.id("signup_submit")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // run test

        // test that clicking enter will not send you to next page with invalid weight
        WebElement messageElement = chromeDriver.findElement(By.id("signup_pg2_title"));
        String message = messageElement.getText();
        String successMsg = "Sign Up - Continued";

        assertEquals(message, successMsg);

        // test that the form errors message is what we expect
        messageElement = chromeDriver.findElement(By.id("signup_pg2_form_errors"));
        message = messageElement.getText();
        String form_errors = "Fix the following errors before resubmitting this form:\n";

        String lastValidDateString = returnLastValidDateString();
        String weight_error = "\u25BA Weight must be at least 50 pounds.\n";
        String birthday_error = "\u25BA Must be a valid birthdate between January 1, 1900 and " +
                lastValidDateString + ".";
        successMsg = form_errors + weight_error + birthday_error;
        assertEquals(message, successMsg);
        }

    public void testSubmitFormBadWeightGoodBirthday(WebDriver chromeDriver) {
        System.out.println("testSubmitFormBadWeightGoodBirthday");

        // change the birth date to be valid
        clearDateField("signup_birthdate", chromeDriver);
        chromeDriver.findElement(By.id("signup_birthdate")).sendKeys("12011990");

        // submit form
        chromeDriver.findElement(By.id("signup_submit")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // run test

        // test that clicking enter will not send you to next page with invalid weight
        WebElement messageElement = chromeDriver.findElement(By.id("signup_pg2_title"));
        String message = messageElement.getText();
        String successMsg = "Sign Up - Continued";

        assertEquals(message, successMsg);

        // test that the form errors message is what we expect

        // actual error message
        messageElement = chromeDriver.findElement(By.id("signup_pg2_form_errors"));
        message = messageElement.getText();

        // expected error message
        String form_errors = "Fix the following errors before resubmitting this form:\n";
        String weight_error = "\u25BA Weight must be at least 50 pounds.";
        successMsg = form_errors + weight_error;

        assertEquals(message, successMsg);
        }

    public void testSubmitFormNoErrors(WebDriver chromeDriver) {
        System.out.println("testSubmitFormNoErrors");

        // change weight to be valid
        chromeDriver.findElement(By.id("signup_weight")).clear();
        chromeDriver.findElement(By.id("signup_weight")).sendKeys("110");

        // submit form
        chromeDriver.findElement(By.id("signup_submit")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // run test

        // test that clicking enter sends you to Display Goals page
        WebElement displayGoalsHeading = chromeDriver.findElement(By.id("display_goals_title"));
        String displayGoalsHeadingText = displayGoalsHeading.getText();

        String expectedText = "Your Suggested Fitness and Nutrition Goals";

        assertEquals(displayGoalsHeadingText, expectedText);
        }

}
