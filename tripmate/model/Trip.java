package com.student.tripmate.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document("trips")
public class Trip {
    @Id
    private String id;
    private String passengerId;
    private String driverId; // nullable
    private String fromLoc;
    private String toLoc;
    private String status; // REQUESTED, ACCEPTED, COMPLETED
    private Date createdAt;

    public Trip() {}

    public Trip(String passengerId, String fromLoc, String toLoc) {
        this.passengerId = passengerId;
        this.fromLoc = fromLoc;
        this.toLoc = toLoc;
        this.status = "REQUESTED";
        this.createdAt = new Date();
    }

    // getters & setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getPassengerId() { return passengerId; }
    public void setPassengerId(String passengerId) { this.passengerId = passengerId; }
    public String getDriverId() { return driverId; }
    public void setDriverId(String driverId) { this.driverId = driverId; }
    public String getFromLoc() { return fromLoc; }
    public void setFromLoc(String fromLoc) { this.fromLoc = fromLoc; }
    public String getToLoc() { return toLoc; }
    public void setToLoc(String toLoc) { this.toLoc = toLoc; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
