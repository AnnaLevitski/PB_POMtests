package tests;


import driver.Driver;
import models.User;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.*;
import org.openqa.selenium.support.PageFactory;

public class LoginPageTest extends Driver{
    @BeforeClass
    public void bc(){
        //PageFactory.initElements(wd, HomePage.class).isHomePage();
        //new HomePage(wd).isHomePage();
    }
    @Test
    public void loginTest_success(){
        PageFactory.initElements(Driver.get(), LoginPage.class).fillEmailPassword(User.builder().email("mara@gmail.com").password("Mmar123456$").build())
                .clickLogin().assertLink("contacts");
    }
//    @Test
//    public void loginTest_negative(){
//        new LoginPage().fillEmailPassword(User.builder().email("mara@gmail.com").password("Mmar123456$").build())
//                .clickLogin().assertLink("login");
//    }
    @AfterMethod
    public void at(){
        PageFactory.initElements(Driver.get(), ContactsPage.class).signOut();
    }

}
