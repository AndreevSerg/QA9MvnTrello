package org.example.tests;

import org.example.pages.*;
import org.example.util.DataProviders;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class LoginTests extends TestBase {
    //HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;


    @BeforeMethod(alwaysRun = true)
    public void initTests() {
        //homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        log4j.startMethod("LoginTests - initTests()");

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        log4j.endMethod("LoginTests - initTests()");

    }

    @Test
    public void negativeLogin() {
       //loginPage.fillInEmailField("email");
       //loginPage.fillInPasswordField("nlkikjl");
       //loginPage.submitLoginNoAttl();
       loginPage.loginNotAttl("email", "password");
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username", "The error message is not correct");

    }

    @Test(groups = {"smoke", "system"}, dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void negativeLoginThirdDataProv(String login, String password) {
        //loginPage.fillInEmailField("email");
        //loginPage.fillInPasswordField("nlkikjl");
        //loginPage.submitLoginNoAttl();
        loginPage.loginNotAttl(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this email", "The error message is not correct");

    }

    @Test(enabled = false)
    public void loginTest() throws InterruptedException {

        driver.findElement(By.cssSelector("[class = 'btn btn-sm btn-link text-primary']")).click();
        Thread.sleep(2000);

        WebElement emailLogin = driver.findElement(By.xpath("//input[@id='user']"));
        emailLogin.click();
        emailLogin.sendKeys(LOGIN);
        Thread.sleep(3000);

        driver.findElement(By.cssSelector("#login")).click();
        Thread.sleep(3000);

        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.click();
        password.sendKeys(PASSWORD);
        password.submit();
        //   driver.findElement(By.cssSelector("#login-submit")).click();
        Thread.sleep(5000);

      /*  System.out.println("Name of the element is: " + driver
                .findElements(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]")).get(0).getText());
        ????????????????
        */

        Assert.assertEquals(driver
                .findElements(By.xpath("(//button[@data-test-id='header-boards-menu-button']/span)[2]")).get(0).getText(), "Boards"
        , "Name of button is not 'Boards'");
        Thread.sleep(3000);
    }

    @Test(dataProviderClass = DataProviders.class,dataProvider = "loginPositive")
    public void positiveLogin(String login, String password) {
        //loginPage.loginAsAttl(LOGIN, PASSWORD);
        loginPage.loginAsAttl(login,password);

        boardsPage.waitUntilPageIsLoaded();
        //   System.out.println("Name of the element is: "
        //          + driver.findElement(By.xpath("//button[@data-test-id='header-boards-menu-button']/.")).getText());
        Assert.assertEquals(boardsPage.getBoardsButtonName(), "Boards",
                "Name of the button is not 'Boards'");

    }

    //??????
    @Test(dataProviderClass = DataProviders.class, dataProvider ="loginNegativeDifferentMessages")
    public void loginNegativeDifferentMessages(String login, String password, String message){
        log4j.startTestCase("loginNegativeDifferentMessages(), parameters: login="
                + login + " password=" + password + " message='" + message + "'");
        log4j.info("Enter login not attl: login=" + login + " password=" + password);
        loginPage.loginNotAttl(login,password);
        log4j.info("Assert: Message has to be - " + message);
        Assert.assertEquals(loginPage.getErrorMessage(),message,
                "The error message is not correct");
        log4j.endTestCase2();
    }

    //????
    @Test(groups = {"system"}, dataProviderClass = DataProviders.class, dataProvider ="loginNegativeRandomData")
    public void loginNegativeRandomData(String login, String password){
        loginPage.loginNotAttl(login,password);
        Assert.assertEquals(loginPage.getErrorMessage(),"There isn't an account for this username",
                "The error message is not correct");
    }



    @Test(enabled = false)
    public void newListCreatingTest() throws InterruptedException {
        driver.findElement(By.cssSelector(".text-primary")).click();
        Thread.sleep(3000);

        WebElement emailField = driver.findElement(By.id("user"));
        emailField.click();
        emailField.sendKeys(LOGIN);
        Thread.sleep(2000);

        driver.findElement(By.id("login")).click();
        Thread.sleep(3000);

        WebElement password = driver.findElement(By.cssSelector("#password"));
        password.click();
        password.sendKeys(PASSWORD);
        password.submit();
        Thread.sleep(20000);
        //  go to the 'Boards' tab
        driver.findElement(By.xpath("//a[@data-test-id = 'home-team-boards-tab']")).click();
        Thread.sleep(3000);
        // open 'QA Haifa9' board
        driver.findElement(By.xpath("//a[@class = 'board-tile'][.//div[@title='QA Haifa9']]")).click();
        Thread.sleep(3000);
        // press 'Add list button'
        WebElement createListButton = driver.findElement(By.cssSelector(".placeholder"));
        createListButton.click();
        // enter name of the list
        WebElement nameListField = driver.findElement(By.cssSelector("input[name='name']"));
        nameListField.click();
        nameListField.sendKeys("New test List");
        // click 'Add list' button
        WebElement saveListButton = driver.findElement(By.cssSelector(".js-save-edit"));
        saveListButton.click();
        // click 'x' button to cancel new list creating
        Thread.sleep(2000);
        WebElement cancelListCreatingButton = driver.findElement(By.cssSelector(".js-cancel-edit"));
        cancelListCreatingButton.click();
        Thread.sleep(2000);
    }

}

