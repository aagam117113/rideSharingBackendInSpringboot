package com.student.tripmate.controller;

import com.student.tripmate.dto.TripCreatePayload;
import com.student.tripmate.model.Trip;
import com.student.tripmate.service.TripService;
import com.student.tripmate.util.SecurityHelper;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/trips")
public class TripController {
    private final TripService svc;

    public TripController(TripService svc) { this.svc = svc; }

    @PostMapping
    public ResponseEntity<Trip> requestTrip(@Valid @RequestBody TripCreatePayload payload) {
        String username = SecurityHelper.currentUsername();
        Trip created = svc.createTrip(username, payload);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/{tripId}/finish")
    public ResponseEntity<Trip> finishTrip(@PathVariable String tripId) {
        String username = SecurityHelper.currentUsername();
        Trip t = svc.finishTrip(username, tripId);
        return ResponseEntity.ok(t);
    }
}

