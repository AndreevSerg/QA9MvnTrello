package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase{
    @FindBy(xpath = "(//button[@data-test-id='header-boards-menu-button']/span)[2]")
    WebElement boardsIcon;
    @FindBy(xpath = "//a[@data-test-id = 'home-team-boards-tab']")
    WebElement boardsMenuLeft;
    @FindBy(xpath = "//h3")
    WebElement headerYourWorkspace;

    public BoardsPageHelper(WebDriver driver){
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(boardsIcon,30);
        return this;
    }

    public String getBoardsButtonName(){
        return boardsIcon.getText();
    }

    public BoardsPageHelper openBoardsMenu() {
        waitUntilElementIsClickable(boardsMenuLeft, 10);
        boardsMenuLeft.click();
        //waitUntilElementIsVisible(By.xpath("//h3[contains(text(),'Your Workspace boards')]"),10);
        waitUntilElementTextIs(headerYourWorkspace, "Your Workspace boards", 10);
        return this;
    }

}
