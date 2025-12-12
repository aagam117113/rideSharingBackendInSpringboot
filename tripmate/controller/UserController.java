package com.student.tripmate.controller;

import com.student.tripmate.model.Trip;
import com.student.tripmate.service.TripService;
import com.student.tripmate.util.SecurityHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/user")
public class UserController {
    private final TripService svc;

    public UserController(TripService svc) { this.svc = svc; }

    @GetMapping("/trips")
    public ResponseEntity<List<Trip>> myTrips() {
        String username = SecurityHelper.currentUsername();
        return ResponseEntity.ok(svc.myTrips(username));
    }
}
