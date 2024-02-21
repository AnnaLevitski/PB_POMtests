package driver;

import config.ConfigurationReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

public class Driver {
    private static WebDriver driver;
    //private static ITestContext context; //?
    public Driver() {}

    public static WebDriver get(){
        if (driver==null){
            driver = new BrowserFactory().createDriver();
        }
        return driver;
    }
    public void close(){
        if(driver!=null){
            driver.quit();
            driver=null;
        }
    }
    @BeforeClass(alwaysRun = true)
    public static void setUp(){
        String url = ConfigurationReader.getProperty("url");
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Driver.get().navigate().to(url);
    }
    @AfterClass(alwaysRun = true)
    public static void tearDown(){
        Driver.get().close();
    }
}
