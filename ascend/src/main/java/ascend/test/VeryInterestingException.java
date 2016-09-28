package ascend.test;

public class VeryInterestingException extends Exception {
	private static final long serialVersionUID = 1L;
	private String message;
	
	public VeryInterestingException(String message){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
