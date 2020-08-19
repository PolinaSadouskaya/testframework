package helpers.listeners;

import app.config.TestConfig;
import helpers.Generator;
import org.apache.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNgListener implements ITestListener {

    Logger LOG =  Logger.getLogger(TestNgListener.class);


    public void onTestStart(ITestResult result) {
        LOG.info("\n STARTED TEST : "+result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        LOG.info(result.getMethod().getMethodName() + " - SUCCESS \n");
    }

    public void onTestFailure(ITestResult result) {
        LOG.info(result.getMethod().getMethodName() + " - FAILURE");
        LOG.info(result.getThrowable());
        String screenshotName = Generator.generateId(5) + result.getMethod().getMethodName();
        DriverListener.takeScreenShot(screenshotName, TestConfig.driver);
        LOG.info("\n");
    }


    public void onStart(ITestContext context) {
        LOG.info("STARTED CLASS : "+context.getCurrentXmlTest().getName());
    }

    public void onFinish(ITestContext context) {
        LOG.info("FINISHED CLASS : "+context.getCurrentXmlTest().getName());
    }

}
