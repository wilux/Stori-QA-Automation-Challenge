package Tools.listeners;

//
//public class Retry implements IRetryAnalyzer {
//
//    private static int maxTry = 1; //Run the failed test 2 times
//    private int count = 0;
//
//    @Override
//    public boolean retry(ITestResult iTestResult) {
//        if ( !iTestResult.isSuccess () ) {                     //Check if test not succeed
//            if ( count < maxTry ) {                           //Check if maxTry count is reached
//                count++;                                    //Increase the maxTry count by 1
//                iTestResult.setStatus ( ITestResult.FAILURE ); //Mark test as failed and take base64Screenshot
//                extendReportsFailOperations ( iTestResult );   //ExtentReports fail operations
//                return true;                                //Tells TestNG to re-run the test
//            }
//        }
//        else {
//            iTestResult.setStatus ( ITestResult.SUCCESS );     //If test passes, TestNG marks it as passed
//        }
//        return false;
//    }
//
//    public void extendReportsFailOperations(ITestResult iTestResult) {
//        Object testClass = iTestResult.getInstance ();
//        WebDriver webDriver = ((BaseTest) testClass).getDriver ();
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) webDriver).getScreenshotAs ( OutputType.BASE64 );
//        getTest ().log ( Status.FAIL, "Test Failed",
//                         getTest ().addScreenCaptureFromBase64String ( base64Screenshot ).getModel ().getMedia ().get ( 0 ) );
//    }
//}
