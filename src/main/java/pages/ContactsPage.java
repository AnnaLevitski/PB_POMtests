package pages;

import driver.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ContactsPage extends BasePage implements Page{
    public ContactsPage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(linkText = "/contacts") WebElement tabContacts;
    @FindBy(xpath = "//button[normalize-space()='Sign Out']") WebElement buttonSignOut;
    @FindBy(xpath = "//button[normalize-space()='Remove']") WebElement buttonRemove;
    @FindBy(xpath = "//button[normalize-space()='Edit']") WebElement buttonEdit;
    @FindBy(xpath = "//div[@class='contact-item_card__2SOIM']") List<WebElement> contactsList;
    @FindBy(xpath = "//div[1][@class='contact-item_card__2SOIM']") WebElement contactItem;

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
        pause(1000);
        int countContactsAfter = countContacts();
        Assert.assertEquals(countContactsBefore, countContactsAfter);
        return this;

    }

    public void signOut(){
        if(buttonSignOut.isDisplayed())
            buttonSignOut.click();
    }

    @Override
    public boolean isLink(String page) {
        return getLink()==page;
    }
    @Override
    public void assertLink(String page) {
        pause(30);
        Assert.assertTrue(new WebDriverWait(Driver.get(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains(page)));
    }
    @Override
    public void assertLink() {
        new WebDriverWait(Driver.get(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains("contacts"));
        System.out.println("contacts");
    }

    @Override
    public String getLink() {
        String[] arr = Driver.get().getCurrentUrl().split("/");
        return arr[arr.length -1];
    }
}
