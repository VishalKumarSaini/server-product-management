package com.vks.serverproductmanagement.config;

import com.vks.serverproductmanagement.service.serviceImpl.JwtUserDetailsServiceImpl;
import com.vks.serverproductmanagement.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUserDetailsServiceImpl jwtUserDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public JwtAuthenticationFilter(JwtUserDetailsServiceImpl jwtUserDetailsService, JwtUtil jwtUtil) {
        this.jwtUserDetailsService = jwtUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //get jwt
        //Bearer
        //validate

        String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String requestJwtToken = null;

        //Checking Null and Format
        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            requestJwtToken = requestTokenHeader.substring(7);

            try {
                username = this.jwtUtil.extractUsername(requestJwtToken);
            } catch (Exception e) {
                e.printStackTrace();
            }

            UserDetails existingUserDetails = jwtUserDetailsService.loadUserByUsername(username);

            //Security
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        existingUserDetails, //principal
                        null,
                        existingUserDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // WebAuthenticationDetailsSource
                // Called by a class when it wishes a new authentication details instance to be created.

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

            } else {
                System.out.println("Token is not valid");
            }


        }

        // Sending forward the request as we have validated the user & set the necessary details
        filterChain.doFilter(request, response);


    }
}
