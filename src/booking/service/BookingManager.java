package booking.service;

import java.util.Date;
import java.util.List;

public interface BookingManager {
	
	/**
	* Return true if there is no booking for the given room on the date,
	* otherwise false
	*/
	public boolean isRoomAvailable(int room, Date date);
	
	/**
	* Add a booking for the given guest in the given room on the given
	* date. If the room is not available, throw a suitable Exception.
	*/
	public void addBooking(String guest, int room, Date date);
	
	/**
	* Return a list of all the available room numbers for the given date
	*/
	public List<String> getAvailableRooms(Date date);

}
