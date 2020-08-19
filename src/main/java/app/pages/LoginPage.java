package app.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends Page{

    @FindBy(css = "input#login-username")
    public WebElement loginInput;

    @FindBy(css = "input#login-passwd")
    public WebElement passwordInput;

    @FindBy(css = "input#login-signin")
    public WebElement submitLoginButton;

    @FindBy(css = "button#login-signin")
    public WebElement submitPasswordButton;

    @FindBy(css = "form p")
    public WebElement formError;


    public LoginPage() {
        super();
    }

    @Step("Type {username} to login input")
    public void typeUsername(String username){
        loginInput.sendKeys(username);
        submitLoginButton.click();
    }

    @Step("Type {password} to password input")
    public void typePassword(String password){
        passwordInput.sendKeys(password);
        submitPasswordButton.click();
    }

    @Step("Login with credentials: {username}, {password}")
    public WelcomePage loginUser(String username, String password){
        typeUsername(username);
        typePassword(password);
        return new WelcomePage();
    }

    @Step("Get text of warning")
    public String getWarningText(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(formError));
        return formError.getText();
    }

    @Step("Check warning is displayed")
    public boolean isWarningDisplayed(){
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOf(formError));
        return formError.isDisplayed();
    }



}
