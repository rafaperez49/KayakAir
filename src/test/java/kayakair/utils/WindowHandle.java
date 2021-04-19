package kayakair.utils;

import org.openqa.selenium.WebDriver;

public class WindowHandle {
	
	public static WebDriver changeWindow(WebDriver driver)
	{
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
		return driver;		
	}

}
