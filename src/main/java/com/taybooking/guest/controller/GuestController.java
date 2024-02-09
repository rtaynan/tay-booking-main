package com.taybooking.guest.controller;

import com.taybooking.guest.dto.GuestDTO;
import com.taybooking.guest.service.GuestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/guests")
@Tag(name = "Guest Management", description = "APIs for managing guests")
public class GuestController {

    @Autowired
    private GuestService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all guests")
    public Set<GuestDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a new guest")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody GuestDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a guest by ID")
    @ResponseStatus(HttpStatus.OK)
    public GuestDTO getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a guest")
    @ResponseStatus(HttpStatus.OK)
    public GuestDTO update(@PathVariable UUID id, @RequestBody GuestDTO updatedRequest) {
        return service.update(id, updatedRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a guest")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}