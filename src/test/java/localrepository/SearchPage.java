package localrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchPage {
    WebDriver driver;

    public SearchPage(WebDriver driver) {
        this.driver = driver;
    }

    By newDestination = By.cssSelector("#arrival-airport-1");
    By search = By.cssSelector(".btn-secondary.btn-sub-action");
    By logo = By.cssSelector("#header-logo");
    By returnDay = By.cssSelector("#flight-returning-hp-flight");

    public WebElement setNewDestination(){
        return driver.findElement(newDestination);
    }
    public WebElement submit(){
        return driver.findElement(search);
    }

    public WebElement expediaButton(){
        return driver.findElement(logo);
    }

    public WebElement setReturnDay(){
        return driver.findElement(returnDay);
    }
}
