package pages;

import driver.Driver;
import models.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class LoginPage extends BasePage {
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

    public BasePage clickLogin(){
        pages.PageFactory pageFactory = new pages.PageFactory();
        buttonLogin.click();
        pause(1000);
        return pageFactory.createPage(getLink());
    }
    public BasePage clickRegistration(){
        buttonRegistration.click();
        return this;
    }

    public void isLoginPasswordAlertPresent(){
        Assert.assertTrue(isAlertPresent("Wrong email or password"));
    }





}
