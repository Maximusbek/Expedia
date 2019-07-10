package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import localrepository.EventTicketsPage;
import localrepository.ExpediaHomepage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Cesar {
    static WebDriver driver;
    static ExpediaHomepage eh;
    static EventTicketsPage etp;

    @BeforeMethod
    public void navigate()
    {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        etp = new EventTicketsPage(driver);
        eh = new ExpediaHomepage(driver);
        driver.get("http://expedia.com");
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);

    }

    @Test
    public void searchUpcomingGamesCesar()
    {
        //1 - Clicks On "Things To Do" tab on the top navigation bar
        eh.thingsToDoTab().click();

        //2 - Attempts to find the "Event Tickets" tab (due to some selenium errors)
        //    Otherwise it navigates to that page directly
        try
        {
            eh.eventTicketsTab().click();
        }
        catch(Exception ex)
        {
            driver.navigate().to("https://www.expedia.com/event-tickets/?rfrr=BEXetix&utm_source=expedia_search&utm_medium=event_tickets&utm_campaign=expedia_search_events_tickets_tab");

        }
        //3 - Finally it searches up "Chicago Bears" within the search bar.
        finally
        {
            etp.getSearchBar().sendKeys("Chicago Bears" + Keys.ENTER);
        }

    }
    @Test
    public void searchConcertsByArtistCesar() throws InterruptedException
    {
        //1 - Clicks On "Things To Do" tab on the top navigation bar
        eh.thingsToDoTab().click();

        //2 - Attempts to find the "Event Tickets" tab (due to some selenium errors)
        //    Otherwise it navigates to that page directly
        try
        {
            eh.eventTicketsTab().click();
        }
        catch(Exception ex)
        {
            driver.navigate().to("https://www.expedia.com/event-tickets/?rfrr=BEXetix&utm_source=expedia_search&utm_medium=event_tickets&utm_campaign=expedia_search_events_tickets_tab");

        }
        //3 - Finally it searches up "ACDC" within the search bar and hits ENTER.
        finally
        {
           Thread.sleep(3000);
            etp.getSearchBar().sendKeys("ACDC" + Keys.ENTER);
        }
    }
    @AfterMethod
    public void close()
    {
        driver.close();
    }

    @Test
    public void findHomeGameCesar() throws InterruptedException
    {
        //1 - Clicks On "Things To Do" tab on the top navigation bar
        eh.thingsToDoTab().click();

        //2 - Attempts to find the "Event Tickets" tab (due to some selenium errors)
        //    Otherwise it navigates to that page directly
        try
        {
            eh.eventTicketsTab().click();
        }
        catch(Exception ex)
        {
            driver.navigate().to("https://www.expedia.com/event-tickets/?rfrr=BEXetix&utm_source=expedia_search&utm_medium=event_tickets&utm_campaign=expedia_search_events_tickets_tab");
        }
        finally
        {
            Thread.sleep(4000);

            //Clicks On "Destination" & clicks on "Current Location"
            etp.getDestinationLink().click();
            etp.getCurrentLocationLink().click();

            //Searches "Chicago Bears" in the search bar
            Thread.sleep(3000);
            etp.getSearchBar().sendKeys("Chicago Bears" + Keys.ENTER);

            //Now we should choose the first option if the it says 'Chicago, IL'
            //That means that game will be home game
            if(driver.findElement(By.xpath("//div[@class = 'event-list']/div/a/table/tbody/tr/td[2]/div/div[2]/span/span")).getText().contains("Chicago"))
            {
                //Click on the element
                driver.findElement(By.xpath("//div[@class = 'event-list']/div/a")).click();

            }
        }


    }
}
