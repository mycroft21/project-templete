package com.example.demo.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import org.springframework.beans.factory.annotation.Value;

public class JwtTokenUtil {

	public static String createToken(String userId, String secretKey, long expireTimeMs) {
		Claims claims = Jwts.claims();
		claims.put("userId", userId);

		return Jwts.builder()
			.setClaims(claims)
			.setIssuedAt(new Date(System.currentTimeMillis()))
			.setExpiration(new Date(System.currentTimeMillis() + expireTimeMs))
			.signWith(SignatureAlgorithm.HS256, secretKey)
			.compact();
	}

	public static String getLoginId(String token, String secretKey) {
		return extractClaims(token, secretKey).get("userId").toString();
	}

	public static boolean isExpired(String token, String secretKey) {
		Date expiredDate = extractClaims(token, secretKey).getExpiration();
		return expiredDate.before(new Date());
	}

	private static Claims extractClaims(String token, String secretKey) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}
