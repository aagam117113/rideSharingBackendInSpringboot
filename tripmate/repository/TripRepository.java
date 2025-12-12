package com.student.tripmate.repository;

import com.student.tripmate.model.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TripRepository extends MongoRepository<Trip, String> {
    List<Trip> findByStatus(String status);
    List<Trip> findByPassengerId(String passengerId);
}
