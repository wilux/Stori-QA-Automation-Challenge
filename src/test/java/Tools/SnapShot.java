package Tools;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;

public class SnapShot {

    public void take(WebDriver webdriver, String name) throws Exception {

        TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
        File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
        File DestFile = new File("./screenshots/"+name);
        FileUtils.copyFile(SrcFile, DestFile);

    }
}