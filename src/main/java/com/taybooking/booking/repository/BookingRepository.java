package com.taybooking.booking.repository;

import com.taybooking.booking.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface BookingRepository extends JpaRepository<BookingEntity, UUID> {
    @Query("SELECT b FROM booking b WHERE b.startDate <= :endDate AND b.endDate >= :startDate AND status = 'ACTIVE' AND property.id = :propertyId")
    List<BookingEntity> findBookingsBetweenDatesForProperty(
            @Param("startDate") Instant startDate,
            @Param("endDate") Instant endDate,
            @Param("propertyId") UUID propertyId);
}