package pages;


import driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.nio.file.Files;
import java.time.Duration;


public class BasePage extends LoadableComponent<ContactsPage> {
    static PageFactory pageFactory = new PageFactory();

    public void findAndType(WebElement el, String text){
        el.click();
        el.clear();
        if(text!=null){
            el.sendKeys(text);
        }
    }
    public void pause(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }



    public void getScreenshot(String link) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) Driver.get();
        File tmp = takesScreenshot.getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(tmp.toPath(), new File(link).toPath());
        }catch (Exception e){

        }
    }
    public boolean isAlertPresent(String message) {
        Alert alert = new WebDriverWait(Driver.get(), Duration.ofSeconds(10))
                .until(ExpectedConditions.alertIsPresent());
        if(alert!=null && alert.getText().contains(message)){
            alert.accept();
            return true;
        }
        return false;
    }

    static public boolean isLink(String page){
        return getLink()==page;
    }
    static public BasePage assertLink(String page){
        new BasePage().pause(30);
        Assert.assertTrue(new WebDriverWait(Driver.get(), Duration.ofSeconds(20))
                .until(ExpectedConditions.urlContains(page)));
        return pageFactory.createPage(BasePage.getLink());
    }
    static public String getLink(){
        String[] arr = Driver.get().getCurrentUrl().split("/");
        return arr[arr.length -1];
    }

    @Override
    protected void load() {

    }

    @Override
    protected void isLoaded() throws Error {

    }

}
