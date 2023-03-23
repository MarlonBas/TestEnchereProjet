package fr.eni.enchere.exceptions;

public class BllException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public BllException() {
		super();
	}
	
	public BllException(String message) {
		super(message);
	}
	
	public String getMessage() {
		return super.getMessage();
		
	}
}
