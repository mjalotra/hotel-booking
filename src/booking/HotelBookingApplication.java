package booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import booking.service.BookingManager;
import booking.service.impl.BookingManagerImpl;

/**
 * Application main class to create Hotel Bookings.
 * @author mayankjalotra
 */
public class HotelBookingApplication {

	/**
	 * Entry method to initiate your bookings and queuries.
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		
		//Part-1, check room availability and add booking
		BookingManager  bm = new BookingManagerImpl();
		Date today = new SimpleDateFormat("yyyy-MM-dd").parse("2012-03-28");
		System.out.println(bm.isRoomAvailable(101, today));
		
		bm.addBooking("Smith", 101, today);
		System.out.println (bm.isRoomAvailable(101, today)); 
//		bm.addBooking("Jones", 101, today);
		
		// Part-2, fetching the list of available rooms
		System.out.println(bm.getAvailableRooms(today));

	}

}
