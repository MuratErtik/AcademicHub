package com.yazlab.academichub.config;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import com.yazlab.academichub.entities.User;
import com.yazlab.academichub.entities.UserRole;
import com.yazlab.academichub.exception.AdminException;
import com.yazlab.academichub.repository.UserRepository;
import com.yazlab.academichub.repository.UserRoleRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtProvider {

    private final UserRoleRepository userRoleRepository;

    private final UserRepository userRepository;

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

    public UserRole getUserRoleFromJwtToken(String jwt) {
        try {
            if (jwt == null || !jwt.startsWith("Bearer ")) {
                throw new IllegalArgumentException("Invalid token");
            }

            jwt = jwt.substring(7);

            Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
            String authorities = (String) claims.get("authorities");

            System.out.println("Authorities from token: " + authorities);
            System.out.println("************************************");

            
            String authority = authorities.split(",")[0].trim(); 

           
            UserRole userRole = userRoleRepository.findUserRoleByUserRole(authority);

            if (userRole == null) {
                System.out.println("User role not found in database for authority: " + authority);
                System.out.println("************************************");

            }

            return userRole;

        } catch (Exception e) {
            throw new RuntimeException("Failed to extract user role from JWT", e);
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

    public String getRolefromjwtByEmail(String email) throws AdminException {

        User user = userRepository.findByEmail(email);

        UserRole userRole = userRoleRepository.findById(user.getUserRole().getUserRoleId())
                .orElseThrow(() -> new AdminException("Role has not found!"));

        return userRole.getUserRole();

    }
}
