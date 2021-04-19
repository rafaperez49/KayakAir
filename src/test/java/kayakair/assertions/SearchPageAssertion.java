package kayakair.assertions;
import static kayakair.utils.TestConstantStrings.NOT_EXIST_NEW_WINDOW;
import static kayakair.utils.TestConstantStrings.THE_DATE_DOES_NOT_CHANGE;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

import org.hamcrest.Matchers;

import kayakair.exceptions.KayakExceptions;
import kayakair.pages.HomePage;
import kayakair.pages.SearchPage;
import kayakair.utils.WindowHandle;

public class SearchPageAssertion {
	
	public void validateTheDate(String dateExpected, String currentDate ){
		try {
			assertThat(dateExpected.replaceAll("/0", "/"), containsString(currentDate));
		}catch(AssertionError e){	
			throw new KayakExceptions(THE_DATE_DOES_NOT_CHANGE.getText());
		}
			
	}
	
	public void validateNewWindow(HomePage home, SearchPage search)
	{
		try {
			assertThat(home.getDriver().getCurrentUrl(),  Matchers.not(Matchers.equalTo(WindowHandle.changeWindow(search.getDriver()).getCurrentUrl()))   );
		}catch(AssertionError e) {
			throw new KayakExceptions(NOT_EXIST_NEW_WINDOW.getText());
		}
		}
	
	
	

}
