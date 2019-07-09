package localrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BaseClass {
    WebDriver driver;

    public BaseClass(WebDriver driver) {
        this.driver = driver;
    }

    By flights = By.id("tab-flight-tab-hp");
    By oneWayButton = By.id("flight-type-one-way-label-hp-flight");
    By flyingFrom = By.cssSelector("#flight-origin-hp-flight");//flight-origin-hp-flight
    By flyingTo = By.cssSelector("#flight-destination-hp-flight");
    By departingCalendar = By.cssSelector("#flight-departing-single-hp-flight");
    By SupportButton = By.cssSelector("#header-support-menu");
    By CustomerSupportButton = By.cssSelector("#support-cs");
    By hotelButton = By.cssSelector("#tab-hotel-tab-hp");
    By searchButtonHotel = By.id("hotel-destination-hp-hotel");

    // 1 task
    // I want to be able to schedule my flights six months in advance
    public WebElement Departing() {
        return driver.findElement(departingCalendar);
    }

    public WebElement From() {
        return driver.findElement(flyingFrom);
    }

    public WebElement To() {
        return driver.findElement(flyingTo);
    }

    public WebElement Flight() {
        return driver.findElement(flights);
    }

    public WebElement OneWay() {
        return driver.findElement(oneWayButton);
    }

    //    2 task
    // I want to be able to get customer support.
    public WebElement Support() {
        return driver.findElement(SupportButton);
    }

    public WebElement CustomerService() {
        return driver.findElement(CustomerSupportButton);
    }


    //3rd task
    //I want to get suggestion when i start searching for a destination.



    public WebElement HotelButton() {
        return driver.findElement(hotelButton);
    }

    public WebElement SearchButtonHotel() {
        return driver.findElement(searchButtonHotel);
    }
}
