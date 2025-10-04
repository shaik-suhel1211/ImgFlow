package com.imgflow.ImgFlow.security;


import com.imgflow.ImgFlow.entity.User;
import com.imgflow.ImgFlow.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserRepository userRepository;

//this jwtAuthFilter intercepts incoming http request and extracts token from header and validates,if valid then create the authentication obj and store in security context holder

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");
        String token = null;
        String email = null;

        if (header != null && header.startsWith("Bearer ")) {
            token = header.substring(7);       //extracting token
            email = jwtTokenUtil.getEmailFromToken(token);   //extracting subject from the extracted token
        }
//if email or sub is not null and SCH is null which means no logged in user,so then store in SCH
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            Optional<User> user = userRepository.findByEmail(email);
            if (user.isPresent() && jwtTokenUtil.validateToken(token)) {
                UsernamePasswordAuthenticationToken auth =
                        new UsernamePasswordAuthenticationToken(user.get(), null, null);   //authenticated authentication obj which is holding user obj(user.get())
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));     //this is for storing from which client(IP address) and session did this request come etc
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }

        filterChain.doFilter(request, response);
    }
}
