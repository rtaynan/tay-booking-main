package com.taybooking.booking.dto;

import com.taybooking.booking.entity.Status;

import java.time.Instant;
import java.util.UUID;

public class BookingReqDTO {

    private UUID propertyId;
    private UUID guestId;
    private Instant startDate;
    private Instant endDate;
    private Status status;

    public BookingReqDTO() {
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
