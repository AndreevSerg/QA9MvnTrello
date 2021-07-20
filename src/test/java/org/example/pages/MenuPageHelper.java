package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class MenuPageHelper extends PageBase{

    @FindBy(css = ".js-open-header-member-menu")
    WebElement headerMemberMenu;
    @FindBy (xpath = "//a[@data-test-id = 'header-member-menu-profile']")
    WebElement profileMemberMenu;
    @FindBy (xpath = "//span[contains(text(),'Activity')]")
    List<WebElement> activityMenuList;

    public MenuPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public void openPage() {
        headerMemberMenu.click();
    }

    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(profileMemberMenu,5);
    }

    public String getProfileVisibilityMenuName() {
        return profileMemberMenu.getText();
    }

    public void openActivityPage(){
        activityMenuList.get(1).click();
    }
}