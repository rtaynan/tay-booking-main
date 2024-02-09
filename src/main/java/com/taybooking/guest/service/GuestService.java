package com.taybooking.guest.service;

import com.taybooking.guest.dto.GuestDTO;
import com.taybooking.guest.entity.GuestEntity;
import com.taybooking.guest.repository.GuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public class GuestService {

    public static final String GUEST_NOT_FOUND_WITH_ID = "Guest not found with id ";
    @Autowired
    private GuestRepository repository;

    public Set<GuestDTO> getAll() {
        Set<GuestDTO> resp = new HashSet<>();
        repository.findAll().forEach(guest -> resp.add(new GuestDTO(guest.getName())));
        return resp;
    }

    public UUID create(GuestDTO request) {
        return repository.save(new GuestEntity(request.getName())).getId();
    }

    private static GuestDTO parseGuestDTO(GuestEntity db) {
        return new GuestDTO(db.getName());
    }

    public GuestDTO getById(UUID id) {
        var db = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(GUEST_NOT_FOUND_WITH_ID + id));
        return parseGuestDTO(db);
    }

    public GuestDTO update(UUID id, GuestDTO updatedRequest) {
        var db = repository.findById(id)
                .map(entity -> {
                    entity.setName(updatedRequest.getName());
                    return repository.save(entity);
                })
                .orElseThrow(() -> new IllegalArgumentException(GUEST_NOT_FOUND_WITH_ID + id));
        return parseGuestDTO(db);
    }

    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
