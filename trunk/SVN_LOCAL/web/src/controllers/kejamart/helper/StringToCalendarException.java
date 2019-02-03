package kejamart.helper;

public class StringToCalendarException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1240809766331230348L;

	/**
	 * 
	 */
	public StringToCalendarException(){}
	
	/**
	 * 
	 * @param message
	 */
	public StringToCalendarException(String message){
		super(message);
	}
	
	/**
	 * 
	 * @param cause
	 */
	public StringToCalendarException(Throwable  cause){
		super(cause);
	}
	
	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public StringToCalendarException(String message, Throwable  cause){
		super(message,cause);
	}
}
