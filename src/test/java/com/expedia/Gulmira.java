package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Gulmira {

    static WebDriver driver;

    public static void main(String[] args)throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        sortHotelPriceMethod1GulmiraKadyrova();
        vecationRentalsSearchMethod2GulmiraKadyrova();
        hotelReviewAndRatingMethod3GulmiraKadyrova();

    }
    public static void sortHotelPriceMethod1GulmiraKadyrova() throws InterruptedException{
        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://www.expedia.com/");
        WebElement hotels = driver.findElement(By.cssSelector("#tab-hotel-tab-hp"));
        hotels.click();
        Thread.sleep(3000);
        WebElement goingTo = driver.findElement(By.xpath("//input[@data-city_element='hotel-destination-hp-hotel-city']"));
        goingTo.sendKeys("Chicago, Illinois");
        WebElement checkIn = driver.findElement(By.id("hotel-checkin-hp-hotel"));
        checkIn.click();
        Thread.sleep(3000);
        WebElement checkInDate = driver.findElement(By.xpath("//button[@data-day='31'][1]"));
        checkInDate.click();
        Thread.sleep(3000);
        WebElement checkOut = driver.findElement(By.id("hotel-checkout-hp-hotel"));
        checkOut.click();
        Thread.sleep(3000);
        WebElement checkOutDate = driver.findElement(By.xpath("//button[@data-day='3']"));
        checkOutDate.click();
        Thread.sleep(3000);
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action  gcw-submit'][1]"));
        search.click();
        Thread.sleep(3000);
        WebElement sortBy = driver.findElement(By.xpath("//input[@id='radio-sort-price']"));
        sortBy.click();
        if(sortBy.isSelected()){
            System.out.println("Sort by button verification is Passes");
        }else{
            System.out.println("Sort by button verification is Failed");
        }
        Thread.sleep(3000);
        WebElement sortByPrice = driver.findElement(By.xpath("//label[@for='radio-sort-price']"));
        if(sortByPrice.isDisplayed()){
            System.out.println("Price verification is Passed");
        }else{
            System.out.println("Price verification is Failed");
        }
        driver.close();
    }
    public static void vecationRentalsSearchMethod2GulmiraKadyrova()throws InterruptedException{

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://www.expedia.com/");
        WebElement vacationRentals = driver.findElement(By.id("primary-header-vacationRental"));
        vacationRentals.click();
        WebElement destination = driver.findElement(By.id("VR-destination"));
        destination.sendKeys("Malibu, California");
        WebElement datePicker = driver.findElement(By.xpath("//input[@id='VR-fromDate']"));
        datePicker.click();
        WebElement checkIn = driver.findElement(By.xpath("(//button[@data-day='14'])[2]"));
        checkIn.click();
        Thread.sleep(4000);
        WebElement datePicker2 = driver.findElement(By.xpath("//input[@id='VR-toDate']"));
        datePicker2.click();
        Thread.sleep(4000);
        WebElement checkOut = driver.findElement(By.xpath("//button[@class='datepicker-cal-date end']"));
        checkOut.click();
        Thread.sleep(4000);
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action']"));
        search.click();
        Thread.sleep(5000);
        WebElement checkInDate = driver.findElement(By.xpath("(//span[@aria-hidden='true'])[2]"));
        if (checkInDate.getText().equals("Aug 14")){
            System.out.println("Date verification is Passed");
        }else{
            System.out.println("Date verification is Failed");
        }
        Thread.sleep(3000);
        WebElement checkOutDate = driver.findElement(By.xpath("(//span[@aria-hidden='true'])[3]"));
        if(checkOutDate.getText().equals("Aug 15")){
            System.out.println("Date verification is Passed");
        }else{
            System.out.println("Date verification is Failed");
        }
        driver.close();
    }
    public static void hotelReviewAndRatingMethod3GulmiraKadyrova()throws InterruptedException{

        driver = new ChromeDriver();
        driver.manage().window().fullscreen();
        driver.get("https://www.expedia.com/");
        WebElement hotels = driver.findElement(By.cssSelector("#tab-hotel-tab-hp"));
        hotels.click();
        Thread.sleep(3000);
        WebElement goingTo = driver.findElement(By.xpath("//input[@data-city_element='hotel-destination-hp-hotel-city']"));
        goingTo.sendKeys("Chicago, Illinois");
        WebElement checkIn = driver.findElement(By.id("hotel-checkin-hp-hotel"));
        checkIn.click();
        Thread.sleep(3000);
        WebElement checkInDate = driver.findElement(By.xpath("//button[@data-day='31'][1]"));
        checkInDate.click();
        Thread.sleep(3000);
        WebElement checkOut = driver.findElement(By.id("hotel-checkout-hp-hotel"));
        checkOut.click();
        Thread.sleep(3000);
        WebElement checkOutDate = driver.findElement(By.xpath("//button[@data-day='3']"));
        checkOutDate.click();
        WebElement search = driver.findElement(By.xpath("//button[@class='btn-primary btn-action  gcw-submit'][1]"));
        search.click();
        Thread.sleep(3000);
        List<WebElement> listReviewAndRating = driver.findElements(By.cssSelector(".listing__reviews"));
        boolean check = false;
        for (WebElement list: listReviewAndRating) {
            if (!list.isDisplayed()) {
                System.out.println("List of Reviews and Ratings are Failed");
                check = true;
                break;
            }
        }
        if (check == false) {
            System.out.println("List of Reviews and Ratings are Passed");
        }
        driver.close();
    }

}
