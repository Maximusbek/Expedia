package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.concurrent.TimeUnit;

public class DavidHovakimyan {

    static WebDriver driver;
    public static void main(String[] args) throws InterruptedException{

        WebDriverManager.chromedriver().setup();

        sortHotelsByGuestRating();

        rentCarByType();

        changeTheLanguage();

    }
    public static void changeTheLanguage()throws InterruptedException {
        //   I want to be able to change the language that is displayed.

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        Thread.sleep(4000);

        WebElement language = driver.findElement(By.xpath("(//a[@id='onPageModalPickerLink01'])[2]"));
        language.click();

        Thread.sleep(4000);
        driver.findElement(By.xpath("//button[@class='btn-mcml-language'][3]")).click();
        WebElement languageButton = driver.findElement(By.xpath("(//div[@class='picker-dropdown menu-bar'])[7]"));
        languageButton.click();
        Thread.sleep(1000);
        WebElement english = driver.findElement(By.xpath("(//*[@data-language-name='English'])[3]"));
        english.click();
        Thread.sleep(1000);
        WebElement saveButton = driver.findElement(By.id("multi-currency-multi-lang-save-btn-header"));
        saveButton.click();

        driver.close();
    }
    public static void sortHotelsByGuestRating()throws InterruptedException{
        // I should be able to sort the "Guest rating" of a hotel by the highest guest rating.

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        Thread.sleep(4000);

        WebElement hotelsButton = driver.findElement(By.id("tab-hotel-tab-hp"));
        hotelsButton.click();

        WebElement hoteldestination = driver.findElement(By.id("hotel-destination-hp-hotel"));
        hoteldestination.sendKeys("Chicago, Illinois" + Keys.ENTER);
        Thread.sleep(3000);

        WebElement GuestRatingRadioButton = driver.findElement(By.id("radio-guestRating-45"));
        GuestRatingRadioButton.click();
        String text = driver.findElement(By.className("playback-pill-grid-item-label")).getText();
        Assert.assertEquals(text,"Guest rating 4.5+");

        driver.close();
    }
    public static void rentCarByType() throws InterruptedException{
        //  I should be able to rent a car by "specific type" and drop it of in another state.

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://www.expedia.com/");
        Thread.sleep(4000);

        WebElement carButton = driver.findElement(By.id("tab-car-tab-hp"));
        carButton.click();
        Thread.sleep(3000);
        WebElement pickuplocation = driver.findElement(By.id("car-pickup-hp-car"));
        pickuplocation.sendKeys("   Chicago, IL");
        Thread.sleep(1000);
        WebElement dropofflocation = driver.findElement(By.id("car-dropoff-hp-car"));
        dropofflocation.sendKeys("   Los Angeles, CA");
        Thread.sleep(3000);
        WebElement startDate = driver.findElement(By.id("car-pickup-date-hp-car"));
        startDate.sendKeys("07/15/2019");
        driver.findElement(By.xpath("//button[@class='datepicker-close-btn close btn-text']")).click();
        Thread.sleep(2000);
       driver.findElement(By.cssSelector("[href*='options-hp-car']")).click();
       Thread.sleep(2000);
       WebElement options = driver.findElement(By.id("car-options-type-hp-car"));
       options.click();

       Select s = new Select(options);
       s.selectByVisibleText("Luxury");
       driver.findElement(By.id("gcw-submit-car")).click();
        Thread.sleep(3000);
        String text = driver.findElement(By.xpath("//button[@data-vtest-area='default_label']")).getAttribute("id");
        Assert.assertEquals(text,"luxury");

        driver.close();
    }
}
