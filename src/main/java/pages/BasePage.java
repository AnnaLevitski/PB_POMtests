package pages;


import driver.Driver;
import org.openqa.selenium.*;

import java.io.File;
import java.nio.file.Files;


public class BasePage  {



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
}
