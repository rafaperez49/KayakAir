package kayakair.utils;

public enum TestConstantNumbers {
	
	FIRST(0),
	TIMES_TO_SEARCH(4), 
	FROM_STARTS_DATE(5);
	
	private int number;
	private TestConstantNumbers(int number){
		this.number=number;
	}
	
	public int getNumber(){
		return number;
	}

}
