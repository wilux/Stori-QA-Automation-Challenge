package testSuit.testCases;

import Config.BaseTest;
import testSuit.pages.HomePage;
import testSuit.pages.NewTabPage;
import testSuit.pages.NewWindowPage;
import Tools.SnapShot;
import Tools.Window;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HomePageTest extends BaseTest {
    protected WebDriver driver;

    protected WebDriverWait wait;
    protected HomePage homePage;
    protected NewTabPage newTabPage;
    protected NewWindowPage newWindowPage;

    protected String mainWindow;

    protected Window window;

    @BeforeClass
    public void beforeTest() {

        //Get Driver
        driver = getDriver();

        //Go To
        driver.get(HOME_URL);

        //Generic Wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        //Pages
        homePage = new HomePage(driver);
        newTabPage = new NewTabPage(driver);
        newWindowPage = new NewWindowPage(driver);

        //Tools
        window = new Window(driver);
        mainWindow = driver.getWindowHandle();
    }


    @Test(priority = 1)
    public void loadPageUrlSuccessful() {

        Assert.assertEquals(driver.getCurrentUrl(), HOME_URL);


    }

    @Test(priority = 2, dependsOnMethods = "loadPageUrlSuccessful")
    public void suggestionCountries() {

        homePage.countriesInput.sendKeys("Me");
        homePage.selectCountryByText("Mexico");

        Assert.assertEquals(homePage.countriesInput.getAttribute("value"), "Mexico");

    }

    @Test(priority = 3, dependsOnMethods = "loadPageUrlSuccessful")
    public void dropDownOptions() {

        homePage.selectByOptionNumber("2");
        Assert.assertEquals(homePage.getSelectedOption(), "option2");
        homePage.selectByOptionNumber("3");
        Assert.assertEquals(homePage.getSelectedOption(), "option3");

    }


    @Test(priority = 4, dependsOnMethods = "loadPageUrlSuccessful")
    public void windowsExampleLoadSuccessful() {

        homePage.openWindowsBtn.click();
        window.switchToLast(mainWindow);

        Assert.assertEquals(driver.getCurrentUrl(), newWindowPage.URL);

    }

    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTitleSelfPaced() {

        Assert.assertEquals(newWindowPage.getWelcomeTitle(1), "SELF PACED ONLINE TRAINING");

    }

    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTitleInDepth() {

        Assert.assertEquals(newWindowPage.getWelcomeTitle(2), "IN DEPTH MATERIAL");

    }

    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTitleLifeTime() {

        Assert.assertEquals(newWindowPage.getWelcomeTitle(3), "LIFETIME INSTRUCTOR SUPPORT");


    }

    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTitleResumePreparation() {

        Assert.assertEquals(newWindowPage.getWelcomeTitle(4), "RESUME PREPARTION & INTERVIEW QUESTIONS");


    }

    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTitle30DayMoneyBack() {

        Assert.assertEquals(newWindowPage.getWelcomeTitle(5), "30 DAY MONEY BACK GUARANTEE");


    }


    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTextForSelfPeace() {

        Assert.assertEquals(newWindowPage.getWelcomeText(1), "You can access the videos on 24* 7 for Lifetime. You can access the videos in iPad/Mobile too.");


    }


    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTextForInDepth() {

        Assert.assertEquals(newWindowPage.getWelcomeText(2), "Top class material designed on all courses for making you better in depth understanding of the concepts taught in the lectures.");

    }

    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTextForLifeTime() {


        Assert.assertEquals(newWindowPage.getWelcomeText(3), "We are happy to help you on any query in the course you are enrolled in. Trainer will reach out to you personally once you signup with the course.");


    }


    @Test(priority = 4, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTextForResumePreparation() {

        Assert.assertEquals(newWindowPage.getWelcomeText(4), "We will help you in preparing the resume and provide good number of Interview questions once you have completed the entire course.");

    }

    @Test(priority = 5, dependsOnMethods = "windowsExampleLoadSuccessful")
    public void windowsExampleTextFor30DayMoney() {

        Assert.assertEquals(newWindowPage.getWelcomeText(5), "We would never want you to be unhappy! If you are unsatisfied with your purchase, contact us in the first 30 days and we will give you a full refund.");

        driver.close();
        driver.switchTo().window(mainWindow);


    }


    @Test(priority = 5, dependsOnMethods = "loadPageUrlSuccessful")
    public void windowsTabExampleLoadSuccessful() {

        homePage.openTabBtn.click();
        window.switchToLast(mainWindow);
        Assert.assertEquals(driver.getCurrentUrl(), newTabPage.URL);

    }

    @Test(priority = 5, dependsOnMethods = "loadPageUrlSuccessful")
    public void windowsTabExampleViewAllCoursesButton() throws Exception {

        SnapShot snapShot = new SnapShot();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        newTabPage.waitPreloadOff();
        Assert.assertTrue(newTabPage.viewAllCoursesBtn.isDisplayed());
        js.executeScript("window.scrollBy(0,1700)");
        snapShot.take(driver, "ViewAllCoursesButton.jpg");
        driver.switchTo().window(mainWindow);

    }


    @Test(priority = 6, dependsOnMethods = "loadPageUrlSuccessful")
    public void switchToAlert() {

        wait.until(ExpectedConditions.elementToBeClickable(homePage.alertInput)).sendKeys("Stori Card");
        homePage.alertBtn.click();
        Assert.assertEquals(driver.switchTo().alert().getText(), "Hello Stori Card, Are you sure you want to confirm?");


    }

    @Test(priority = 7, dependsOnMethods = "loadPageUrlSuccessful")
    public void switchToAlertAndAccept() {

        driver.switchTo().alert().accept();

    }

    @Test(priority = 8, dependsOnMethods = "loadPageUrlSuccessful")
    public void printCoursesByPrice() {

        List<String> test = homePage.getCoursesByPrice("25");
        Assert.assertEquals(test.size(), 4);

    }


    @Test(priority = 8, dependsOnMethods = "loadPageUrlSuccessful")
    public void printEngineerNames() {

        List<String> test = homePage.getNameEngineers();
        Assert.assertEquals(test.size(), 8);

    }

    @Test(priority = 8, dependsOnMethods = "loadPageUrlSuccessful")
    public void textMentorshipParagraph() {

        driver.switchTo().frame(homePage.coursesIframe);
        String textParagraph = newTabPage.getParagraphText(2);
        Reporter.log(textParagraph);
        System.out.println(textParagraph);
        Assert.assertEquals(textParagraph, "His mentorship program is most after in the software testing community with long waiting period.");

    }


}

