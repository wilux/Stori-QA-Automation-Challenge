package testSuit.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewWindowPage {
    WebDriver driver;

    public String URL = "http://www.qaclickacademy.com/";

    @FindBy(xpath = "//input[@id='autocomplete']")
    public WebElement countriesInput;

    @FindBy(xpath = "//select[@id='dropdown-class-example']")
    public WebElement dropdownSelect;

    @FindBy(xpath = "//button[@id='openwindow']")
    public WebElement openWindowsBtn;

    public NewWindowPage(WebDriver driver) {

        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    public String getWelcomeTitle(int order) {
        return driver.findElement(By.cssSelector(".text-center.row > .container > div:nth-of-type(" + order + ") .col-sm-9 > h3")).getText();
    }

    public String getWelcomeText(int order) {
        return driver.findElement(By.cssSelector(".text-center.row > .container > div:nth-of-type(" + order + ") p")).getText();
    }

}