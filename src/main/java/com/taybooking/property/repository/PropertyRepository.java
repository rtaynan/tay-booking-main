package com.taybooking.property.repository;

import com.taybooking.property.entity.PropertyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PropertyRepository extends JpaRepository<PropertyEntity, UUID> {
}