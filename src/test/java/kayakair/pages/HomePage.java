package kayakair.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class HomePage extends BasePage{
	
  private String url;
  
  public HomePage(WebDriver driver, String url){
	  
	 this.driver=driver;
	 PageFactory.initElements(driver, this);
	 this.url=url;
  }

  public void open(){
	  driver.get(url);
  }

  
}
