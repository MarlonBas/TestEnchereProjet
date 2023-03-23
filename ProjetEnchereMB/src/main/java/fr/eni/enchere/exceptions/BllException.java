package fr.eni.enchere.exceptions;

public class BllException extends Exception{
	
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
			StringBuffer sb = new StringBuffer("Couche Bll - ");
			sb.append(super.getMessage());
			
			return sb.toString() ;
		}

		
		
	}


