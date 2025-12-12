package com.student.tripmate.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class SignupPayload {
    @NotBlank(message = "username required")
    @Size(min = 3)
    private String username;

    @NotBlank(message = "password required")
    @Size(min = 4)
    private String password;

    @NotBlank(message = "role required")
    @Pattern(regexp = "ROLE_USER|ROLE_DRIVER", message = "role must be ROLE_USER or ROLE_DRIVER")
    private String role;

    // getters/setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
