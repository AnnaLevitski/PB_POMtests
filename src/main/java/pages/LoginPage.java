package pages;

import models.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver wd) {
        super(wd);
    }

    @FindBy(name = "email") WebElement inputEmail;
    @FindBy(name = "password") WebElement inputPassword;
    @FindBy(name = "registration") WebElement buttonRegistration;
    @FindBy(name = "login") WebElement buttonLogin;

    public LoginPage fillEmailPassword(User user){
        findAndType(inputEmail, user.getEmail());
        return this;
    }

}
