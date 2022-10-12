package Tools.logs;

import com.aventstack.extentreports.Status;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static Tools.extentreports.ExtentTestManager.getTest;

public class Log {
    //Initialize Log4j instance
    private static final Logger Log = LogManager.getLogger ( Log.class );

    //Info Level Logs
    public static void info(String message) {
        Log.info ( message );
    }

    //Method for adding logs passed from test cases
    public static void reportLog(String message) {
        try {
            getTest ().log ( Status.INFO, message );
        } catch (Exception e) {
            System.out.println ( e );
        }


    }

    public static void reportLogScreen(WebDriver driver) {
        try {
            String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs ( OutputType.BASE64 );

            int i = getTest ().addScreenCaptureFromBase64String ( base64Screenshot ).getModel ().getMedia ().size ();

            getTest ().log ( Status.INFO, "Manual Screenshot",
                             getTest ().addScreenCaptureFromBase64String ( base64Screenshot ).getModel ().getMedia ().get ( i ) );


        } catch (Exception e) {
            System.out.println ( e );
        }


    }

    //Warn Level Logs
    public static void warn(String message) {
        Log.warn ( message );
    }

    //Error Level Logs
    public static void error(String message) {
        Log.error ( message );
    }

    //Fatal Level Logs
    public static void fatal(String message) {
        Log.fatal ( message );
    }

    //Debug Level Logs
    public static void debug(String message) {
        Log.debug ( message );
    }
}