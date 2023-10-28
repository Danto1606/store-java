package com.danny.store.java.config;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.Jwts.SIG;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private static final String SECRETE_KEY = 
			"85833563e2275370a8a99a872058c7c0fe7f9b0822f87bfbfd71ebdfaa7c83c2";

	
	
	public String extractUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	
	
	public String generateToken(UserDetails user) {
		return generateToken(new HashMap<>(), user);
	}
	
	public boolean isTokenValid(String token, UserDetails user) {

		return extractUserName(token)
				.equals(user.getUsername())
				&& !isTokenExpired(token);
	}
	
	
	public <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
		 
		final Claims claim = extractAllClaims(token);
		
		return claimResolver.apply(claim);
		
	}
	
	
	public String generateToken(
			Map<String, ?> extraClaims,
			UserDetails user
	) {
		
		Calendar calender = Calendar.getInstance();
		
		Date currentDate = calender.getTime();
		
		calender.set(Calendar.MONTH, calender.get(Calendar.MONTH)+1);
		
		Date expirationDate = calender.getTime();
		
		return Jwts
				.builder()
				.claims(extraClaims)
				.subject(user.getUsername())
				.issuedAt(currentDate)
				.expiration(expirationDate)
				.signWith(getSignInKey(), SIG.HS256)
				.compact();
	}
	
	
	private boolean isTokenExpired(String token) {
		
		return extractClaim(token, Claims::getExpiration)
				.before(Calendar.getInstance().getTime());
	}	
	
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSignInKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	private SecretKey getSignInKey() {
		byte[] keyByte = Decoders.BASE64.decode(SECRETE_KEY);
		
		return Keys.hmacShaKeyFor(keyByte);
	}
	
	
}
