package booking.repository.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import booking.repository.BookingInfoRepository;
import booking.vo.BookingInfo;

/**
 * Implementation repository with static 
 * initial bookings and hotel rooms list
 * to serve the purpose of booking manager service.
 * 
 * @author mayankjalotra
 */
public class BookingInfoRepositoryImpl implements BookingInfoRepository{
	
	static Map<BookingInfo, Integer> bookings = new ConcurrentHashMap<>();
	static List<Integer> roomsList = Arrays.asList(102,101,103,201,203,305);
	
	static {
		BookingInfo booking1 = new BookingInfo.BookingInfoBuilder(101, new Date()).surName("Kumar").build();
//		BookingInfo booking2 = new BookingInfo.BookingInfoBuilder(102, new Date()).surName("Jalotra").build();
		bookings.put(booking1, 101);
//		bookings.put(booking2, 102);
	}

	/**
	 * {@inheritDoc}
	 * <p>In case you remove initialized data
	 * need to addd Objects::nonNull when streaming in {@link booking.service.impl.BookingManagerImpl.getAvailableRooms()} </p>.
	 * 
	 */
	public Map<BookingInfo, Integer> findAllBookedRooms(){
		return bookings;
	}
	
	/**
	 * {@inheritDoc}.
	 */
	public List<Integer> getHotelRoomsList(){
		return roomsList;
	}

}
