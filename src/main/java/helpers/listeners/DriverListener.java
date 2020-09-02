package helpers.listeners;

import io.qameta.allure.Attachment;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Date;

public class DriverListener extends AbstractWebDriverEventListener {

    private static final String SCREENSHOT_LOCATION = "D:/work/projects/testoutput";

    static Logger LOG =  Logger.getLogger(DriverListener.class);

    @Override
    public void afterNavigateTo(String url, WebDriver driver) {
        LOG.info("Navigated to url: [" + url + "]");
    }

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
        LOG.info("Try to locate element using [" + by + "]");
    }

    @Override
    public void beforeClickOn(WebElement element, WebDriver driver) {
        LOG.info("Clicking element [" + element + "]");
    }

    @Override
    public void afterClickOn(WebElement element, WebDriver driver) {
       LOG.info("Clicked element [" + element + "]");
    }

    @Override
    public void afterChangeValueOf(WebElement element, WebDriver driver, CharSequence[] keysToSend) {
        String s = "";
        for (CharSequence c: keysToSend){
            s+= c.toString();
        }
        LOG.info("Type text "+ s +" into element [" + element + "]");

    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
        String name = new Date().toString() + "Screen on exception";
        takeScreenShot(name, driver);
        LOG.info("Taked scrrenshot "+ name);
    }

    @Attachment (value = "Page screenshot", type = "image/png")
    public static byte[] takeScreenShot(String name, WebDriver driver){
        if (driver != null){
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            try {
                //save screenshot
                FileChannel srcChannel = new FileInputStream(src).getChannel();
                File dst = new File(SCREENSHOT_LOCATION, name + ".png");
                FileChannel dstChannel = new FileOutputStream(dst).getChannel();
                dstChannel.transferFrom(srcChannel, 0, srcChannel.size());
                LOG.info("Taken screenshot: "+ name);

            } catch (IOException e) {
                e.printStackTrace();
            }
            //return screenshot for @attachment
            return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
        } else {
            LOG.info("No driver found for screenshot");
            return null;
        }

    }
}
