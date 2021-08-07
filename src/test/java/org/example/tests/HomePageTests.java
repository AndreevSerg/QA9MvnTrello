package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase{
    //HomePageHelper homePage;


        //@BeforeMethod
        //public void initTest(){
        //homePage = new HomePageHelper(driver);
        //homePage = PageFactory.initElements(driver,HomePageHelper.class);
        //homePage.waitUntilPageIsLoaded();
    //}

    @Test(groups = {"smoke", "regression", "system"})
    public void verifyApplTest(){

            log4j.startTestCase("VerifyApplTest");
            Assert.assertTrue(homePage.isCorrectPage());
    }

}