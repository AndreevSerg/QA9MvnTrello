package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase{

    @FindBy (css = ".phenom-desc")
    List<WebElement> activityRecordList;

    public ActivityPageHelper (WebDriver driver){
        this.driver=driver;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilAllElementsAreVisible(activityRecordList,10);
    }


    public boolean lastActivityContains(String string) {
        return activityRecordList.get(0).getText().contains(string);
    }
}
