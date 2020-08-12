/**
 * 
 */
package booking.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Value Object to store the hotel booking information. 
 * @author mayankjalotra 
 */
public final class BookingInfo {
	
	private final String firstName;
	private final String sirName;
	private final int roomNumber;
	private final Long phoneNumber;
	private final Date bookingDate;
	private final String bookingDateStr;
	
	private BookingInfo(BookingInfoBuilder builder) {
		this.firstName = builder.firstName;
		this.sirName = builder.sirName;
		this.roomNumber = builder.roomNumber;
		this.phoneNumber = builder.phoneNumber;
		this.bookingDate = builder.bookingDate;
		this.bookingDateStr = getBookingDateStr(builder.bookingDate);
	}
	
	/**
	 * Builder class for assignment of various attributes.
	 * @author mayankjalotra
	 *
	 */
	public static class BookingInfoBuilder {
		private String firstName;
		private String sirName;
		private final int roomNumber;
		private Long phoneNumber;
		private final Date bookingDate;
		
		public BookingInfoBuilder(int roomNumber, Date bookingDate) {
			this.roomNumber = roomNumber;
			this.bookingDate = bookingDate;
		}
		
		public BookingInfoBuilder surName(String sirName) {
			this.sirName = sirName;
			return this;
		}
		
		public BookingInfoBuilder firstName(String firstname) {
			this.firstName = firstname;
			return this;
		}

		public BookingInfoBuilder lastName(Long phoneNumber) {
			this.phoneNumber = phoneNumber;
			return this;
		}

		public BookingInfo build() {
			return new BookingInfo(this);
		}
		
	}
	
	private String getBookingDateStr(final Date bookingDate) {
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(bookingDate);
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getSurName() {
		return sirName;
	}
	
	public int getRoomNumber() {
		return roomNumber;
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	public Date getBookingDate() {
		return new Date(bookingDate.getTime());
	}

	public String getBookingDateStr() {
		return bookingDateStr;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingDateStr == null) ? 0 : bookingDateStr.hashCode());
		result = prime * result + roomNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingInfo other = (BookingInfo) obj;
		if (bookingDateStr == null) {
			if (other.bookingDateStr != null)
				return false;
		} else if (!bookingDateStr.equals(other.bookingDateStr))
			return false;
		if (roomNumber != other.roomNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "\n[sirName=" + sirName + ", bookingDate=" + bookingDateStr
				+ "]";
	}
	
		
}
