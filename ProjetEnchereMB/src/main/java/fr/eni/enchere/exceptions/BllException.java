package fr.eni.enchere.exceptions;

public class BllException extends Exception{
	private static final long serialVersionUID = 1L;

		public BllException() {
			super();
		}
		
		public BllException(String message) {
			super(message);
		}
		
		public BllException(String message, Throwable exception) {
			super(message, exception);
		}

		//Méthodes
		@Override
		public String getMessage() {
			StringBuffer sb = new StringBuffer("");
			sb.append(super.getMessage());
			
			return sb.toString() ;
		}

		
		
	}


