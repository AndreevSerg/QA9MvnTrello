package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase{
    @FindBy(css = ".text-primary")
    WebElement logInIcon;

    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "user")
    WebElement emailField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy (css = "p.error-message")
    WebElement errorMessage;
    @FindBy(xpath = "//input[@value = 'Log in with Atlassian']")
    WebElement loginAsAttlButton;
    @FindBy(id = "login-submit")
    WebElement submitAsAttlButton;

    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageHelper openPage(){
        //click 'Log in' button
        //Thread.sleep(5000);
        waitUntilElementIsClickable(logInIcon, 40);
        //click 'Log in' button
        //System.out.println("Log in button name: " + driver.findElement(By.cssSelector(".text-primary")));
        logInIcon.click();
        return this;
    }
    public LoginPageHelper waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginButton,10);
        return this;
    }

    public void loginNotAttl(String login, String password){
        fillInEmailField(login);
        fillInPasswordField(password);
        submitLoginNoAttl();
    }

    public String getErrorMessage(){
        waitUntilElementIsVisible(errorMessage, 10);
        //Output error message
        /*System.out.println("Error message: " + driver
                .findElements(By.cssSelector("p.error-message")).get(0).getText());
            Устарело
            */
       return errorMessage.getText();
    }

    public void fillInEmailField(String value) {
        //fill in email field;
        editField(emailField,value);
    }

    public void fillInPasswordField(String value) {
        //fill in password field
        editField(passwordField, value);
    }

    public void submitLoginNoAttl() {
        //press log in button
        loginButton.click();
    }

    public LoginPageHelper loginAsAttl(String login, String password){
       fillInEmailField(login);
       pressLoginAttlButton();
       fillInPasswordAttl(password);
       submitLoginAttl();
       return this;
    }

    public void pressLoginAttlButton() {
        waitUntilElementIsClickable(loginAsAttlButton, 5);
        // press 'Log in with Atlassian' button
        loginAsAttlButton.click();
    }

    public void fillInPasswordAttl(String volume) {
        waitUntilElementIsClickable(passwordField, 5);
        editField(passwordField, volume);
    }

    public void submitLoginAttl() {
        //  driver.findElement(By.id("login-submit")).click();
        waitUntilElementIsClickable(submitAsAttlButton, 5);
        submitAsAttlButton.click();
    }
}
