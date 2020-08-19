package app.pages;

import app.config.AppConfig;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class WelcomePage extends Page{
    @FindBy(partialLinkText = "Login")
    WebElement loginButton;

    @FindBy(css = "a[class='profile-button']")
    WebElement userProfileButton;

    public WelcomePage() {
        super();
    }

    public LoginPage openLoginPage(){
        loginButton.click();
        return new LoginPage();
    }

    public boolean isUserLoggedIn(){
        return userProfileButton.isDisplayed();
    }

    public void open(){
        driver.get(AppConfig.baseUrl);
    }

}
