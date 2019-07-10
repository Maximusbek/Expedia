package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import localrepository.ExpediaHomePage2;
import localrepository.SearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class BugHunterz {

    static WebDriver driver;
    static WebDriverWait d;
    static ExpediaHomePage2 eh;
    static SearchPage sp;


    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void navigate() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        eh = new ExpediaHomePage2(driver);
        sp = new SearchPage(driver);
        d = new WebDriverWait(driver, 15);
        driver.get("https://www.expedia.com/");

    }

    public void HamzaUtil(){
        eh.flightsButton().click();
        eh.setDeparture().sendKeys("   chi" + Keys.SHIFT);
        eh.setDeparture().sendKeys(Keys.DOWN, Keys.DOWN);
        eh.setDeparture().sendKeys(Keys.ENTER);

        eh.setDestination().sendKeys("   oma" + Keys.SHIFT);
        eh.setDestination().sendKeys(Keys.DOWN, Keys.DOWN);
        eh.setDestination().sendKeys(Keys.ENTER);


        eh.setDepartureDay().click();
        eh.pickMonthandDay("Dec","11");

        sp.setReturnDay().click();
        eh.pickMonthandDay("Dec","15");

        eh.passengers().click();

        List<WebElement> listOfPassengers = eh.listOfPassengers();
        listOfPassengers.get(0).click();
        listOfPassengers.get(0).click();
        listOfPassengers.get(1).click();

        Select s = new Select(eh.setChildren());
        s.selectByVisibleText("4");
        listOfPassengers.get(2).click();

        s = new Select(eh.setInfants());
        s.selectByVisibleText("1");

        eh.closePassengertab().click();
        eh.Search().click();
    }

    @AfterMethod
    public void tearDown() throws InterruptedException{
        Thread.sleep(2000);
        driver.close();
    }

    @Test(priority = 1)
    public void airportTransportationToHotelByKonstantin() throws InterruptedException{

//      When  I search for airport transportation to specific hotel User should see map of the route
//      with time to destination.

//      1. Locate and click on Things to Do link
        WebElement thingsToDo = driver.findElement(By.id("tab-activity-tab-hp"));
        thingsToDo.click();
        Thread.sleep(2000);
//      2. Due to dynamic structure of the web-site "Airport Transportation" link is dependent on "Event Tickets link
//             that couldn't be displayed all the time.
        List<WebElement> listOfLabels = driver.findElement(By.cssSelector("#gcw-activities-form-hp-activity")).findElements(By.tagName("label"));
        for(WebElement lable: listOfLabels){
            if(lable.getText().equalsIgnoreCase("Airport Transportation")){
                lable.click();
            }
        }
        Thread.sleep(2000);
//      3. Selecting and inserting Airport of arrival
        WebElement fromAirport = driver.findElement(By.cssSelector("#flight-origin-hp-activity"));
        fromAirport.sendKeys("ORD" + Keys.SHIFT);
        Thread.sleep(1000);
        fromAirport.sendKeys(Keys.DOWN, Keys.ENTER);
//      4. Selecting date of arrival
        WebElement flightArrivalDate = driver.findElement(By.cssSelector("#gt-origin-flight-arrival-date-hp-activity"));
        flightArrivalDate.clear();
        flightArrivalDate.sendKeys("09/11/2019");
//      5. Locating and selecting destination Hotel
        WebElement toHotel = driver.findElement(By.cssSelector("#hotel-hp-activity"));
        toHotel.sendKeys("Waldorf Astoria Chicago, Chicago, Illinois");
        toHotel.sendKeys(Keys.DOWN, Keys.ENTER);
//      6. Verifying the result
        WebElement durationToDestination = driver.findElement(By.xpath("//span[@class='map-duration-text alt']"));
        System.out.println(durationToDestination.getText() + " from ORD to Waldorf Astoria, Chicago.");

    }

    @Test(priority = 2)
    public static void searchForCruiseByTimeDestinationByKonstantin() throws InterruptedException{

//      Verifying how many cruises available for selection after specifying destination and date.

//      1. Locate and select Cruises
        WebElement cruisesHeader = driver.findElement(By.id("primary-header-cruise"));
        cruisesHeader.click();
        Thread.sleep(1000);
//      2. Locate and select destination of the cruise
        WebElement goingTo = driver.findElement(By.xpath("//select[@class='cruise-destination gcw-storeable']"));
        Select goingToDropDown = new Select(goingTo);
        Thread.sleep(1000);
        goingToDropDown.selectByVisibleText("Alaska");
//      3. Select date of departure
        WebElement departAsEarlyAs = driver.findElement(By.id("cruise-start-date-cruiselp"));
        departAsEarlyAs.sendKeys("08/11/2019");
        Thread.sleep(2000);
//      4. Submit the requiest
        WebElement submitButton = driver.findElement(By.xpath("//button[@class='btn-primary btn-action gcw-submit ab14933']"));
        submitButton.click();
//      5. Cheching the results of the search
        WebElement howManyCruisesFound = driver.findElement(By.xpath("//h1[@class='cruise-page-header universalFilterAriaHidden header']"));
        System.out.println(howManyCruisesFound.getText() + " found for your search criteria.");

    }

    @Test(priority = 3)
    public static void filteringCarsByTypeByKonstantin() throws InterruptedException{

//      Filtering car's search by car type

//      1. Locating and selscting Car link in header
        WebElement carsHeaderLink = driver.findElement(By.id("primary-header-car"));
        carsHeaderLink.click();
        Thread.sleep(2000);
//      2. Setting pickup lication
        WebElement pickingUpLocation = driver.findElement(By.xpath("//label[@class='text icon-before autocomplete-arrow']"));
        pickingUpLocation.sendKeys("ORD" + Keys.SHIFT);
        Thread.sleep(2000);
        pickingUpLocation.sendKeys(Keys.DOWN, Keys.ENTER);
        Thread.sleep(2000);
//      3. Setting dropping off loction
        WebElement dropingOfLocation = driver.findElement(By.xpath("//input[@class='clear-btn-input gcw-storeable text gcw-carDropOff']"));
        dropingOfLocation.sendKeys("BOS");
        Thread.sleep(2000);
        dropingOfLocation.sendKeys(Keys.DOWN, Keys.ENTER);
//      4. Selecting date of car's pickup
        WebElement pickUpDate = driver.findElement(By.xpath("//input[@id='car-pickup-date-clp']"));
        pickUpDate.sendKeys("07/22/2019");
//      5. Selecting date of car's drop of
        WebElement dropOfDate = driver.findElement(By.xpath("//input[@id='car-dropoff-date-clp']"));
        dropOfDate.sendKeys("08/08/2019");
        Thread.sleep(2000);
//      6. Selecting advanced options for search
        WebElement advancedOptions = driver.findElement(By.linkText("Advanced options"));
        advancedOptions.click();
        Thread.sleep(1000);
//      7. Locating and selecting car type
        WebElement carType = driver.findElement(By.xpath("//select[@name='kind']"));
        Select selectCarDropDown = new Select(carType);
        selectCarDropDown.selectByVisibleText("Minivan");
        Thread.sleep(1000);
//      8. Submitting the request
        WebElement submitButton = driver.findElement(By.id("gcw-submit-car"));
        submitButton.click();

    }


    @Test(priority = 4)
    public void David_ChangeingTheLanguage_Test() throws InterruptedException {
        WebElement supportButton = driver.findElement(By.id("header-support-menu"));
        supportButton.click();
        Thread.sleep(2000);
        WebElement customerSupportButton = driver.findElement(By.cssSelector("#support-cs"));
        customerSupportButton.click();
        Thread.sleep(2000);
        WebElement messageCustomerSupport = driver.findElement(By.xpath("//h3[@class='aside-prompt']"));
        if(messageCustomerSupport.isDisplayed()){
        String messageDisplayed = messageCustomerSupport.getText();
        String expectedMessage = "We're here to help.";
        Assert.assertEquals(messageDisplayed, expectedMessage, "Message displayed is different from requirement");
        } else{
            System.out.println("Expected message is not displayed");
        }

    }


    @Test(priority = 5)
    public void David_RentingACarByModel_Test() throws InterruptedException {
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


    @Test(priority = 6)
    public void David_LookAHotelByGuestRating_Test() throws InterruptedException {
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
    @Test(priority = 7)

    public void editMySearchResults(){
        HamzaUtil();
        //   Once i put my search preferences  i should be able to change it from the same page.

        String cityName = sp.setNewDestination().getAttribute("value");
        sp.setNewDestination().clear();
        sp.setNewDestination().sendKeys("  New");
        sp.setNewDestination().sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
        sp.submit();


        String newCityName = sp.setNewDestination().getAttribute("value");
        System.out.println(cityName);
        System.out.println(newCityName);
        boolean check = cityName.equals(newCityName);
        Assert.assertFalse(check);
    }

    @Test(priority = 8)
    public void navigateToTheHomePageByClickingExpediaLogo(){
        HamzaUtil();
        //I want to be able to go to home page by clicking the Expedia icon and discart everything.

        String title = driver.getTitle();
        sp.expediaButton().click();

        String title2 = driver.getTitle();
        System.out.println(title);
        System.out.println(title2);
        boolean check2 = title.equals(title2);
        Assert.assertFalse(check2);

    }
    @Test(priority = 9)
    public void lookUpMySearchHistory(){
        HamzaUtil();
        // I want to be able to see the history of my searches.

        sp.setNewDestination().clear();
        sp.setNewDestination().sendKeys("  New");
        sp.setNewDestination().sendKeys(Keys.DOWN, Keys.DOWN, Keys.ENTER);
        sp.submit();

        sp.expediaButton().click();

        eh.searchHistory().click();

        eh.recentSearches().click();

        List<WebElement> listOfSearches = eh.getListOfPackages();

        boolean check = listOfSearches.size()>0;
        Assert.assertTrue(check);
    }
    @Test(priority = 10)
    public static void BundleAndSaveEmre() throws InterruptedException {
        /*EMRE*/
        /*I want to bundle flight and hotel and get savings*/

        WebElement bundleAndSave = driver.findElement(By.id("primary-header-package"));
        bundleAndSave.click();
        Thread.sleep(5000);
        WebElement origin = driver.findElement(By.xpath("//input[@id='package-origin-plp-fh']"));
        origin.click();
        origin.clear();
        origin.sendKeys("chi");
        Thread.sleep(3000);
        origin.sendKeys(Keys.DOWN);
        origin.sendKeys(Keys.ENTER);

        WebElement destination = driver.findElement(By.cssSelector("#package-destination-plp-fh"));
        destination.click();
        destination.sendKeys("new");
        Thread.sleep(3000);
        destination.sendKeys(Keys.DOWN);
        destination.sendKeys(Keys.ENTER);

        WebElement departing = driver.findElement(By.id("package-departing-plp-fh"));
        departing.click();
        WebElement jul2019ForDeparting = driver.findElement(By.xpath("//caption[contains(text(),'Aug 2019')]"));
        WebElement departingDay = driver.findElement(By.xpath("(//button[@data-day='29'])[2]"));

        if(jul2019ForDeparting.getText().contains("Aug 2019")){
            departingDay.click();
            /*selecting August 29*/
        }

        Thread.sleep(3000);
        WebElement returning = driver.findElement(By.cssSelector("#package-returning-plp-fh"));
        returning.click();
        WebElement jul2019ForReturning = driver.findElement(By.xpath("//caption[contains(text(),'Aug 2019')]"));
        WebElement returningDay = driver.findElement(By.xpath("(//button[@data-day='30'])[1]"));
        if(jul2019ForReturning.getText().contains("Aug 2019")) {
            returningDay.click();
            /*selecting August 30*/
        }

        WebElement search = driver.findElement(By.id("search-button-plp-fh"));
        search.click();

    }

    @Test(priority = 11)
    public static void RoundTripEmre() throws InterruptedException {
        /*EMRE*/
        /*I want to be able to shedule a roundtrip flight.*/

        WebElement flight = driver.findElement(By.cssSelector("#tab-flight-tab-hp"));
        flight.click();

        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-hp-flight']"));
        flyingFrom.click();
        flyingFrom.clear();
        flyingFrom.sendKeys("chi");
        Thread.sleep(3000);
        flyingFrom.sendKeys(Keys.DOWN);
        flyingFrom.sendKeys(Keys.ENTER);


        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-hp-flight']"));
        flyingTo.click();
        flyingTo.clear();
        flyingTo.sendKeys("turkey");
        Thread.sleep(3000);
        flyingTo.sendKeys(Keys.DOWN);
        flyingTo.sendKeys(Keys.ENTER);

        WebElement departing = driver.findElement(By.cssSelector("#flight-departing-hp-flight"));
        departing.click();

        WebElement arrowOnCalendarForDeparting = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForDeparting.click();
        /*to see the next month*/

        WebElement departingDay = driver.findElement(By.xpath("(//button[@data-month='8'])[5]"));
        departingDay.click();
        /*selecting Sep 5*/

        WebElement returning = driver.findElement(By.id("flight-returning-hp-flight"));
        returning.click();

        WebElement arrowOnCalendarForReturning = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForReturning.click();

        WebElement returningDay = driver.findElement(By.xpath("(//button[@data-month='8'])[25]"));
        returningDay.click();
        /*selecting Sep 29*/

        WebElement search = driver.findElement(By.xpath
                ("(//button[@class='btn-primary btn-action gcw-submit'])[1]"));
        search.click();

        Thread.sleep(10000);
        WebElement selectFly = driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        selectFly.click();

        Thread.sleep(3000);
        WebElement selectThisFare = driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        try {
            selectThisFare.click();
        }catch (Exception e){

        }

        Thread.sleep(3000);
        WebElement selectSecondFly =driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        try {
            selectSecondFly.click();
        }catch (Exception e){

        }

        Thread.sleep(3000);
        WebElement noThanks = driver.findElement(By.xpath("//a[@id='forcedChoiceNoThanks']"));

        WebElement selectThisFare2 = driver.findElement(By.xpath("(//button[@class='btn-secondary btn-action t-select-btn'])[1]"));
        if(!noThanks.isDisplayed()) {
            selectThisFare2.click();
        }


        Thread.sleep(7000);


        if(noThanks.isDisplayed()) {
            noThanks.click();
        }

    }

    @Test(priority = 12)
    public static void BestPricesEmre() throws InterruptedException {
        /*EMRE*/
        /*I want to get the best prices first when searching for flights.*/

        WebElement flight = driver.findElement(By.cssSelector("#tab-flight-tab-hp"));
        flight.click();

        WebElement flyingFrom = driver.findElement(By.xpath("//input[@id='flight-origin-hp-flight']"));
        flyingFrom.click();
        flyingFrom.clear();
        flyingFrom.sendKeys("chi");
        Thread.sleep(3000);
        flyingFrom.sendKeys(Keys.DOWN);
        flyingFrom.sendKeys(Keys.ENTER);

        WebElement flyingTo = driver.findElement(By.xpath("//input[@id='flight-destination-hp-flight']"));
        flyingTo.click();
        flyingTo.clear();
        flyingTo.sendKeys("turkey");
        Thread.sleep(3000);
        flyingTo.sendKeys(Keys.DOWN);
        flyingTo.sendKeys(Keys.ENTER);

        WebElement departing = driver.findElement(By.cssSelector("#flight-departing-hp-flight"));
        departing.click();

        WebElement arrowOnCalendarForDeparting = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForDeparting.click();
        /*to see the next month*/

        WebElement departingDay = driver.findElement(By.xpath("(//button[@data-month='8'])[5]"));
        departingDay.click();
        /*selecting Sep 5*/

        WebElement returning = driver.findElement(By.id("flight-returning-hp-flight"));
        returning.click();

        WebElement arrowOnCalendarForReturning = driver.findElement(By.xpath
                ("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        arrowOnCalendarForReturning.click();

        WebElement returningDay = driver.findElement(By.xpath("(//button[@data-month='8'])[25]"));
        returningDay.click();
        /*selecting Sep 25*/

        WebElement search = driver.findElement(By.xpath
                ("(//button[@class='btn-primary btn-action gcw-submit'])[1]"));
        search.click();

        List<WebElement> listOfPrices = driver.findElements(By.id("listing-price-dollars"));
        listOfPrices.size();

        for(int i = 0; i < listOfPrices.size()-1; i++){
            if(Integer.valueOf(listOfPrices.get(i).getText().replace("$","").replace(",",""))
                    > Integer.valueOf(listOfPrices.get(i+1).getText().replace("$","").replace(",",""))){
                System.out.println("Best prices verification FAILED");
            }
        }
        System.out.println("Best prices verification PASSED");

    }

    @Test(priority = 13)
    public  void thingsToDo1() throws InterruptedException{

        WebElement thingsToDo = driver.findElement(By.cssSelector("#tab-activity-tab-hp"));
        thingsToDo.click();
        WebElement destination = driver.findElement(By.cssSelector("#activity-destination-hp-activity"));
        Thread.sleep(3000);
        destination.sendKeys("Wyomi");
        Thread.sleep(3000);
        Thread.sleep(3000);
        for(int i = 0; i < 5; i++) {
            destination.sendKeys(Keys.DOWN);
        }
        destination.sendKeys(Keys.ENTER);
        WebElement dateBox = driver.findElement(By.cssSelector("#activity-start-hp-activity"));
        dateBox.click();
        Thread.sleep(3000);
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        nextButton.click();
        Thread.sleep(3000);
        WebElement dateFrom= driver.findElement(By.cssSelector("[data-day='25']"));
        dateFrom.click();
        Thread.sleep(3000);
        WebElement dateBoxTo = driver.findElement(By.cssSelector("#activity-end-hp-activity"));
        dateBoxTo.click();
        WebElement dateTo = driver.findElement(By.cssSelector("[data-day='30']"));
        dateTo.click();
        WebElement searchButton = driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[4]"));
        searchButton.click();
        Thread.sleep(7000);
    }
    @Test(priority = 14)
    public  void pressOnTickets2() throws InterruptedException{
        WebElement thingsToDo = driver.findElement(By.cssSelector("#tab-activity-tab-hp"));
        thingsToDo.click();
        WebElement destination = driver.findElement(By.cssSelector("#activity-destination-hp-activity"));
        Thread.sleep(3000);
        destination.sendKeys("Wyomi");
        Thread.sleep(3000);

        Thread.sleep(3000);
        for(int i = 0; i < 5; i++) {
            destination.sendKeys(Keys.DOWN);
        }
        destination.sendKeys(Keys.ENTER);
        WebElement dateBox = driver.findElement(By.cssSelector("#activity-start-hp-activity"));
        dateBox.click();
        Thread.sleep(3000);
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        nextButton.click();
        Thread.sleep(3000);
        WebElement dateFrom= driver.findElement(By.cssSelector("[data-day='25']"));
        dateFrom.click();
        Thread.sleep(3000);
        WebElement dateBoxTo = driver.findElement(By.cssSelector("#activity-end-hp-activity"));
        dateBoxTo.click();
        WebElement dateTo = driver.findElement(By.cssSelector("[data-day='30']"));
        dateTo.click();
        WebElement searchButton = driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[4]"));
        searchButton.click();
        Thread.sleep(7000);
        WebElement ticketsAtractions = driver.findElement(By.cssSelector("#categoryFilter6"));
        Actions down = new Actions(driver);
        down.moveToElement(ticketsAtractions);
        ticketsAtractions.click();
        Thread.sleep(10000);

    }@Test (priority = 15)
    public  void pressOnInterests() throws InterruptedException{
        WebElement thingsToDo = driver.findElement(By.cssSelector("#tab-activity-tab-hp"));
        thingsToDo.click();
        WebElement destination = driver.findElement(By.cssSelector("#activity-destination-hp-activity"));
        Thread.sleep(3000);
        destination.sendKeys("Wyomi");
        Thread.sleep(3000);

        for(int i = 0; i < 5; i++) {
            destination.sendKeys(Keys.DOWN);
        }
        destination.sendKeys(Keys.ENTER);
        WebElement dateBox = driver.findElement(By.cssSelector("#activity-start-hp-activity"));
        dateBox.click();
        Thread.sleep(3000);
        WebElement nextButton = driver.findElement(By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"));
        nextButton.click();
        Thread.sleep(3000);
        WebElement dateFrom= driver.findElement(By.cssSelector("[data-day='25']"));
        dateFrom.click();
        Thread.sleep(3000);
        WebElement dateBoxTo = driver.findElement(By.cssSelector("#activity-end-hp-activity"));
        dateBoxTo.click();
        WebElement dateTo = driver.findElement(By.cssSelector("[data-day='30']"));
        dateTo.click();
        WebElement searchButton = driver.findElement(By.xpath("(//button[@class='btn-primary btn-action gcw-submit'])[4]"));
        searchButton.click();
        Thread.sleep(7000);

        WebElement interestsAtractions = driver.findElement(By.cssSelector("#categoryFilter7"));
        Actions down = new Actions(driver);
        down.moveToElement(interestsAtractions);
        interestsAtractions.click();
        Thread.sleep(7000);
    }

}
