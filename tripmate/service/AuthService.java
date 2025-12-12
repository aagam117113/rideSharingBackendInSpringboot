package com.student.tripmate.service;

import com.student.tripmate.dto.LoginPayload;
import com.student.tripmate.dto.SignupPayload;
import com.student.tripmate.dto.TokenPayload;
import com.student.tripmate.exception.BadRequestEx;
import com.student.tripmate.model.AppUser;
import com.student.tripmate.repository.AppUserRepository;
import com.student.tripmate.util.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AppUserRepository userRepo;
    private final PasswordEncoder encoder;
    private final JwtProvider jwt;

    public AuthService(AppUserRepository userRepo, PasswordEncoder encoder, JwtProvider jwt) {
        this.userRepo = userRepo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public void signup(SignupPayload p) {
        if (userRepo.existsByUsername(p.getUsername())) {
            throw new BadRequestEx("username already used");
        }
        AppUser u = new AppUser(p.getUsername(), encoder.encode(p.getPassword()), p.getRole());
        userRepo.save(u);
    }

    public TokenPayload login(LoginPayload p) {
        AppUser u = userRepo.findByUsername(p.getUsername())
                .orElseThrow(() -> new BadRequestEx("invalid credentials"));
        if (!encoder.matches(p.getPassword(), u.getPassword())) {
            throw new BadRequestEx("invalid credentials");
        }
        String t = jwt.createToken(u.getUsername(), u.getRole());
        return new TokenPayload(t, u.getUsername(), u.getRole());
    }
}
