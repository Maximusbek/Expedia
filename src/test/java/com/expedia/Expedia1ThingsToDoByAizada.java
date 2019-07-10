package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
/*
My scenario:
 I want to be able to search for activities on
 “thing to go” in a certain area and in a specific timeframe
 */

public class Expedia1ThingsToDoByAizada {
    WebDriver driver;
        @BeforeMethod
                public void setUp(){
        WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.get("http://expedia.com");
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.manage().window().maximize();
    }
    @Test
    public  void thingsToDo1() throws InterruptedException{

        WebElement thingsToDo = driver.findElement(By.cssSelector("#tab-activity-tab-hp"));
        thingsToDo.click();
        WebElement destination = driver.findElement(By.cssSelector("#activity-destination-hp-activity"));
        Thread.sleep(3000);
        destination.sendKeys("Wyomi");
        Thread.sleep(3000);
        Thread.sleep(3000);
        for(int i = 0; i < 5; i++) {
            destination.sendKeys(Keys.DOWN);
        }
        destination.sendKeys(Keys.ENTER);
        WebElement dateBox = driver.findElement(By.cssSelector("#activity-start-hp-activity"));
        dateBox.click();
        Thread.sleep(3000);
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        nextButton.click();
        Thread.sleep(3000);
        WebElement dateFrom= driver.findElement(By.cssSelector("[data-day='25']"));
        dateFrom.click();
        Thread.sleep(3000);
        WebElement dateBoxTo = driver.findElement(By.cssSelector("#activity-end-hp-activity"));
        dateBoxTo.click();
        WebElement dateTo = driver.findElement(By.cssSelector("[data-day='30']"));
        dateTo.click();
        WebElement searchButton = driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[4]"));
        searchButton.click();
        Thread.sleep(7000);
    }
    @Test(priority = 1)
    public  void pressOnTickets2() throws InterruptedException{
        WebElement thingsToDo = driver.findElement(By.cssSelector("#tab-activity-tab-hp"));
        thingsToDo.click();
        WebElement destination = driver.findElement(By.cssSelector("#activity-destination-hp-activity"));
        Thread.sleep(3000);
        destination.sendKeys("Wyomi");
        Thread.sleep(3000);

        Thread.sleep(3000);
        for(int i = 0; i < 5; i++) {
            destination.sendKeys(Keys.DOWN);
        }
        destination.sendKeys(Keys.ENTER);
        WebElement dateBox = driver.findElement(By.cssSelector("#activity-start-hp-activity"));
        dateBox.click();
        Thread.sleep(3000);
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        nextButton.click();
        Thread.sleep(3000);
        WebElement dateFrom= driver.findElement(By.cssSelector("[data-day='25']"));
        dateFrom.click();
        Thread.sleep(3000);
        WebElement dateBoxTo = driver.findElement(By.cssSelector("#activity-end-hp-activity"));
        dateBoxTo.click();
        WebElement dateTo = driver.findElement(By.cssSelector("[data-day='30']"));
        dateTo.click();
        WebElement searchButton = driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[4]"));
        searchButton.click();
        Thread.sleep(7000);
        WebElement ticketsAtractions = driver.findElement(By.cssSelector("#categoryFilter6"));
        Actions down = new Actions(driver);
        down.moveToElement(ticketsAtractions);
        ticketsAtractions.click();
        Thread.sleep(10000);

}@Test (priority = 2)
        public  void pressOnInterests() throws InterruptedException{
            WebElement thingsToDo = driver.findElement(By.cssSelector("#tab-activity-tab-hp"));
            thingsToDo.click();
            WebElement destination = driver.findElement(By.cssSelector("#activity-destination-hp-activity"));
            Thread.sleep(3000);
            destination.sendKeys("Wyomi");
            Thread.sleep(3000);

            for(int i = 0; i < 5; i++) {
                destination.sendKeys(Keys.DOWN);
            }
                destination.sendKeys(Keys.ENTER);
                WebElement dateBox = driver.findElement(By.cssSelector("#activity-start-hp-activity"));
                dateBox.click();
                Thread.sleep(3000);
                WebElement nextButton = driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
                nextButton.click();
                Thread.sleep(3000);
                WebElement dateFrom= driver.findElement(By.cssSelector("[data-day='25']"));
                dateFrom.click();
                Thread.sleep(3000);
                WebElement dateBoxTo = driver.findElement(By.cssSelector("#activity-end-hp-activity"));
                dateBoxTo.click();
                WebElement dateTo = driver.findElement(By.cssSelector("[data-day='30']"));
                dateTo.click();
                WebElement searchButton = driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[4]"));
                searchButton.click();
                Thread.sleep(7000);

            WebElement interestsAtractions = driver.findElement(By.cssSelector("#categoryFilter7"));
            Actions down = new Actions(driver);
            down.moveToElement(interestsAtractions);
            interestsAtractions.click();
            Thread.sleep(7000);
        }

        }