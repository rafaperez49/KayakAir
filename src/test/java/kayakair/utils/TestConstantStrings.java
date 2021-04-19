package kayakair.utils;

public enum TestConstantStrings {
	
	VOID_STRING(""),
	ORIGIN("origin"),
	DESTINATION("destination"),
	CLICK_ELEMENT ("arguments[0].click()"),
	HOME_PAGE_TITLE("Busca vuelos, hoteles y autos de alquiler | KAYAK"),
	HOME_PAGE_MESSAGE_ERROR("The web page is not www.Kayak.com"), 
	THE_DATE_DOES_NOT_CHANGE("The date is not the correct one, maybe the date did not change its value"),
	NOT_EXIST_NEW_WINDOW("The new window that was expected to be opened, didn't open.");
	
	private String text;
	private TestConstantStrings(String text) {
		this.text=text;		
	}
	
	public String getText() {
		return text;
	}
	
	
}
