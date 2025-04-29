package com.graduationProject.graduationProject.business.abstracts;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import io.jsonwebtoken.Claims;

public interface JwtService {

	String extractUsername(String token);
	
	<T> T extractClaim(String token, Function<Claims, T> claimsResolver);
	
	String generateToken(UserDetails userDetails);
	
	String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
	
	long getExpirationTime();
	
	String buildToken(Map<String, Object> extraClaims,UserDetails userDetails,long expiration);
	
	boolean isTokenValid(String token, UserDetails userDetails);
	
	boolean isTokenExpired(String token);
	
	Date extractExpiration(String token);
	
	Claims extractAllClaims(String token) ;
	
	Key getSignInKey();
	
	
	
	
	
	
	
	
	
	
	
	
}
