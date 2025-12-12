package com.student.tripmate.dto;

import jakarta.validation.constraints.NotBlank;

public class TripCreatePayload {
    @NotBlank(message = "from location required")
    private String fromLoc;

    @NotBlank(message = "to location required")
    private String toLoc;

    // getters/setters
    public String getFromLoc() { return fromLoc; }
    public void setFromLoc(String fromLoc) { this.fromLoc = fromLoc; }
    public String getToLoc() { return toLoc; }
    public void setToLoc(String toLoc) { this.toLoc = toLoc; }
}
