package com.taybooking.guest.repository;

import com.taybooking.guest.entity.GuestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GuestRepository extends JpaRepository<GuestEntity, UUID> {
}