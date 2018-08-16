package SeleniumTests;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static junit.framework.TestCase.assertTrue;
import static net.bytebuddy.matcher.ElementMatchers.anyOf;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class TestSignUpPgOne {

    @Test
    public void runTests() {

        System.setProperty("webdriver.chrome.driver", "C:\\Users\\barodriguez\\Desktop\\chromedriver.exe");
        WebDriver chromeDriver = new ChromeDriver();
        DesiredCapabilities capability = DesiredCapabilities.chrome();

        testSignUpLink(chromeDriver);
        testTypeUsernameThatAlreadyExists(chromeDriver);
        testUsernameThatDoesNotExist(chromeDriver);
        testPasswordTooShort(chromeDriver);
        testPasswordsDoNotMatch(chromeDriver);
        testPasswordTooShortAndNoMatch(chromeDriver);
        testSubmitFormGoodUsernameShortPasswordPasswordNoMatch(chromeDriver);
        testSubmitFormBadUsernameShortPasswordPasswordNoMatch(chromeDriver);
        testSubmitFormBadUsernamePasswordOk(chromeDriver);
        testSubmitFormBadUsernamePasswordShortYesMatch(chromeDriver);
        testSuccessfullySubmitForm(chromeDriver);
        testSubmitFormBackBadUsername(chromeDriver);
        chromeDriver.quit();
    }

    public void testSignUpLink(WebDriver chromeDriver) {
        System.out.println("testSignUpLink");
        //go to home page
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

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_title"));
        String message = messageElement.getText();
        String successMsg = "Sign Up";
        assertEquals(message, successMsg);

    }


    public void testTypeUsernameThatAlreadyExists(WebDriver chromeDriver) {
        System.out.println("testTypeUsernameThatAlreadyExists");
        chromeDriver.findElement(By.id("username")).sendKeys("barbara");
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");

        // wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(chromeDriver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                String usernameErrorText = d.findElement(By.id("username_error")).getText();
                return usernameErrorText != "";
            }
        });

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("username_error"));
        String message = messageElement.getText();
        String successMsg = "Username is taken. Select another.";
        assertEquals(message, successMsg);
    }

    public void testUsernameThatDoesNotExist(WebDriver chromeDriver) {
        System.out.println("testUsernameThatDoesNotExist");
        chromeDriver.findElement(By.id("username")).sendKeys("barbara1");
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("username_error"));
        String message = messageElement.getText();
        String successMsg = "Username is available";
        assertEquals(message, successMsg);
    }

    public void testPasswordTooShort(WebDriver chromeDriver) {
        System.out.println("testPasswordTooShort");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello");
        //only want the "password too short" error. Thus, make sure password and confirm
        //password are the same.
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("password_error"));
        String message = messageElement.getText();
        String successMsg = "The password must be at least 8 characters.";
        assertEquals(message, successMsg);
    }

    public void testPasswordsDoNotMatch(WebDriver chromeDriver) {
        System.out.println("testPasswordsDoNotMatch");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello1234");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("password_error"));
        String message = messageElement.getText();
        String successMsg = "Passwords do not match.";
        assertEquals(message, successMsg);
    }

    public void testPasswordTooShortAndNoMatch(WebDriver chromeDriver) {
        System.out.println("testPasswordTooShortAndNoMatch");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello");
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello1");

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("password_error"));
        String message = messageElement.getText();
        String successMsg = "Passwords do not match. The password must be at least 8 characters.";
        assertEquals(message, successMsg);
    }

    public void testSubmitFormGoodUsernameShortPasswordPasswordNoMatch(WebDriver chromeDriver) {
        System.out.println("testSubmitFormGoodUsernameShortPasswordPasswordNoMatch");
        //fill in form to get errors we want
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys("barbara1");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello");
        chromeDriver.findElement(By.id("confirm_password")).clear();
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello1");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("form_errors"));
        String message = messageElement.getText();
        String successMsg = "Fix the following errors before resubmitting this form:\n" +
                "\u25BA Password must be at least 8 characters.\n" +
                "\u25BA Password field must match Confirm Password field.";
        assertEquals(message, successMsg);
    }

    public void testSubmitFormBadUsernameShortPasswordPasswordNoMatch(WebDriver chromeDriver) {
        System.out.println("testSubmitFormBadUsernameShortPasswordPasswordNoMatch");
        //fill in form to get errors we want
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys("barbara");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello");
        chromeDriver.findElement(By.id("confirm_password")).clear();
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello1");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("form_errors"));
        String message = messageElement.getText();
        String successMsg = "Fix the following errors before resubmitting this form:\n" +
                "\u25BA Username is taken. Select another.\n" +
                "\u25BA Password must be at least 8 characters.\n" +
                "\u25BA Password field must match Confirm Password field.";
        assertEquals(message, successMsg);
    }

    public void testSubmitFormBadUsernamePasswordOk(WebDriver chromeDriver) {
        System.out.println("testSubmitFormBadUsernamePasswordOk");
        //fill in form to get errors we want
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys("barbara");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");
        chromeDriver.findElement(By.id("confirm_password")).clear();
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello123");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("form_errors"));
        String message = messageElement.getText();
        String successMsg = "Fix the following errors before resubmitting this form:\n" +
                "\u25BA Username is taken. Select another.";

        assertEquals(message, successMsg);
    }

    public void testSubmitFormBadUsernamePasswordShortYesMatch(WebDriver chromeDriver) {
        System.out.println("testSubmitFormBadUsernamePasswordShortYesMatch");
        //fill in form to get errors we want
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys("barbara");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello");
        chromeDriver.findElement(By.id("confirm_password")).clear();
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("form_errors"));
        String message = messageElement.getText();
        String successMsg = "Fix the following errors before resubmitting this form:\n" +
                "\u25BA Username is taken. Select another.\n" +
                "\u25BA Password must be at least 8 characters.";

        assertEquals(message, successMsg);
    }

    public void testSuccessfullySubmitForm(WebDriver chromeDriver) {
        System.out.println("testSuccessfullySubmitForm");
        //fill in form with passing values
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys("barbara1");
        chromeDriver.findElement(By.id("password")).clear();
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");
        chromeDriver.findElement(By.id("confirm_password")).clear();
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello123");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).click();

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_pg2_title"));
        String message = messageElement.getText();
        String successMsg = "Sign Up - Continued";

        assertEquals(message, successMsg);
    }

    public void testSubmitFormBackBadUsername(WebDriver chromeDriver) {
        System.out.println("testSubmitFormBackBadUsername");
        chromeDriver.navigate().back();

        //type in form values
        chromeDriver.findElement(By.id("password")).sendKeys("hello123");
        chromeDriver.findElement(By.id("confirm_password")).sendKeys("hello123");
        chromeDriver.findElement(By.id("username")).clear();
        chromeDriver.findElement(By.id("username")).sendKeys("barbara");

        //submit form
        chromeDriver.findElement(By.id("signup_next")).sendKeys(Keys.ENTER);

        // wait for the page to load, timeout after 10 seconds
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //run test
        WebElement messageElement = chromeDriver.findElement(By.id("signup_title"));
        String message = messageElement.getText();
        String successMsg = "Sign Up";

        // test that clicking enter will not send you to next page with invalid username
        assertEquals(message, successMsg);

        // test that you get username error message
        messageElement = chromeDriver.findElement(By.id("form_errors"));
        message = messageElement.getText();
        successMsg = "Form was rejected because username already exists.";
        String otherMsg = "Fix the following errors before resubmitting this form:\n" +
                "\u25BA Username is taken. Select another.";

        // successMsg means server side validation worked. otherMsg means javascript
        // validation worked. In theory, only javascript should be necessary.
        // TODO: find out why client side validation doesn't always work with back button
        System.out.println(message);
        System.out.println(successMsg);
        assertTrue(message.equals(successMsg) || message.equals(otherMsg));
    }
}
