package com.taybooking.property.service;

import com.taybooking.property.dto.PropertyDTO;
import com.taybooking.property.entity.PropertyEntity;
import com.taybooking.property.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class PropertyService {
    public static final String PROPERTY_NOT_FOUND_WITH_ID = "Property not found with id ";
    @Autowired
    private PropertyRepository repository;

    public Set<PropertyDTO> getAll() {
        Set<PropertyDTO> resp = new HashSet<>();
        repository.findAll().forEach(property ->
                resp.add(parseToDTO(property)));
        return resp;
    }

    public UUID create(PropertyDTO request) {
        var n = new PropertyEntity();
        n.setName(request.getName());
        n.setDescription(request.getDescription());
        return repository.save(n).getId();

    }

    public PropertyDTO getById(UUID id) {
        var db = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(PROPERTY_NOT_FOUND_WITH_ID + id));
        return parseToDTO(db);
    }

    public PropertyDTO update(UUID id, PropertyDTO updatedRequest) {
        var db = repository.findById(id)
                .map(entity -> {
                    entity.setName(updatedRequest.getName());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new IllegalArgumentException(PROPERTY_NOT_FOUND_WITH_ID + id));
        return parseToDTO(db);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }

    private static PropertyDTO parseToDTO(PropertyEntity db) {
        return new PropertyDTO(db.getName(), db.getDescription());
    }
}
