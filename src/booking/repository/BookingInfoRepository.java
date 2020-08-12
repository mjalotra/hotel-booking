package booking.repository;

import java.util.List;
import java.util.Map;

import booking.vo.BookingInfo;

/**
 * repository with static data to be used for booking details
 * @author mayankjalotra
 *
 */
public interface BookingInfoRepository {
	
	/**
	 * Method to find all the booked Rooms.
	 * @return Map<{@link BookingInfo}, {@link Integer}RoomNumber>
	 */
	public Map<BookingInfo, Integer> findAllBookedRooms();
	
	/**
	 * Method returning a static immutable list of hotel rooms.
	 * @return
	 */
	public List<Integer> getHotelRoomsList();
}
