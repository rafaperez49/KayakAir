package kayakair.assertions;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.hamcrest.MatcherAssert;

import kayakair.exceptions.KayakExceptions;
import kayakair.pages.HomePage;
import static kayakair.utils.TestConstantStrings.HOME_PAGE_MESSAGE_ERROR;



public class HomePageAssertion {

	public void validateTheCurrentPage(String title, HomePage homePage) {
		try {		
		MatcherAssert.assertThat(title, is(equalTo(homePage.getDriver().getTitle())));
		}catch(AssertionError e) {
			throw new KayakExceptions(HOME_PAGE_MESSAGE_ERROR.getText());
		}
	}

}
