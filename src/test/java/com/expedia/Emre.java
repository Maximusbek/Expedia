package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
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

public class Emre {
    static  WebDriver driver;
    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
    }

    @Test
    public static void BundleAndSaveEmre() throws InterruptedException {
        /*EMRE*/
        /*I want to bundle flight and hotel and get savings*/

        WebElement bundleAndSave = driver.findElement(By.id("primary-header-package"));
        bundleAndSave.click();
        Thread.sleep(5000);
        WebElement origin = driver.findElement(By.xpath("//input[@id='package-origin-plp-fh']"));
        origin.click();
        origin.clear();
        origin.sendKeys("chi");
        Thread.sleep(3000);
        origin.sendKeys(Keys.DOWN);
        origin.sendKeys(Keys.ENTER);

        WebElement destination = driver.findElement(By.cssSelector("#package-destination-plp-fh"));
        destination.click();
        destination.sendKeys("new");
        Thread.sleep(3000);
        destination.sendKeys(Keys.DOWN);
        destination.sendKeys(Keys.ENTER);

        WebElement departing = driver.findElement(By.id("package-departing-plp-fh"));
        departing.click();
        WebElement jul2019ForDeparting = driver.findElement(By.xpath("//caption[contains(text(),'Aug 2019')]"));
        WebElement departingDay = driver.findElement(By.xpath("(//button[@data-day='29'])[2]"));

        if(jul2019ForDeparting.getText().contains("Aug 2019")){
            departingDay.click();
            /*selecting August 29*/
        }

        Thread.sleep(3000);
        WebElement returning = driver.findElement(By.cssSelector("#package-returning-plp-fh"));
        returning.click();
        WebElement jul2019ForReturning = driver.findElement(By.xpath("//caption[contains(text(),'Aug 2019')]"));
        WebElement returningDay = driver.findElement(By.xpath("(//button[@data-day='30'])[1]"));
        if(jul2019ForReturning.getText().contains("Aug 2019")) {
            returningDay.click();
            /*selecting August 30*/
        }

        WebElement search = driver.findElement(By.id("search-button-plp-fh"));
        search.click();

    }

    @Test
    public static void RoundTripEmre() throws InterruptedException {
        /*EMRE*/
        /*I want to be able to shedule a roundtrip flight.*/

        WebElement flight = driver.findElement(By.cssSelector("#tab-flight-tab-hp"));
        flight.click();

        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-hp-flight']"));
        flyingFrom.click();
        flyingFrom.clear();
        flyingFrom.sendKeys("chi");
        Thread.sleep(3000);
        flyingFrom.sendKeys(Keys.DOWN);
        flyingFrom.sendKeys(Keys.ENTER);


        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-hp-flight']"));
        flyingTo.click();
        flyingTo.clear();
        flyingTo.sendKeys("turkey");
        Thread.sleep(3000);
        flyingTo.sendKeys(Keys.DOWN);
        flyingTo.sendKeys(Keys.ENTER);

        WebElement departing = driver.findElement(By.cssSelector("#flight-departing-hp-flight"));
        departing.click();

        WebElement arrowOnCalendarForDeparting = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForDeparting.click();
        /*to see the next month*/

        WebElement departingDay = driver.findElement(By.xpath("(//button[@data-month='8'])[5]"));
        departingDay.click();
        /*selecting Sep 5*/

        WebElement returning = driver.findElement(By.id("flight-returning-hp-flight"));
        returning.click();

        WebElement arrowOnCalendarForReturning = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForReturning.click();

        WebElement returningDay = driver.findElement(By.xpath("(//button[@data-month='8'])[25]"));
        returningDay.click();
        /*selecting Sep 29*/

        WebElement search = driver.findElement(By.xpath
                ("(//button[@class='btn-primary btn-action gcw-submit'])[1]"));
        search.click();

        Thread.sleep(10000);
        WebElement selectFly = driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        selectFly.click();

        Thread.sleep(3000);
        WebElement selectThisFare = driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        try {
            selectThisFare.click();
        }catch (Exception e){

        }

        Thread.sleep(3000);
        WebElement selectSecondFly =driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        try {
            selectSecondFly.click();
        }catch (Exception e){

        }

        Thread.sleep(3000);
        WebElement noThanks = driver.findElement(By.xpath("//a[@id='forcedChoiceNoThanks']"));

        WebElement selectThisFare2 = driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        if(!noThanks.isDisplayed()) {
            selectThisFare2.click();
        }


        Thread.sleep(7000);


        if(noThanks.isDisplayed()) {
            noThanks.click();
        }

    }

    @Test
    public static void BestPricesEmre() throws InterruptedException {
        /*EMRE*/
        /*I want to get the best prices first when searching for flights.*/

        WebElement flight = driver.findElement(By.cssSelector("#tab-flight-tab-hp"));
        flight.click();

        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-hp-flight']"));
        flyingFrom.click();
        flyingFrom.clear();
        flyingFrom.sendKeys("chi");
        Thread.sleep(3000);
        flyingFrom.sendKeys(Keys.DOWN);
        flyingFrom.sendKeys(Keys.ENTER);

        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-hp-flight']"));
        flyingTo.click();
        flyingTo.clear();
        flyingTo.sendKeys("turkey");
        Thread.sleep(3000);
        flyingTo.sendKeys(Keys.DOWN);
        flyingTo.sendKeys(Keys.ENTER);

        WebElement departing = driver.findElement(By.cssSelector("#flight-departing-hp-flight"));
        departing.click();

        WebElement arrowOnCalendarForDeparting = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForDeparting.click();
        /*to see the next month*/

        WebElement departingDay = driver.findElement(By.xpath("(//button[@data-month='8'])[5]"));
        departingDay.click();
        /*selecting Sep 5*/

        WebElement returning = driver.findElement(By.id("flight-returning-hp-flight"));
        returning.click();

        WebElement arrowOnCalendarForReturning = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForReturning.click();

        WebElement returningDay = driver.findElement(By.xpath("(//button[@data-month='8'])[25]"));
        returningDay.click();
        /*selecting Sep 25*/

        WebElement search = driver.findElement(By.xpath
                ("(//button[@class='btn-primary btn-action gcw-submit'])[1]"));
        search.click();

        List<WebElement> listOfPrices = driver.findElements(By.id("listing-price-dollars"));
        listOfPrices.size();

        for(int i = 0; i < listOfPrices.size()-1; i++){
            if(Integer.valueOf(listOfPrices.get(i).getText().replace("$","").replace(",",""))
                    > Integer.valueOf(listOfPrices.get(i+1).getText().replace("$","").replace(",",""))){
                System.out.println("Best prices verification FAILED");
            }
        }
        System.out.println("Best prices verification PASSED");
    }


}
