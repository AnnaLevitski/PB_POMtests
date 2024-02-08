package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Browser;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

public class Setup {
    public WebDriver wd;
    @BeforeClass
    @Parameters("browser")
    public void setUp(@Optional("chrome")String browser) {
        
        if (browser.equals(Browser.EDGE.browserName())) {
            wd = new EdgeDriver();
        } else if (browser.equals(Browser.FIREFOX.browserName())) {
            wd = new FirefoxDriver();
        } else if (browser.equals(Browser.SAFARI.browserName())) {
            wd = new SafariDriver();
        } else {
            wd = new ChromeDriver();
        }
        wd.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        wd.navigate().to("https://telranedu.web.app/");
    }
    @AfterClass
    public void tearDown(){
        wd.quit();
    }

}
