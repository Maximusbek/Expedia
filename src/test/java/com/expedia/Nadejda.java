package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import localrepository.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Nadejda {
    static WebDriver driver;
    static BaseClass sf;
    @BeforeMethod
    public void navigate(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        sf = new BaseClass(driver);
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
    }
    @Test
    public void scheduleFlightEightMonthsInAdvance()throws InterruptedException{
        // Scenario: I want to be able to schedule my flights eight months in advance

        sf.Flight().click();
        sf.OneWay().click();
        sf.From().sendKeys("chi");
        sf.From().sendKeys(Keys.DOWN, Keys.ENTER);

        sf.To().sendKeys("atl");
        sf.To().sendKeys(Keys.DOWN, Keys.ENTER);


        sf.Departing().click();

        //Checking if i can choose a date  months in advance
        while (!driver.findElement(By.xpath("//div[@class='datepicker-cal-month']")).
                findElement(By.cssSelector(".datepicker-cal-month-header")).getText().contains("Mar")){
            driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']")).click();
            Thread.sleep(1000);
        }

        driver.findElement(By.cssSelector("[data-day='12']")).click();
        driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[1]")).click();

        if(driver.findElement(By.cssSelector(".title-city-text")).getText().contains("Select your departure to")){
            System.out.println("Verification passed");
        }else{
            System.out.println("Verification failed");
        }
    }
    @Test
    public void getCustomerSupport(){
        // Scenario2: I want to be able to get customer support.

        //clicking on suport button
        sf.Support().click();
        //clicking on customer service button from dropdown
        sf.CustomerService().click();
        //Checking if the result contains my input
        WebElement searchCS = driver.findElement(By.id("search-term"));
        String question = "Cancel my flight";
        if (searchCS.isDisplayed()) {
            searchCS.sendKeys(question + Keys.ENTER);
        } else {
            System.out.println("Verification of search customer service failed");
        }
        List<WebElement> listResults = driver.findElements(By.cssSelector("#article-list-container-- li"));

        boolean check = false;
        String[] words = question.split(" ");
        for (WebElement l : listResults) {
            for (String w : words) {
                if (l.getText().contains(w)) {
                    check = true;
                }
            }
        }
        if (check) {
            System.out.println("Verification for result customer support passed");
        } else {
            System.out.println("Verification for result customer support failed");
        }
    }
    @Test
    public void getSuggestionsWhenLookingForDestination(){
        //I want to get suggestion when i start searching for a destination.

        sf.HotelButton().click();
        sf.SearchButtonHotel().sendKeys("Chicago");
        //Checking if all sugestions contain the input
        List<WebElement> listOptions = driver.findElements(By.cssSelector("#typeaheadDataPlain a"));
        boolean check = true;
        for (WebElement w : listOptions) {
            if(!w.getAttribute("data-value").contains("Chicago")){
                check = false;
                System.out.println("Verification of dropdown auto-suggest FAILED");
            }
        }
        if(check){
            System.out.println("Verification of dropdown auto-suggest PASSED");

        }
    }
    @AfterMethod
    public void close(){
        driver.close();
    }
}
