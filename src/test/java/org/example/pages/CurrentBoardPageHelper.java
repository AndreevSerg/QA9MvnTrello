package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase {
    String boardName;

    @FindBy (css = ".placeholder")
    WebElement addListButton;
    @FindBy (css = ".js-list-content")
    List<WebElement> columnsList;
    @FindBy (css = "input[name='name']")
    WebElement nameListField;
    @FindBy (css = ".js-save-edit")
    WebElement saveListButton;
    @FindBy (css = ".js-cancel-edit")
    WebElement cancelListCreatingButton;
    @FindBy (css = ".card-composer-container")
    WebElement addCardButton;
    @FindBy (css = ".js-add-card")
    WebElement submitCardButton;
    @FindBy (css = ".js-card-title")
    WebElement cardTitleField;
    @FindBy (css = ".js-cancel")
    WebElement cancelButton;
    @FindBy (css = ".js-copy-list")
    WebElement copyListButton;
    @FindBy (css = ".js-autofocus")
    WebElement newListName;
    @FindBy (css = ".js-submit")
    WebElement createListButton;
    @FindBy(css = ".js-close-list")
    WebElement deleteListButton;
    @FindBy (css = ".list-header-extras-menu")
    List<WebElement> columnsMenuList;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        this.driver = driver;
        this.boardName = boardName;
        PageFactory.initElements(driver,this);
    }

    public CurrentBoardPageHelper openPage() {
        waitUntilElementIsClickable(getLocatorBoardButton(),10);
        // open 'QA Haifa9' board
        WebElement qaHaifa9Board = driver.findElement(getLocatorBoardButton());
        qaHaifa9Board.click();
        return this;
    }

    public By getLocatorBoardButton(){
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='"+boardName+"']]");
    }

    public CurrentBoardPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(addListButton,10);

        if (addListButton.getText().equals("Add another list")) {
            //waitUntilAllElementsArePresent(By.cssSelector(".js-list-content"),5);
            waitUntilAllElementsAreVisible(columnsList,10);
        }
        return this;
    }

    public int getListsQuantity() {
        //List<WebElement> collumnsList = driver.findElements(By.cssSelector(".js-list-content"));
        return columnsList.size();
    }
    public int getCardsQuantity() {
        List<WebElement> columnsList = driver.findElements(By.cssSelector(".list-card-title"));
        return columnsList.size();
    }

    public void addNewList(String name) {
        int beginListsQuantity = this.getListsQuantity();
        // add new list by 'Add list button'
        //WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
        addListButton.click();
        // enter name of the list
        editField(nameListField, name);
        // click 'Add list' button
        saveListButton.click();
        // click 'x' button to cancel new list creating
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginListsQuantity+1,10);
        System.out.println("After adding: " + this.getListsQuantity());
        waitUntilElementIsClickable(cancelListCreatingButton,5);
        cancelListCreatingButton.click();
        waitUntilElementIsClickable(By.cssSelector(".placeholder"),5);
    }

    public void addNewCardToFirstList(String name) {
        int beginCards = this.getCardsQuantity();
        // press 'Add a card' ('Add another card')
        addCardButton.click();
        // fill in card title
        editField(cardTitleField, "card title");
        submitCardButton.click();
        waitUntilElementsBecome(By.cssSelector(".list-card-title"),beginCards+1,10);
        // --- cancel new list creation ---
        waitUntilElementIsClickable(cancelListCreatingButton,5);
        cancelButton.click();
    }

    public void archiveFirstList() {

        this.archiveList(0);
    }

    public void copyFirstList(String name) {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(By.cssSelector(".list-header-extras-menu"),5);
        driver.findElement(By.cssSelector(".list-header-extras-menu")).click();

        // -- click on "Copy menu"
        copyListButton.click();

        waitUntilElementIsClickable(newListName,5);
        newListName.sendKeys(name);

        waitUntilElementIsClickable(By.cssSelector(".js-submit"),10);
        //WebElement nameField  = driver.findElement(By.cssSelector(".js-autofocus"));
        //nameField.sendKeys("nameChanged");
        createListButton.click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists+1,5);
    }

    public int getNumberOfElementWithName(String nameList) {
        int number = -1;
        int counter = 0;
        for(WebElement element: driver.findElements(By.cssSelector(".list-header-name"))){
            if(element.getText().equals(nameList)){
                number = counter;
            }
            counter++;
        }
        return number;
    }

    public void archiveList(int number) {
        int beginLists = this.getListsQuantity();
        // -- click on the header menu
        waitUntilElementIsClickable(columnsMenuList.get(number),5);
        columnsMenuList.get(number).click();

        // -- click on "Archive menu"
        waitUntilElementIsClickable(deleteListButton,5);
        deleteListButton.click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"),beginLists-1,5);
    }

}
