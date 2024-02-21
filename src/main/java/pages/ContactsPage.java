package pages;

import driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ContactsPage extends BasePage{
    BasePage bp = new BasePage();
    public ContactsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @Override
    protected void load() {
        Driver.get().get("https://telranedu.web.app/contacts");
    }

    @Override
    protected void isLoaded() throws Error {
        try {
            Assert.assertTrue(buttonSignOut.isDisplayed());
        }catch (Exception e){

        }
    }

    @FindBy(linkText = "/contacts") WebElement tabContacts;
    @FindBy(xpath = "//button[normalize-space()='Sign Out']") WebElement buttonSignOut;
    @FindBy(xpath = "//button[normalize-space()='Remove']") WebElement buttonRemove;
    @FindBy(xpath = "//button[normalize-space()='Edit']") WebElement buttonEdit;
    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']") List<WebElement> contactsList;
    @FindBy(xpath = "//div[1][@class='contact-item_card__2SOIM']") WebElement contactItem;
    @FindBy(className = "contact-item-detailed_card__50dTS") WebElement contactCard;

    public void isTabContactsPresent(){
        Assert.assertTrue(buttonSignOut.isDisplayed());
    }

    public int countContacts(){
        return contactsList.size();
    }

    public ContactsPage removeContact(){
        int countContactsBefore = countContacts()-1;
        contactItem.click();
        buttonRemove.click();
        WebDriverWait webDriverWait = new WebDriverWait(Driver.get(), Duration.ofSeconds(10));
        webDriverWait.until(ExpectedConditions.invisibilityOf(contactCard));
        int countContactsAfter = countContacts();
        Assert.assertEquals(countContactsBefore, countContactsAfter);
        return this;
    }

    public void signOut(){
        try{
            if(buttonSignOut.isDisplayed()){
                buttonSignOut.click();
            }
        }catch (Exception e){

        }
    }


}
