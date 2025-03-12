package com.exam.examserver.config;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.exam.examserver.services.impl.UserDetailServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Autowired
    private JwtUtils jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, 
                                    HttpServletResponse response, 
                                    FilterChain filterChain) 
        throws ServletException, IOException {
        
        // Skip filter for authentication endpoint
        if (request.getServletPath().equals("/user/generate-token") || request.getServletPath().equals("/user/")) {
            filterChain.doFilter(request, response);
            return;
        }

        final String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;
        System.out.println("Authorization header -- " + authorizationHeader);
        System.out.println("Request URL -- " + request.getRequestURL());
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            System.out.println("Jwt created is : "+jwt);
            try {
            	System.out.println("getting username from jwt");
                username = jwtUtil.extractUsername(jwt);
                System.out.println("username get by jwt is  -- > "+username);
            } catch (ExpiredJwtException e) {
                sendError(response, "Token expired", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            } catch (Exception e) {
                sendError(response, "Invalid token", HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailService.loadUserByUsername(username);
            
            if (jwtUtil.validateToken(jwt, userDetails)) {
                UsernamePasswordAuthenticationToken authentication = 
                    new UsernamePasswordAuthenticationToken(
                        userDetails, 
                        null, 
                        userDetails.getAuthorities()
                    );
                authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        
        filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response, String message, int code) 
        throws IOException {
        response.setContentType("application/json");
        response.setStatus(code);
        response.getWriter().write("{\"error\": \"" + message + "\"}");
    }
}