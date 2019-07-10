package com.expedia;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;
public class DavidHovakimyan {

    WebDriver driver;

    @BeforeMethod
        public void setUp() throws InterruptedException {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("https://www.expedia.com/");
            Thread.sleep(3000);
    }

    //   I want to be able to change the language that is displayed.

    @Test(priority = 1)
    public void David_ChangeLanguage_Test() throws InterruptedException {
        WebElement language = driver.findElement(By.xpath("(//a[@id='onPageModalPickerLink01'])[2]"));
        language.click();
        Thread.sleep(4000);
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

    //  I should be able to rent a car by "specific type" and drop it of in another state.

    @Test(priority = 2)
    public void David_RentCar_Test() throws InterruptedException {
        WebElement carButton = driver.findElement(By.id("tab-car-tab-hp"));
        carButton.click();
        Thread.sleep(3000);
        WebElement pickuplocation = driver.findElement(By.id("car-pickup-hp-car"));
        pickuplocation.sendKeys("Chicago, IL");
        Thread.sleep(1000);
        WebElement dropofflocation = driver.findElement(By.id("car-dropoff-hp-car"));
        dropofflocation.sendKeys("Los Angeles, CA");
        Thread.sleep(3000);
        WebElement startDate = driver.findElement(By.id("car-pickup-date-hp-car"));
        startDate.sendKeys("07/15/2019" + Keys.ENTER);
        Thread.sleep(1000);
    }

    // I should be able to sort the "Guest rating" of a hotel by the highest guest rating.

    @Test(priority = 3)
    public void David_GuestRating_Test() throws InterruptedException {
        WebElement hotelsButton = driver.findElement(By.id("tab-hotel-tab-hp"));
        hotelsButton.click();
        WebElement hoteldestination = driver.findElement(By.id("hotel-destination-hp-hotel"));
        hoteldestination.sendKeys("Chicago, Illinois");
        WebElement searchButton = driver.findElement(By.xpath("(//button[@data-gcw-change-submit-text='Search'])[1]"));
        searchButton.click();
        Thread.sleep(2000);
        WebElement GuestRatingRadioButton = driver.findElement(By.id("radio-guestRating-45"));
        GuestRatingRadioButton.click();

    }
}