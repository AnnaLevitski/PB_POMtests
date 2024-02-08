package tests;

import config.Setup;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginPageTest extends Setup {
    @Test
    public void homePage_test(){

        new HomePage(wd).pause(100);
        new HomePage(wd).isHomePage();
    }
//    @Test
//    public void loginTest_success(){
//        LoginPage loginPage = new LoginPage();
//    }
}
