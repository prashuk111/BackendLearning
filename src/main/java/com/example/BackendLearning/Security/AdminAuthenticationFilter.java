package com.example.BackendLearning.Security;

import com.example.BackendLearning.Service.AdminService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class AdminAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final AdminService adminService;

    public AdminAuthenticationFilter(
            AdminService adminService,
            JwtService jwtService) {

        this.adminService = adminService;
        this.jwtService = jwtService;
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getServletPath().equals("/admin/login");
    }


    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        System.out.println("=================================");
        System.out.println("REQUEST: " + request.getMethod() + " " + request.getRequestURI());


        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            System.out.println("NO VALID BEARER HEADER");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        try {

            String username = jwtService.extractUsername(token);



            if (username != null &&
                    SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails =
                        adminService.loadUserByUsername(username);



                boolean valid =
                        jwtService.isTokenValid(token, userDetails);



                if (valid) {

                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(
                                    userDetails,
                                    null,
                                    userDetails.getAuthorities()
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);


                }
            }

        } catch (Exception e) {

            System.out.println("JWT ERROR: " + e.getClass().getName());
            System.out.println("JWT MESSAGE: " + e.getMessage());
        }

        filterChain.doFilter(request, response);
    }}