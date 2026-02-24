package com.flexpos.pos_dashboard_api.config.security;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.flexpos.pos_dashboard_api.modules.auth.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.access-token-expiration}")
	private long accessTokenExpiration;

	@Value("${jwt.refresh-token-expiration}")
	private long refreshTokenExpiration;

	public String generateAccessToken(User user) {
		Algorithm algorithm = Algorithm.HMAC256(secret);

		return JWT.create()
				.withSubject(user.getId().toString())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpiration))
				.sign(algorithm);
	}

	public String generateRefreshToken(User user) {
		Algorithm algorithm = Algorithm.HMAC256(secret);

		return JWT.create()
				.withSubject(user.getId().toString())
				.withIssuedAt(new Date())
				.withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenExpiration))
				.sign(algorithm);
	}

	public Instant generateRefreshTokenExpiry() {
		return Instant.now().plusMillis(refreshTokenExpiration);
	}

	public UUID extractUserId(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);

		DecodedJWT decodedJWT = JWT.require(algorithm)
				.build()
				.verify(token);

		return UUID.fromString(decodedJWT.getSubject());
	}
}
