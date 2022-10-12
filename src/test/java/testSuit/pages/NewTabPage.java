package testSuit.pages;

import com.google.common.base.Stopwatch;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class NewTabPage {
    WebDriver driver;

    public String URL = "https://www.rahulshettyacademy.com/";

    @FindBy(xpath = "//a[.='VIEW ALL COURSES']")
    public WebElement viewAllCoursesBtn;

    @FindBy(xpath = "//div[@class='preloader'][contains(@style,'display: none')]")
    public WebElement preloaderOff;

    public NewTabPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String getParagraphText(int order) {
        return driver.findElement(By.cssSelector(".price-title div:nth-of-type(2) li:nth-of-type(" + order + ")")).getText();
    }

    public void waitPreloadOff() {

        final Stopwatch stopwatch = Stopwatch.createStarted ();

        while ((stopwatch.elapsed ( TimeUnit.SECONDS ) < 10)) {
            try {
                preloaderOff.isDisplayed();
                break;
            } catch (Exception e) {
            }

        }
    }


}