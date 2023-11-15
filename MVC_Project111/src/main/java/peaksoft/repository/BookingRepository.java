package peaksoft.repository;

import peaksoft.entity.Agency;
import peaksoft.entity.Booking;

import java.util.List;

public interface BookingRepository {
    void saveBooking(Booking booking);
    List<Booking> getAllBooking();
    Agency getBookingById(Long id);
    void updateBookingById(Long id,Booking booking);
    void deletedBookingById(Long id);


}
