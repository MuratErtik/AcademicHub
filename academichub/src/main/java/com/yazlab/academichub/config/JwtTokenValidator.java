package com.yazlab.academichub.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidator extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
    
        // 🔥 GEÇİCİ EKLEME: Aşağıdaki endpointlerde JWT kontrolü devre dışı bırakıldı
        String path = request.getRequestURI();
        if (path.startsWith("/api/application/print-articles") 
            || path.startsWith("/api/application/article-category-count") 
            || path.startsWith("/api/application/print-publication-criteria")
            || path.startsWith("/api/application/validate")) { // ✅ BURASI EKLENDİ
            filterChain.doFilter(request, response);
            return;
        }
        // 🔥 GEÇİCİ EKLEME BİTİŞ
    
        String jwt = request.getHeader("Authorization");
    
        if (jwt != null) {
            jwt = jwt.substring(7);
            try {
                SecretKey secretKey = Keys.hmacShaKeyFor(JWT_CONSTANT.SECRET_KEY.getBytes());
                Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(jwt).getBody();
    
                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
    
                List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                Authentication authentication = new UsernamePasswordAuthenticationToken(email, null, auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);
    
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token!");
            }
        }
    
        filterChain.doFilter(request, response);
    }    
}