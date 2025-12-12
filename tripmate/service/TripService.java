package com.student.tripmate.service;

import com.student.tripmate.dto.TripCreatePayload;
import com.student.tripmate.exception.BadRequestEx;
import com.student.tripmate.exception.NotFoundEx;
import com.student.tripmate.model.AppUser;
import com.student.tripmate.model.Trip;
import com.student.tripmate.repository.AppUserRepository;
import com.student.tripmate.repository.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService {
    private final TripRepository tripRepo;
    private final AppUserRepository userRepo;

    public TripService(TripRepository tripRepo, AppUserRepository userRepo) {
        this.tripRepo = tripRepo;
        this.userRepo = userRepo;
    }

    public Trip createTrip(String username, TripCreatePayload p) {
        AppUser u = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundEx("user not found"));
        if (!"ROLE_USER".equals(u.getRole())) throw new BadRequestEx("only passengers can request trips");
        Trip t = new Trip(u.getId(), p.getFromLoc(), p.getToLoc());
        return tripRepo.save(t);
    }

    public List<Trip> pendingTrips() {
        return tripRepo.findByStatus("REQUESTED");
    }

    public Trip acceptTrip(String username, String tripId) {
        AppUser driver = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundEx("driver not found"));
        if (!"ROLE_DRIVER".equals(driver.getRole())) throw new BadRequestEx("only drivers can accept");
        Trip t = tripRepo.findById(tripId).orElseThrow(() -> new NotFoundEx("trip not found"));
        if (!"REQUESTED".equals(t.getStatus())) throw new BadRequestEx("trip must be REQUESTED");
        t.setDriverId(driver.getId());
        t.setStatus("ACCEPTED");
        return tripRepo.save(t);
    }

    public Trip finishTrip(String username, String tripId) {
        Trip t = tripRepo.findById(tripId).orElseThrow(() -> new NotFoundEx("trip not found"));
        if (!"ACCEPTED".equals(t.getStatus())) throw new BadRequestEx("trip must be ACCEPTED");
        AppUser caller = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundEx("user not found"));
        boolean isPassenger = caller.getId().equals(t.getPassengerId());
        boolean isDriver = t.getDriverId() != null && caller.getId().equals(t.getDriverId());
        if (!isPassenger && !isDriver) throw new BadRequestEx("only passenger or assigned driver can finish trip");
        t.setStatus("COMPLETED");
        return tripRepo.save(t);
    }

    public List<Trip> myTrips(String username) {
        AppUser u = userRepo.findByUsername(username).orElseThrow(() -> new NotFoundEx("user not found"));
        return tripRepo.findByPassengerId(u.getId());
    }
}
