package localrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventTicketsPage {

    WebDriver driver;

    public EventTicketsPage(WebDriver driver)
    {
        this.driver = driver;
    }

    By destinationLink = By.cssSelector("#locationLink");
    By currentLocationLink = By.cssSelector("#currentLocation");
    By searchBar = By.name("q");



    public WebElement getDestinationLink ( )
    {
        return driver.findElement(destinationLink);
    }
    public WebElement getCurrentLocationLink()
    {
        return driver.findElement(currentLocationLink);
    }
    public WebElement getSearchBar()
    {
        return driver.findElement(searchBar);
    }



}
