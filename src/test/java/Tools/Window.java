package Tools;

import org.openqa.selenium.WebDriver;

import java.util.Iterator;
import java.util.Set;

public class Window {

    WebDriver driver;

    public Window(WebDriver driver) {

        this.driver = driver;

    }

    public void switchToLast(String mainWindow) {
        Set<String> s1 = driver.getWindowHandles();
        Iterator<String> i1 = s1.iterator();

        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!mainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
//                driver.close();
            }
        }
    }
}
