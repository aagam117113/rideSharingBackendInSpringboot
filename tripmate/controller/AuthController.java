package com.student.tripmate.controller;

import com.student.tripmate.dto.LoginPayload;
import com.student.tripmate.dto.SignupPayload;
import com.student.tripmate.dto.TokenPayload;
import com.student.tripmate.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/auth")
public class AuthController {
    private final AuthService svc;

    public AuthController(AuthService svc) { this.svc = svc; }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupPayload p) {
        svc.signup(p);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<TokenPayload> signin(@Valid @RequestBody LoginPayload p) {
        TokenPayload token = svc.login(p);
        return ResponseEntity.ok(token);
    }
}
