package org.example.pages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePageHelper  extends PageBase{
    @FindBy(css = ".text-primary") WebElement logInIcon;

    public HomePageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        log4j.startMethod("HomePageHelper - waitUntilPageIsLoaded()");

        //waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        waitUntilElementIsClickable(logInIcon,40);
        log4j.endMethod("HomePageHelper - waitUntilPageIsLoaded()");
    }

    public boolean isCorrectPage() {
    //return driver.findElement(By.cssSelector(".text-primary")).getText().equals("Log in");
        log4j.startMethod("HomePageHelper() - isCorrectPage()");
        log4j.endMethod("HomePageHelper() - isCorrectPage()");
    return logInIcon.getText().equals("Log in");
    }


}
