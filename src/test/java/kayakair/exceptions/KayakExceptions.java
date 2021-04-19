package kayakair.exceptions;

public class KayakExceptions extends AssertionError{
	
	private static final long serialVersionUID = 1L;

	public KayakExceptions(){
		super();
	}
	
	public KayakExceptions (String message){
		super(message);
	}

}
