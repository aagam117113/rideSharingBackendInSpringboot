package com.student.tripmate.config;

import com.student.tripmate.util.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final JwtProvider jwt;

    public SecurityConfig(JwtProvider jwt) { this.jwt = jwt; }

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        JwtFilter f = new JwtFilter(jwt);

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/v2/auth/**").permitAll()
                .requestMatchers("/api/v2/trips/**").hasAnyAuthority("ROLE_USER","ROLE_DRIVER")
                .requestMatchers("/api/v2/user/**").hasAuthority("ROLE_USER")
                .requestMatchers("/api/v2/driver/**").hasAuthority("ROLE_DRIVER")
                .anyRequest().authenticated()
            )
            .addFilterBefore(f, UsernamePasswordAuthenticationFilter.class)
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
