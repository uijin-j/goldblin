package goldblin.auth.domain.service;

import static goldblin.auth.constants.AuthMessages.*;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import goldblin.auth.config.properties.JwtProperties;
import goldblin.auth.domain.Member;
import goldblin.auth.domain.enums.AuthType;
import goldblin.auth.exception.TokenValidationFailException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtManager {
	private static final long MILLISECONDS_PER_MINUTE = 60 * 1000L;
	public static final String MEMBER_ID_CLAIM = "memberId";
	public static final String ROLE_CLAIM = "role";

	private final String issuer;
	private final SecretKey secretKey;
	private final long expiredAfter;
	private final long refreshExpiredAfter;

	public JwtManager(JwtProperties jwtProperties) {
		issuer = jwtProperties.issuer();
		secretKey = Keys.hmacShaKeyFor(jwtProperties.secretKey().getBytes(StandardCharsets.UTF_8));
		expiredAfter = jwtProperties.expiredAfter() * MILLISECONDS_PER_MINUTE;
		refreshExpiredAfter = jwtProperties.refreshExpiredAfter() * MILLISECONDS_PER_MINUTE;
	}

	public String generateAccessToken(Member member) {
		return generateToken(member, expiredAfter);
	}

	public String generateRefreshToken(Member member) {
		return generateToken(member, refreshExpiredAfter);
	}

	public Long getMemberId(String token) {
		return parseClaims(token).get(MEMBER_ID_CLAIM, Long.class);
	}

	public AuthType getRole(String token) {
		return AuthType.valueOf(parseClaims(token).get(ROLE_CLAIM, String.class));
	}

	public LocalDateTime getExpiredAt(String token) {
		Date expirationDate = parseClaims(token).getExpiration();
		return LocalDateTime.ofInstant(expirationDate.toInstant(), ZoneId.systemDefault());
	}

	private String generateToken(Member member, long expiredAfter) {
		Instant now = Instant.now();
		Instant expiredAt = now.plusMillis(expiredAfter);

		return Jwts.builder()
			.setIssuer(issuer)
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(expiredAt))
			.claim(MEMBER_ID_CLAIM, member.getId())
			.claim(ROLE_CLAIM, member.getAuthorityType())
			.signWith(secretKey)
			.compact();
	}

	private Claims parseClaims(String token) {
		try {
			return Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(token)
				.getBody();
		} catch (ExpiredJwtException e) {
			throw new TokenValidationFailException(EXPIRED_TOKEN);
		} catch (Exception e) {
			throw new TokenValidationFailException(INVALID_TOKEN);
		}
	}
}
