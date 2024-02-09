package com.taybooking.booking.dto;

import com.taybooking.booking.entity.Status;

import java.time.Instant;
import java.util.UUID;

public class BookingRespDTO {

    private UUID id;
    private UUID propertyId;
    private String propertyName;
    private String propertyDescription;
    private UUID guestId;
    private String guestName;
    private Instant startDate;
    private Instant endDate;
    private Status status;
    private Instant createdAt;

    public BookingRespDTO() {
    }

    public BookingRespDTO(UUID id, UUID propertyId, String propertyName, String propertyDescription, UUID guestId,
                          String guestName, Instant startDate, Instant endDate, Status status, Instant createdAt) {
        this.id = id;
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.propertyDescription = propertyDescription;
        this.guestId = guestId;
        this.guestName = guestName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(UUID propertyId) {
        this.propertyId = propertyId;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public void setPropertyName(String propertyName) {
        this.propertyName = propertyName;
    }

    public String getPropertyDescription() {
        return propertyDescription;
    }

    public void setPropertyDescription(String propertyDescription) {
        this.propertyDescription = propertyDescription;
    }

    public UUID getGuestId() {
        return guestId;
    }

    public void setGuestId(UUID guestId) {
        this.guestId = guestId;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
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

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
