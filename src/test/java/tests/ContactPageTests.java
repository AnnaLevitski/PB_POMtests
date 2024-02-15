package tests;


import driver.Driver;
import models.User;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.ContactsPage;
import pages.LoginPage;

public class ContactPageTests extends Driver{
    @BeforeClass
    public void bc(){
        PageFactory.initElements(Driver.get(), LoginPage.class).fillEmailPassword(User.builder().email("mara@gmail.com").password("Mmar123456$").build())
                .clickLogin();
    }

    @Test
    public void removeContact_success(){
        PageFactory.initElements(Driver.get(), ContactsPage.class).removeContact();
    }
}
