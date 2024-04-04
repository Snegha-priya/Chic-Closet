package com.chiccloset.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = 8218660496743281202L;

	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	public Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey("varuvaai").parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	public String generateToken(UserDetails user) {
		return doGenerateToken(user.getUsername(), user.getAuthorities());
	}

	public ArrayList<?> getScopes(String token) {
		Claims claims = getAllClaimsFromToken(token);
		return (ArrayList<?>) claims.get("scopes");
	}

	private String doGenerateToken(String subject, Collection<? extends GrantedAuthority> grantedAuth) {
		Claims claims = Jwts.claims().setSubject(subject);
		claims.put("scopes", Arrays.asList(grantedAuth.toArray()));
		return Jwts.builder().setClaims(claims).setIssuer("Authorized")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 18000000))// 5 Hour Validity
				.signWith(SignatureAlgorithm.HS256, "varuvaai").compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	public String getUserName() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return userDetails.getUsername();
	}
}