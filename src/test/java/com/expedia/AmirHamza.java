package com.expedia;

import io.github.bonigarcia.wdm.WebDriverManager;
import localrepository.ExpediaHomePage2;
import localrepository.SearchPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AmirHamza {

    static WebDriver driver;
    static WebDriverWait d;
    static ExpediaHomePage2 eh;
    static SearchPage sp;


    @BeforeMethod
    public void putSearchInfo(){
    //Setting up a roundtrip with 5 passengers

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        eh = new ExpediaHomePage2(driver);
        sp = new SearchPage(driver);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().fullscreen();
        d = new WebDriverWait(driver, 15);
        driver.get("http://expedia.com");

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

    @Test
    public void editMySearchResults(){
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

    @Test
    public void navigateToTheHomePageByClickingExpediaLogo(){
        //I want to be able to go to home page by clicking the Expedia icon and discart everything.

        String title = driver.getTitle();
        sp.expediaButton().click();

        String title2 = driver.getTitle();
        System.out.println(title);
        System.out.println(title2);
        boolean check2 = title.equals(title2);
        Assert.assertFalse(check2);

    }
    @Test
    public void lookUpMySearchHistory(){
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
    @AfterMethod
    public void close(){
        driver.close();
    }
}
