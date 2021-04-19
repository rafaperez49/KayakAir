package kayakair.pages;

import org.openqa.selenium.WebDriver;

public abstract class BasePage {
	
	protected WebDriver driver;	
	
	public WebDriver getDriver(){
		return driver;
	}
	
}
