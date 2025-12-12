package com.student.tripmate.controller;

import com.student.tripmate.model.Trip;
import com.student.tripmate.service.TripService;
import com.student.tripmate.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/driver/trips")
public class DriverController {
    private final TripService svc;

    public DriverController(TripService svc) { this.svc = svc; }

    @GetMapping("/requests")
    public ResponseEntity<List<Trip>> requests() {
        return ResponseEntity.ok(svc.pendingTrips());
    }

    @PostMapping("/{tripId}/accept")
    public ResponseEntity<Trip> accept(@PathVariable String tripId) {
        String username = SecurityHelper.currentUsername();
        Trip t = svc.acceptTrip(username, tripId);
        return ResponseEntity.ok(t);
    }
}
