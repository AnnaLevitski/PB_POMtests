package pages;

import driver.Driver;
import models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class LoginPage extends BasePage implements Page{
    public LoginPage() {
        PageFactory.initElements(Driver.get(), this);
    }
    @FindBy(xpath = "//a[text()='LOGIN']") WebElement tabLogin;
    @FindBy(name = "email") WebElement inputEmail;
    @FindBy(name = "password") WebElement inputPassword;
    @FindBy(name = "registration") WebElement buttonRegistration;
    @FindBy(name = "login") WebElement buttonLogin;

    public LoginPage fillEmailPassword(User user){
        if(!isLink("login")) tabLogin.click();
        findAndType(inputEmail, user.getEmail());
        findAndType(inputPassword, user.getPassword());
        return this;
    }

    public Page clickLogin(){
        pages.PageFactory pageFactory = new pages.PageFactory();
        buttonLogin.click();
        pause(1000);
        return pageFactory.createPage(getLink());
    }
    public BasePage clickRegistration(){
        buttonRegistration.click();
        return this;
    }

    @Override
    public boolean isLink(String page) {
        return getLink()==page;
    }

    @Override
    public void assertLink() {
        new WebDriverWait(Driver.get(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("login"));
        System.out.println("login");
    }
    @Override
    public void assertLink(String page) {
        pause(30);
        Assert.assertTrue(new WebDriverWait(Driver.get(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains(page)));
    }

    @Override
    public String getLink() {
        String[] arr = Driver.get().getCurrentUrl().split("/");
        return arr[arr.length -1];
    }
}
