package com.taybooking.booking.controller;

import com.taybooking.booking.dto.BookingReqDTO;
import com.taybooking.booking.dto.BookingRespDTO;
import com.taybooking.booking.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/bookings")
@Tag(name = "Booking Management", description = "APIs for managing bookings")
public class BookingController {

    @Autowired
    private BookingService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all bookings")
    public Set<BookingRespDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a new booking")
    @ResponseStatus(HttpStatus.CREATED)
    public BookingRespDTO create(@RequestBody BookingReqDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a booking by ID")
    @ResponseStatus(HttpStatus.OK)
    public BookingRespDTO getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PatchMapping("/{id}/cancel")
    @Operation(summary = "Cancel a booking")
    @ResponseStatus(HttpStatus.OK)
    public BookingRespDTO cancelBooking(@PathVariable UUID id) {
        return service.cancelBooking(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a booking")
    @ResponseStatus(HttpStatus.OK)
    public BookingRespDTO update(@PathVariable UUID id, @RequestBody BookingReqDTO updatedRequest) {
        return service.update(id, updatedRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a booking")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }


}