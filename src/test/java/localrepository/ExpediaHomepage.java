package localrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ExpediaHomepage {

    WebDriver driver;

    public ExpediaHomepage(WebDriver driver)
    {
        this.driver = driver;
    }

    By thingsToDo = By.cssSelector("#tab-activity-tab-hp");
    By eventTickets = By.cssSelector(".link.event-tickets-link");

    public WebElement thingsToDoTab()
    {
        return driver.findElement(thingsToDo);
    }
    public WebElement eventTicketsTab()
    {
        return driver.findElement(eventTickets);
    }

}
