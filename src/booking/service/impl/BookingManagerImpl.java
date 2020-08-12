/**
 * 
 */
package booking.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import booking.exception.BookingException;
import booking.repository.BookingInfoRepository;
import booking.repository.impl.BookingInfoRepositoryImpl;
import booking.service.BookingManager;
import booking.vo.BookingInfo;

/**
 * Implementation service to manage the hotel bookings
 * @author mayankjalotra
 */
public class BookingManagerImpl implements BookingManager{
	
	private BookingInfoRepository repository;
	
	public BookingManagerImpl(){ 
		repository = new BookingInfoRepositoryImpl();
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 **/
	@Override
	public boolean isRoomAvailable(int room, Date date) {
		this.isRoomAvailable(room);
		//for a valid room, check availability
		Map<BookingInfo, Integer> bookings = repository.findAllBookedRooms();
		return !(bookings.containsKey(new BookingInfo.BookingInfoBuilder(room, date).build()));
	}
	
	
	/**
	 * {@inheritDoc}
	 * 
	 **/
	@Override
	public void addBooking(String guest, int room, Date date) {
		this.isRoomAvailable(room, date, String.format("Room number %d is already booked, please try with some other room", room));
		/*
		 * since i am using concurrentHashMap to store the booking details, i need not
		 * to have a synchronized block, if it was not the case, I
		 * would have created a synchronized block with double check of
		 * isRoomAvailable() and in synchronized block would have added Booking
		 */
		Map<BookingInfo, Integer> bookings = repository.findAllBookedRooms();
		Integer alreadyBookedRoom = bookings.putIfAbsent(new BookingInfo.BookingInfoBuilder(room, date).surName(guest).build(),room);
		if(alreadyBookedRoom!=null) {
			throw new BookingException(String.format("Room number %d got booked, please try with some other room", alreadyBookedRoom));
		}
	}
	
	/**
	 * {@inheritDoc}
	 * 
	 **/
	@Override
	public List<String> getAvailableRooms(Date date) {
		Map<BookingInfo, Integer> bookings = repository.findAllBookedRooms();
		String bookingDateStr = getBookingDateStr(date);
		//fetch list of booked rooms on the given date
		List<Integer> bookedRooms = bookings.entrySet().stream().filter(e-> bookingDateStr.equalsIgnoreCase(e.getKey().getBookingDateStr()))
				.map(entry-> entry.getValue())
					.collect(Collectors.toList());
		//remove booked rooms from the list of available rooms to get available rooms
		List<Integer> roomsInHotel = new ArrayList<>(repository.getHotelRoomsList());
		roomsInHotel.removeAll(bookedRooms);
		// since it is expected to return a String type list, converting rooms of Integer type to String
		if(roomsInHotel!=null && !roomsInHotel.isEmpty()) {
			return roomsInHotel.stream().map(String::valueOf).collect(Collectors.toList());
		}
		return Collections.emptyList();
	}
	
	/**
	 * This method validates if the room provided exist in hotel or not
	 * @param room
	 */
	private void isRoomAvailable(int room) {
		List<Integer> rooms = repository.getHotelRoomsList();
		//throw exception a invalid room is Added.
		if(!rooms.contains(room)) {
			throw new BookingException(String.format("Room %d does not exist in hotel, please refer BookingInfoRepository for hotel rooms list", room));
		}
	}
	
	/**
	 * This method is used when adding bookings to verify if the provided room 
	 * is available or not. The method accepts an error msg string in case 
	 * room is not available, the passed {@linkplain errorMsg} could be thrown.
	 * 
	 * @param room
	 * @param date
	 * @param errorMsg
	 */
	private void isRoomAvailable(int room, Date date, String errorMsg) {
		if(! this.isRoomAvailable(room, date)) {
			//System.out.println(repository.findAllBookedRooms());
			throw new BookingException(String.format("Room number %d is already booked, please try with some other room", room));
		}
	}
	
	/**
	 * This method converts the booking date to yyyy-MM-dd format, so
	 * it can be used for comparison without considering the time part.
	 * @param bookingDate
	 * @return
	 */
	private String getBookingDateStr(final Date bookingDate) {
		if(bookingDate == null) {
			throw new BookingException("Invalid date entered, please provide a valid date");
		}
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(bookingDate);
	}
	
}




