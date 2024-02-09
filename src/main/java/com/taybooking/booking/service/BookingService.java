package com.taybooking.booking.service;

import com.taybooking.booking.dto.BookingReqDTO;
import com.taybooking.booking.dto.BookingRespDTO;
import com.taybooking.booking.entity.BookingEntity;
import com.taybooking.booking.entity.Status;
import com.taybooking.booking.repository.BookingRepository;
import com.taybooking.guest.repository.GuestRepository;
import com.taybooking.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class BookingService {

    public static final String BOOKING_NOT_FOUND_WITH_ID = "Booking not found with id ";
    @Autowired
    private BookingRepository repository;
    @Autowired
    private PropertyRepository propertyRepository;
    @Autowired
    private GuestRepository guestRepository;

    public Set<BookingRespDTO> getAll() {
        Set<BookingRespDTO> resp = new HashSet<>();
        repository.findAll().forEach(booking -> resp.add(
                parseBooking(booking)
        ));
        return resp;
    }

    public BookingRespDTO create(@RequestBody BookingReqDTO request) {
        var prop = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid property_id " + request.getPropertyId()));
        var guest = guestRepository.findById(request.getGuestId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid guest_id " + request.getGuestId()));
        validateDates(request);
        var db = repository.save(new BookingEntity(
                Status.ACTIVE,
                prop,
                guest,
                request.getStartDate(),
                request.getEndDate(),
                Instant.now(),
                null
        ));
        return parseBooking(db);
    }

    public BookingRespDTO getById(@PathVariable UUID id) {
        var db = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(BOOKING_NOT_FOUND_WITH_ID + id));
        return parseBooking(db);
    }

    public BookingRespDTO cancelBooking(@PathVariable UUID id) {
       var db = repository.findById(id)
                .map(entity -> {
                    entity.setStatus(Status.CANCELED);
                    return repository.save(entity);
                })
                .orElseThrow(() -> new IllegalArgumentException(BOOKING_NOT_FOUND_WITH_ID + id));
        return parseBooking(db);
    }

    public BookingRespDTO update(@PathVariable UUID id, @RequestBody BookingReqDTO updatedRequest) {

        validateDates(updatedRequest);
        var db = repository.findById(id)
                .map(booking -> {
                    booking.setStartDate(updatedRequest.getStartDate());
                    booking.setEndDate(updatedRequest.getEndDate());
                    booking.setStatus(updatedRequest.getStatus());
                    return repository.save(booking);
                })
                .orElseThrow(() -> new IllegalArgumentException(BOOKING_NOT_FOUND_WITH_ID + id));
        return parseBooking(db);
    }

    private void validateDates(BookingReqDTO updatedRequest) {
        if (updatedRequest.getStartDate().isAfter(updatedRequest.getEndDate()) ||
            !isValidRangeOfDates(updatedRequest)) {
           throw new IllegalArgumentException("Invalid date range");
        }
    }

    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }


    public boolean isValidRangeOfDates(BookingReqDTO bookingDTO) {
        return repository.findBookingsBetweenDatesForProperty(bookingDTO.getStartDate(), bookingDTO.getEndDate(), bookingDTO.getPropertyId()).isEmpty();
    }

    private static BookingRespDTO parseBooking(BookingEntity db) {
        return new BookingRespDTO(
                db.getId(),
                db.getProperty().getId(),
                db.getProperty().getName(),
                db.getProperty().getDescription(),
                db.getGuest().getId(),
                db.getGuest().getName(),
                db.getStartDate(),
                db.getEndDate(),
                db.getStatus(),
                db.getCreatedAt()
        );
    }
}
