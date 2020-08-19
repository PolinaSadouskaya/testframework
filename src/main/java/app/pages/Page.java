package app.pages;

import app.config.TestConfig;
import helpers.listeners.DriverListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.concurrent.TimeUnit;

public abstract class Page {

    public static WebDriver driver;

    public Page() {
        if (TestConfig.props.isEmpty()) {
            TestConfig.initConfig();
        }
        driver = TestConfig.driver;

        EventFiringWebDriver eventFiringWebDriver = new EventFiringWebDriver(driver);
        eventFiringWebDriver.register(new DriverListener());
        driver = eventFiringWebDriver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        PageFactory.initElements(driver, this);
    }

    public static WebDriver getDriver() {
        return driver;
    }
}
