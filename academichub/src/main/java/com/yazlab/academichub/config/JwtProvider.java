package com.yazlab.academichub.config;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

    SecretKey secretKey = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());

    public String generateToken(Authentication authentication) {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000)) // 24saat surecek sekilde
                .claim("email", authentication.getName())
                .claim("authorities", roles)
                .signWith(secretKey)
                .compact();

        return jwt;
    }

    public String getEmailFromJwtToken(String jwt) {

        try {
            if (jwt == null || !jwt.startsWith("Bearer ")) {

                throw new IllegalArgumentException("Invalid token");

            }

            jwt = jwt.substring(7);

            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();

            String email = String.valueOf(claims.get("email"));

            return email;

        } catch (Exception e) {

            throw new RuntimeException("Failed to extract email from JWT", e);
        }

    }

    public List<String> getUserRolesFromJwtToken(String jwt) {

        try {

            if (jwt == null || !jwt.startsWith("Bearer ")) {

                throw new IllegalArgumentException("Invalid token");

            }

            jwt = jwt.substring(7);

            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();

            String authorities = (String) claims.get("authorities");

            return Arrays.asList(authorities.split(","));

        } catch (Exception e) {

            throw new RuntimeException("Failed to extract user roles from JWT", e);
        }
    }

    // bir kullanıcının yetkilerini (GrantedAuthority olarak) alır ve bu yetkileri
    // bir string olarak birleştirir.
    private String populateAuthorities(Collection<? extends GrantedAuthority> authorities) {

        Set<String> auths = new HashSet<>();

        for (GrantedAuthority authority : authorities) {
            auths.add(authority.getAuthority());
        }

        return String.join(",", auths);

    }
}
