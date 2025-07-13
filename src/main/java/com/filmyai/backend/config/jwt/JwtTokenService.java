package com.filmyai.backend.config.jwt;

import com.filmyai.backend.service.SecurityService.CustomeUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

// for Generating JWT Token
@Service
public class JwtTokenService {

    private final JwtEncoder jwtEncoder;

    public JwtTokenService(JwtEncoder jwtEncoder) {
        this.jwtEncoder = jwtEncoder;
    }



    public String generateToken(Authentication authentication) {
        var scope = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        Object principal = authentication.getPrincipal();

        String firstName = "";
        String lastName = "";
        if (principal instanceof CustomeUserDetails userDetails) {
            firstName = userDetails.getFirstName();
            lastName = userDetails.getLastName();
        }

        var claims = JwtClaimsSet.builder()
                .issuer("Self")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plus(30, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("Role",scope)
                .claim("firstName", firstName)
                .claim("lastName", lastName)
                .build();

        return this.jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }

}
