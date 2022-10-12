package testSuit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;

    WebDriverWait wait;

    @FindBy(xpath = "//input[@id='autocomplete']")
    public WebElement countriesInput;

    @FindBy(xpath = "//select[@id='dropdown-class-example']")
    public WebElement dropdownSelect;

    @FindBy(xpath = "//button[@id='openwindow']")
    public WebElement openWindowsBtn;

    @FindBy(xpath = "//a[@id='opentab']")
    public WebElement openTabBtn;

    @FindBy(xpath = "//input[@id='name']")
    public WebElement alertInput;

    @FindBy(xpath = "//input[@id='alertbtn']")
    public WebElement alertBtn;

    @FindBy(xpath = "//table[@name='courses']/tbody[1]/tr")
    public List<WebElement> coursesTable;


    @FindBy(xpath = "//fieldset[contains(.,'Web Table Fixed header')]//table[@id='product']/tbody[1]/tr")
    public List<WebElement> fixedTable;


    @FindBy(css = "#courses-iframe")
    public WebElement coursesIframe;


    public HomePage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);

    }


    public void selectCountryByText(String text) {

        wait.until(ExpectedConditions.elementToBeClickable(
                driver.findElement(By.xpath("//ul[@class='ui-menu ui-widget ui-widget-content ui-autocomplete ui-front']//div[.='" + text + "']")))).click();

        wait.until(ExpectedConditions.textToBePresentInElementValue(countriesInput, "Mexico"));

    }

    public void selectByOptionNumber(String number) {
        Select drpCountry = new Select(dropdownSelect);
        drpCountry.selectByValue("option" + number);
    }

    public String getSelectedOption() {
        return dropdownSelect.getAttribute("value");
    }

    public List<String> getCoursesByPrice(String price) {
        List<String> labelsList = new ArrayList<>();
        for (int i = 1; i < coursesTable.size(); i++) {
            try {
                WebElement name = driver.findElement(By.xpath("//table[@id='product']/tbody[1]/tr[" + i + "]/td[3]"));
                if (name.getText().equals(price)) {
                    String printName = driver.findElement(By.xpath("//table[@name='courses']/tbody[1]/tr[" + i + "]/td[2]")).getText();
                    labelsList.add(printName);
                    Reporter.log(printName);
                    System.out.println(printName);
                }
            } catch (Exception e) {
            }
        }
        return labelsList;
    }

    public List<String> getNameEngineers() {
        List<String> labelsList = new ArrayList<>();
        for (int i = 1; i < fixedTable.size(); i++) {
            WebElement name = driver.findElement(By.xpath("//fieldset[contains(.,'Web Table Fixed header')]//table[@id='product']/tbody[1]/tr[" + i + "]/td[1]"));
            String printName = name.getText();
            labelsList.add(printName);
            Reporter.log(printName);
            System.out.println(printName);

        }
        return labelsList;
    }

}