package booking.exception;

/**
 * Custom business exception for hotel room booking service.
 * @author mayankjalotra
 */
public class BookingException extends RuntimeException {
	
	private static final long serialVersionUID = -7634494059541407527L;

	/**
	 * Constructs a new runtime exception with the specified message.
	 * @param message the detail message.
	 */
	public BookingException(String message) {
        super(message);
    }
}
