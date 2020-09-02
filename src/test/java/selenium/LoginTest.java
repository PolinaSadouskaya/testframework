package selenium;

import app.config.AppConfig;
import app.pages.LoginPage;
import app.pages.Page;
import app.pages.WelcomePage;
import io.qameta.allure.Description;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoginTest extends BaseTest {
    private final String WRONG_USERNAME_WARNING = "Sorry, we don't recognize this email.";
    private final String WRONG_PASSWORD_WARNING = "Invalid password. Please try again";
    private final String EMPTY_PASSWORD_WARNING = "Please provide password.";

    WelcomePage welcomePage = new WelcomePage();
    LoginPage loginPage = new LoginPage();
    WebDriver driver = Page.getDriver();
    SoftAssert softAssert = new SoftAssert();

    /**
     * 1 - isPasswordScreen
     * 2 - email to type
     * 3 - password to type
     * 4 - error expected
     * */
    @DataProvider(name = "credentials")
    public Object[][] getData(){
        Object[][] data = new Object[][]{
                {false, "wrong_email", "", WRONG_USERNAME_WARNING},
                {false, "", "", WRONG_USERNAME_WARNING},
                {true, AppConfig.username, "wrong_password", WRONG_PASSWORD_WARNING},
                {true, AppConfig.username, "", EMPTY_PASSWORD_WARNING}
        };
        return data;
    }


    @Test (testName = "Warnings test", dataProvider = "credentials", enabled = false)
    @Description("Invalid login credentials input for verification error's visibility and text")
    public void invalidLogin(boolean isPasswordScreen, String email, String password, String expectedError){

        welcomePage.open();
        loginPage = welcomePage.openLoginPage();
        loginPage.typeUsername(email);
        if (isPasswordScreen) {
            loginPage.typePassword(password);
        }
        softAssert.assertEquals(loginPage.getWarningText(), expectedError);
        softAssert.assertTrue(loginPage.isWarningDisplayed(), "Is warning displayed");
        softAssert.assertAll();

    }

    @Test(testName = "Successful login", enabled=false)
    @Description ("Verification of successful entry to system")
    public void successfulLogin() {
        welcomePage.open();
        loginPage = welcomePage.openLoginPage();
        loginPage.loginUser(AppConfig.username, AppConfig.userpassword);
        softAssert.assertTrue(welcomePage.isUserLoggedIn());
        softAssert.assertAll();
    }

    @Test(testName = "Test with wrong data", enabled=true)
    @Description("Always fail")
    public void failedLogin(){
        welcomePage.open();
        loginPage = welcomePage.openLoginPage();
        loginPage.loginUser(AppConfig.username, "123456");
        softAssert.assertTrue(welcomePage.isUserLoggedIn());
        softAssert.assertAll();
    }

    @Test(testName = "Test always skipped", enabled=false)
    @Description("Always skipped")
    public void skippedLogin(){
        welcomePage.open();
        loginPage = welcomePage.openLoginPage();
        loginPage.loginUser(AppConfig.username, AppConfig.userpassword);
        softAssert.assertTrue(welcomePage.isUserLoggedIn());
        softAssert.assertAll();
    }

    @AfterClass
    public void cleanUp() {
        driver.quit();
        //close the browser
    }
}
