package localrepository;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ExpediaHomePage2 {

    WebDriver driver;

    public ExpediaHomePage2(WebDriver driver){
        this.driver = driver;
    }
    By flights = By.cssSelector("#tab-flight-tab-hp");
    By departure = By.cssSelector("#flight-origin-hp-flight");
    By destination = By.cssSelector("#flight-destination-hp-flight");
    By departureDay = By.cssSelector("#flight-departing-hp-flight");
    By calendar = By.tagName("caption");
    By secondMonth = By.xpath("//div[@class='datepicker-cal-month'][2]");
    By next = By.cssSelector(".datepicker-paging.datepicker-next.btn-paging.btn-secondary.next");
    By days = By.tagName("button");
    By passenger = By.cssSelector("[class*='gcw-traveler-amount-select gcw-component-initialized']");
    By travelers = By.cssSelector(".traveler-selector-sinlge-room-data.traveler-selector-room-data");
    By children = By.cssSelector("#flight-age-select-1-hp-flight");
    By infants = By.cssSelector("[data-gcw-storeable-name='gcw-infant-age-1-1']");
    By search2 = By.cssSelector(".btn-primary.btn-action.gcw-submit");
    By closePassengers = By.cssSelector(".uitk-col .close.btn-text");
    By history = By.cssSelector("#header-history");
    By searches = By.cssSelector("[aria-controls='Recent Searches']");
    By packages = By.cssSelector(".uitk-cell.uitk-card-text-segment");

    public WebElement flightsButton(){
        return driver.findElement(flights);
    }
    public WebElement setDeparture(){
        return driver.findElement(departure);
    }
    public WebElement setDestination(){
        return driver.findElement(destination);
    }
    public WebElement setDepartureDay(){
        return  driver.findElement(departureDay);
    }
    public List<WebElement> getCalendarMonths(){
        return driver.findElements(calendar);
    }
    public WebElement targetMonth(){
        return driver.findElement(secondMonth).findElement(By.cssSelector(".datepicker-cal-month-header"));
    }
    public WebElement nextMonth(){
        return driver.findElement(next);
    }
    public List<WebElement> getListOfDays(){
        return driver.findElement(secondMonth).findElements(days);
    }
    public WebElement passengers(){
        return driver.findElement(passenger);
    }
    public List<WebElement> listOfPassengers(){
        return driver.findElement(travelers).findElements(By.cssSelector("[class='uitk-step-input-button uitk-step-input-plus']"));
    }
    public WebElement setChildren(){
        return driver.findElement(children) ;
    }
    public WebElement setInfants(){
        return driver.findElement(infants);
    }

    public WebElement closePassengertab(){
        return driver.findElement(closePassengers);
    }
    public WebElement Search(){
        return   driver.findElement(search2);
    }
    public WebElement searchHistory(){
        return driver.findElement(history);
    }
    public WebElement recentSearches(){
        return driver.findElement(searches);
    }
    public List<WebElement> getListOfPackages(){
        return driver.findElements(packages);
    }
    public void pickMonthandDay(String month, String day){
        List<WebElement> listOfMonths2 = getCalendarMonths();

        if(listOfMonths2.get(0).getText().contains(month)){
            List<WebElement> listOfDays2 = getCalendarMonths();

            for(int i=0; i<listOfDays2.size(); i++){
                if(listOfDays2.get(i).getAttribute("class").contains("disabled")){
                    continue;
                }else{
                    if(listOfDays2.get(i).getAttribute("data-day").equals(day)){
                        listOfDays2.get(i).click();
                        break;
                    }
                }
            }
        }else {
            while (!targetMonth().getText().contains(month)){
                nextMonth().click();
            }

        }
        List<WebElement> listOfDays2 = getListOfDays();

        for(int i=0; i<listOfDays2.size(); i++){
            if(listOfDays2.get(i).getAttribute("class").contains("disabled")){
                continue;
            }else{
                if(listOfDays2.get(i).getAttribute("data-day").equals(day)){
                    listOfDays2.get(i).click();
                    break;
                }
            }
        }
    }
}
