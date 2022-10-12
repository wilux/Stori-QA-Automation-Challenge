package Config;


import Tools.DownloadDriver;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.time.Duration;


public abstract class BaseTest {

    public WebDriver driver;

    public final String HOME_URL = "https://rahulshettyacademy.com/AutomationPractice/";

    public WebDriver getDriver() {
        return driver;
    }


    @Parameters("browser")
    @BeforeClass
    public void beforeAll(@Optional("chrome") String browserName) {


        DownloadDriver downloadDriver = new DownloadDriver();
        driver = downloadDriver.get(browserName);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.manage().window().maximize();
    }

    @AfterClass
    public void afterTest() {

        if (driver != null) {
            driver.quit();
        }
    }

}