package kayakair.pages;

import static kayakair.utils.TestConstantNumbers.FIRST;
import static kayakair.utils.TestConstantNumbers.FROM_STARTS_DATE;
import static kayakair.utils.TestConstantNumbers.TIMES_TO_SEARCH;
import static kayakair.utils.TestConstantStrings.CLICK_ELEMENT;
import static kayakair.utils.TestConstantStrings.DESTINATION;
import static kayakair.utils.TestConstantStrings.ORIGIN;
import static kayakair.utils.TestConstantStrings.VOID_STRING;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage{	
	
	private String departDate;	
	private WebDriverWait wait;
	private JavascriptExecutor interaction;	
	private List <WebElement> thePrices;
	
	public static final String ORIGIN_FIELD="//div[contains(@id, 'origin-input-wrapper')]";
	public static final String DESTINY_FIELD="//div[contains(@id, 'destination-input-wrapper')]";
	public static final String DATE_ELEMENT_DEPARTURE = "//div[contains(@id, 'dateRangeInput-display-start-inner')]";
	public static final String DATE_ELEMENT_RETURN = "//div[contains(@id, 'dateRangeInput-display-end-inner')]";
	public static final String DEPARTURE_DATE="//div[contains(@id, 'depart-input')]";
	public static final String RETURN_DATE="//div[contains(@id, 'return-input')]";
	public static final String NEW_DEPARTURE_DATE="//div[substring(@id,string-length(@id)-(string-length('-departDate-input')-1 ))='-departDate-input']";
	public static final String NEW_RETURN_DATE="//div[substring(@id,string-length(@id)-(string-length('-returnDate-input')-1 ))='-returnDate-input']";
	public static final String SEARCH_BUTTON="//*[contains(@id, '-submit')][contains(@title, 'Buscar vuelos')]";
	public static final String NEW_SEARCH_BUTTON="//*[contains(@id, '-submit')][contains(@title, 'Buscar vuelos')]";
	public static final String CLOSE_BUTTON = ".//div[substring(@id,string-length(@id)-(string-length('dialog')-1))='dialog'][contains(@class,'R9-Overlay')][contains(@class,'visible')]//*[substring(@id,string-length(@id)-(string-length('close')-1))='close']";
	public static final String LOAD_MORE_BUTTON = "//a[contains(@id, 'loadMore')]";
	public static final String LOWER_PRICE = "//span[contains(@id, 'price-text')]" ;
	
	
	public SearchPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(this.driver, this);
		wait = new WebDriverWait(driver, 40);
		interaction = (JavascriptExecutor)driver;		
	}
	
	
	public void searchOriginCity(String origin) {		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(ORIGIN_FIELD)));
		driver.findElement(By.xpath(ORIGIN_FIELD)).click();		
		driver.findElements(By.name(ORIGIN.getText())).get(FIRST.getNumber()).sendKeys(origin);
		driver.findElements(By.name(ORIGIN.getText())).get(FIRST.getNumber()).sendKeys(Keys.ENTER);			
	}

	public void searchDestinyCity(String destiny){		
			driver.findElements(By.xpath(DESTINY_FIELD)).get(FIRST.getNumber()).click();		
			driver.findElements(By.name(DESTINATION.getText())).get(FIRST.getNumber()).sendKeys(destiny);
			driver.findElements(By.name(DESTINATION.getText())).get(FIRST.getNumber()).sendKeys(Keys.ENTER);			
		}

	public void to(String returnDate) {				
			driver.findElements(By.xpath(DATE_ELEMENT_DEPARTURE)).get(FIRST.getNumber()).click();
			driver.findElement(By.xpath(DEPARTURE_DATE)).click();
			driver.findElement(By.xpath(DEPARTURE_DATE)).clear();
			driver.findElement(By.xpath(DEPARTURE_DATE)).sendKeys(departDate);
			driver.findElement(By.xpath(DEPARTURE_DATE)).sendKeys(Keys.ENTER);
			
			driver.findElements(By.xpath(DATE_ELEMENT_RETURN)).get(FIRST.getNumber()).click();	
			driver.findElement(By.xpath(RETURN_DATE)).click();
			driver.findElement(By.xpath(RETURN_DATE)).clear();
			driver.findElement(By.xpath(RETURN_DATE)).sendKeys(returnDate);
			driver.findElement(By.xpath(RETURN_DATE)).sendKeys(Keys.ENTER);
			
			driver.findElement(By.xpath(SEARCH_BUTTON)).click();	
	}
	
	public void toNew(String returnDate) {	
		
		interaction.executeScript(CLICK_ELEMENT.getText(), driver.findElements(By.xpath(DATE_ELEMENT_DEPARTURE)).get(FIRST.getNumber()));
		
		driver.findElement(By.xpath(NEW_DEPARTURE_DATE)).click();	
		driver.findElement(By.xpath(NEW_DEPARTURE_DATE)).clear();
		driver.findElement(By.xpath(NEW_DEPARTURE_DATE)).sendKeys(departDate);
		driver.findElement(By.xpath(NEW_DEPARTURE_DATE)).sendKeys(Keys.ENTER);
		
		driver.findElements(By.xpath(DATE_ELEMENT_RETURN)).get(FIRST.getNumber()).click();
		driver.findElement(By.xpath(NEW_RETURN_DATE)).click();
		driver.findElement(By.xpath(NEW_RETURN_DATE)).clear();
		driver.findElement(By.xpath(NEW_RETURN_DATE)).sendKeys(returnDate);
		driver.findElement(By.xpath(NEW_RETURN_DATE)).sendKeys(Keys.ENTER);
		
		driver.findElement(By.xpath(NEW_SEARCH_BUTTON)).click();		
	}
	
	public SearchPage searchDate() {		
		return this;
	}
	
	public SearchPage from(String departDate){
		this.departDate=departDate;
		return this;
	}
	
	public void closeThePopup()	{				
		driver.findElement(By.xpath(CLOSE_BUTTON)).click();		
	}
	
	public void closeThePopup()	{				
		driver.findElement(By.xpath(CLOSE_BUTTON)).click();		
	}
	
	public void lookForMoreResults() {
		for (int i = 0; i < TIMES_TO_SEARCH.getNumber(); i++){
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(LOAD_MORE_BUTTON)));
			interaction.executeScript(CLICK_ELEMENT.getText(),driver.findElement(By.xpath(LOAD_MORE_BUTTON)));
		}		
	}
	
	
	public void selectTheLowestCost(){		
			
		thePrices = driver.findElements(By.xpath(LOWER_PRICE)).stream()
						.sorted(Comparator.comparing(WebElement::getText))
						.filter(voidResults -> !voidResults.getText().equals(VOID_STRING.getText()))
						.collect(Collectors.toList());	
		
		wait.until(ExpectedConditions.elementToBeClickable(thePrices.get(FIRST.getNumber())));
		interaction.executeScript(CLICK_ELEMENT.getText(), thePrices.get(FIRST.getNumber()));		
	}
	
	public String getDepartureDate() {
		return driver.findElement(By.xpath(DATE_ELEMENT_DEPARTURE)).getText().substring(FROM_STARTS_DATE.getNumber(), driver.findElement(By.xpath(DATE_ELEMENT_DEPARTURE)).getText().length());		
	}
	
	public String getRetunDate() {
		return driver.findElement(By.xpath(DATE_ELEMENT_RETURN)).getText().substring(FROM_STARTS_DATE.getNumber(), driver.findElement(By.xpath(DATE_ELEMENT_RETURN)).getText().length());		
	}
	

}
