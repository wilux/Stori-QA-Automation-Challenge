package Tools.listeners;

import Config.BaseTest;
import Tools.extentreports.ExtentManager;
import Tools.extentreports.ExtentTestManager;
import Tools.logs.Log;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static Tools.extentreports.ExtentTestManager.getTest;


public class TestListener implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver webDriver = ((BaseTest) testClass).getDriver();
        Capabilities cap = ((RemoteWebDriver) webDriver).getCapabilities();
        String browserName = cap.getBrowserName().toLowerCase();
        return browserName + " - " + iTestResult.getMethod().getConstructorOrMethod().getName();
    }


    public void onStart(ITestContext context) {
        Log.info("I am in onStart method " + context.getName());
    }

    public void onFinish(ITestContext context) {
        System.out.println(("*** Test Suite " + context.getName() + " ending ***"));
        ExtentTestManager.endTest();
        ExtentManager.getInstance().flush();
    }

    public void onTestStart(ITestResult result) {
        Log.info("I am in onFinish method " + result.getName());
        ExtentTestManager.startTest(result.getMethod().getMethodName(), " test is starting.");
    }

    public void onTestSuccess(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is succeed.");
        getTest().log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is failed.");

        Object testClass = result.getInstance();
        try {
            WebDriver webDriver = ((BaseTest) testClass).getDriver();
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
            getTest().log(Status.FAIL, "Test Failed",
                    getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        } catch (Exception e) {
        }
    }

    public void onTestSkipped(ITestResult result) {
        Log.info(getTestMethodName(result) + " test is skipped.");
        ExtentTestManager.getTest().log(Status.SKIP, "Test Skipped");
    }

    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        Log.info("Test failed but it is in defined success ratio " + getTestMethodName(result));
    }

}