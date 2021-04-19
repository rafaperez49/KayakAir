package kayakair.stepdefinitions;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import kayakair.assertions.HomePageAssertion;
import kayakair.assertions.SearchPageAssertion;
import kayakair.pages.HomePage;
import kayakair.pages.SearchPage;
import static kayakair.utils.TestConstantStrings.HOME_PAGE_TITLE;


public class KayakStepDefinitions{

  
	private WebDriver driver;
	private HomePage homePage;
	private SearchPageAssertion searchPageAssertion;
	private HomePageAssertion homePageAssertion;
	private SearchPage searchPage;
	private String departureDate;
	private String returnDate;
	private String newDepartureDate;
	private String newReturnDate;
	private String originCity;
	private String destinationCity;
	
	@Before
	public void setup()
	{
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);        
        driver.manage().window().maximize();
		homePage= new HomePage(driver, "http://kayak.com.co");
		searchPage = new SearchPage(driver);
		searchPageAssertion = new SearchPageAssertion();
		homePageAssertion = new HomePageAssertion();
		
		departureDate="10/05/2021";
		returnDate="10/06/2021";
		newDepartureDate="10/07/2021";
		newReturnDate="10/08/2021";
		originCity="Medellin";
		destinationCity="San Francisco";
		
	}

  @Test
  public void buyAFlightInKayakWebPage() {	  
	  
	  /*Scenario: As a traveler an user wants to buy a flight for his next trip*/
	   
	  /* Given the user go to www.kayak.com web page */
	  
	  homePage.open();
	  homePageAssertion.validateTheCurrentPage(HOME_PAGE_TITLE.getText(), homePage); 
	  
	  
	  /* When the user enter all the information required*/
	  
	  searchPage.searchOriginCity(originCity);
	  searchPage.searchDestinyCity(destinationCity);
	  searchPage.searchDate().from(departureDate).to(returnDate);	
	  
	  searchPage.closeThePopup();
	  
	  /*When the user wants to change the information about the dates*/
	  /*Check if the initial date were OK*/
	  
	  searchPageAssertion.validateTheDate(departureDate, searchPage.getDepartureDate());
	  searchPageAssertion.validateTheDate(returnDate, searchPage.getRetunDate());
	  
	  searchPage.searchDate().from(newDepartureDate).toNew(newReturnDate);
	  
	  /*Check that the new dates were enter correctly*/
	  searchPageAssertion.validateTheDate(newDepartureDate, searchPage.getDepartureDate());
	  searchPageAssertion.validateTheDate(newReturnDate, searchPage.getRetunDate());
	  
	  /*Then the user should see all the prices to select the lowest one*/
	  searchPage.selectTheLowestCost();
	  searchPageAssertion.validateNewWindow(homePage, searchPage);
	  
  } 
  
  @After
  public void closeTest(){
	  driver.close();
	  driver.quit();
  }
}
