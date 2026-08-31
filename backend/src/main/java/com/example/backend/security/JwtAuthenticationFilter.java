package com.example.backend.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String token = getJWTfromRequest(request);
        if (token != null && !token.equals("null")) {
            log.info("JWT Token found in request: {}", token);

            try {
                // Validate Firebase JWT and extract user details
                FirebaseToken firebaseToken = FirebaseAuth.getInstance().verifyIdToken(token);

                String email = firebaseToken.getEmail();
                List<String> roles = (List<String>) firebaseToken.getClaims().get("roles");

                log.info("Firebase Token validated successfully for user: {}", email);
                log.info("User type from Firebase Token: {}", roles);

                UserDetails userDetails = customUserDetailsService.loadUserByUsername(email);

                // Set authentication in the SecurityContext
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());

                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                log.info("User authenticated and SecurityContext updated for user: {}", email);

            } catch (FirebaseAuthException e) {
                log.warn("Firebase Token validation failed: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            } catch (IllegalArgumentException e) {
                log.warn("Invalid user type in Firebase Token: {}", e.getMessage());
                SecurityContextHolder.clearContext();
            }
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTfromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            log.info("Bearer Token found in request header");
            return bearerToken.substring(7);
        } else {
            return null;
        }
    }
}