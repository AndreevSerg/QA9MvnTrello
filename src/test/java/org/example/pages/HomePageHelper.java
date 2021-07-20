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

        //waitUntilElementIsClickable(By.cssSelector(".text-primary"),40);
        waitUntilElementIsClickable(logInIcon,40);
    }

    public boolean isCorrectPage() {
    //return driver.findElement(By.cssSelector(".text-primary")).getText().equals("Log in");
    return logInIcon.getText().equals("Log in");
    }


}
