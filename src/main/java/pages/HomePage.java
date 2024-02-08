package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;

public class HomePage extends BasePage{
    public HomePage(WebDriver wd) {
        super(wd);
    }

    @FindBy(tagName = "h1")
    WebElement h1;

    public HomePage isHomePage(){
        Assert.assertEquals(h1.getText(), "PHONEBOOK");
        return this;
    }
}
