package com.taybooking.property.controller;

import com.taybooking.property.dto.PropertyDTO;
import com.taybooking.property.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/properties")
@Tag(name = "Property Management", description = "APIs for managing properties")
public class PropertyController {

    @Autowired
    private PropertyService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all properties")
    public Set<PropertyDTO> getAll() {
        return service.getAll();
    }

    @PostMapping
    @Operation(summary = "Create a new property")
    @ResponseStatus(HttpStatus.CREATED)
    public UUID create(@RequestBody PropertyDTO request) {
        return service.create(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a property by ID")
    @ResponseStatus(HttpStatus.OK)
    public PropertyDTO getById(@PathVariable UUID id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a property")
    @ResponseStatus(HttpStatus.OK)
    public PropertyDTO update(@PathVariable UUID id, @RequestBody PropertyDTO updatedRequest) {
        return service.update(id, updatedRequest);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a property")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        service.delete(id);
    }
}