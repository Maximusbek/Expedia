package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Gulmira {

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

    // want to be able to sort my hotels by the price.

    @Test(priority = 1)
    public void gulmira_sortHotelPriceMethod1_test() throws InterruptedException {
        WebElement hotels = driver.findElement(By.cssSelector("#tab-hotel-tab-hp"));
        hotels.click();
        WebElement goingTo = driver.findElement(By.xpath("//input[@data-city_element='hotel-destination-hp-hotel-city']"));
        goingTo.sendKeys("Chicago");
        WebElement checkIn = driver.findElement(By.id("hotel-checkin-hp-hotel"));
        checkIn.click();
        WebElement checkInDate = driver.findElement(By.xpath("//button[@data-day='31'][1]"));
        Thread.sleep(3000);
        checkInDate.click();
        WebElement checkOut = driver.findElement(By.id("hotel-checkout-hp-hotel"));
        checkOut.click();
        WebElement checkOutDate = driver.findElement(By.xpath("//button[@data-day='3']"));
        Thread.sleep(3000);
        checkOutDate.click();
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action  gcw-submit'][1]"));
        search.click();
        WebElement sortBy = driver.findElement(By.xpath("//input[@id='radio-sort-price']"));
        sortBy.click();
        if (sortBy.isSelected()) {
            System.out.println("Sort by button verification is Passes");
        } else {
            System.out.println("Sort by button verification is Failed");
        }
        Thread.sleep(3000);
        WebElement sortByPrice = driver.findElement(By.xpath("//label[@for='radio-sort-price']"));
        if (sortByPrice.isDisplayed()) {
            System.out.println("Price verification is Passed");
        } else {
            System.out.println("Price verification is Failed");
        }
    }
    //I want to be able to search for vacation rentals and get a list with the available dates that im looking for

    @Test(priority = 2)
    public void gulmira_vacationRentalsSearchMethod2_test() throws InterruptedException {
        WebElement vacationRentals = driver.findElement(By.id("primary-header-vacationRental"));
        vacationRentals.click();
        WebElement destination = driver.findElement(By.id("VR-destination"));
        destination.sendKeys("Malibu, California");
        WebElement datePicker = driver.findElement(By.xpath("//input[@id='VR-fromDate']"));
        datePicker.click();
        WebElement checkIn = driver.findElement(By.xpath("(//button[@data-day='14'])[2]"));
        checkIn.click();
        WebElement datePicker2 = driver.findElement(By.xpath("//input[@id='VR-toDate']"));
        datePicker2.click();
        WebElement checkOut = driver.findElement(By.xpath("//button[@class='datepicker-cal-date end']"));
        checkOut.click();
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action']"));
        search.click();
        WebElement checkInDate = driver.findElement(By.xpath("(//span[@aria-hidden='true'])[2]"));
        if (checkInDate.getText().equals("Aug 14")) {
            System.out.println("Date verification is Passed");
        } else {
            System.out.println("Date verification is Failed");
        }
        Thread.sleep(3000);
        WebElement checkOutDate = driver.findElement(By.xpath("(//span[@aria-hidden='true'])[3]"));
        if (checkOutDate.getText().equals("Aug 15")) {
            System.out.println("Date verification is Passed");
        } else {
            System.out.println("Date verification is Failed");
        }
    }
    //I want to see the number of reviews and rating of each hotel

    @Test(priority = 3)
    public void gulmira_hotelReviewAndRatingMethod3_test() throws InterruptedException {
        WebElement hotels = driver.findElement(By.cssSelector("#tab-hotel-tab-hp"));
        hotels.click();
        WebElement goingTo = driver.findElement(By.xpath("//input[@data-city_element='hotel-destination-hp-hotel-city']"));
        goingTo.sendKeys("Chicago, Illinois");
        WebElement checkIn = driver.findElement(By.id("hotel-checkin-hp-hotel"));
        checkIn.click();
        WebElement checkInDate = driver.findElement(By.xpath("//button[@data-day='31'][1]"));
        Thread.sleep(5000);
        checkInDate.click();
        WebElement checkOut = driver.findElement(By.id("hotel-checkout-hp-hotel"));
        checkOut.click();
        WebElement checkOutDate = driver.findElement(By.xpath("//button[@data-day='3']"));
        Thread.sleep(5000);
        checkOutDate.click();
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action  gcw-submit'][1]"));
        search.click();
        Thread.sleep(3000);
        List<WebElement> listReviewAndRating = driver.findElements(By.cssSelector(".listing__reviews"));
        boolean check = false;
        for (WebElement list : listReviewAndRating) {
            if (!list.isDisplayed()) {
                System.out.println("List of Reviews and Ratings are Failed");
                check = true;
                break;
            }
        }
        if (check == false) {
            System.out.println("List of Reviews and Ratings are Passed");
        }
    }
}




