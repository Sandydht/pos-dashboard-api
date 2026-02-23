package com.flexpos.pos_dashboard_api.config.security;

import com.flexpos.pos_dashboard_api.modules.auth.entity.Role;
import com.flexpos.pos_dashboard_api.modules.auth.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String SECRET = "very-secret-key-very-secret-key";

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("username", user.getUsername())
                .claim("roles", user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .toList())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }
}
