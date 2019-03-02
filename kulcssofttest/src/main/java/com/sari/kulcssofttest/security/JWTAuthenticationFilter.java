package com.sari.kulcssofttest.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.sari.kulcssofttest.dto.ErrorDTO;
import com.sari.kulcssofttest.model.Admin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.sari.kulcssofttest.security.SecurityConstants.*;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    private JwtAuthenticationFailureHandler failureHandler;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                                   JwtAuthenticationFailureHandler failureHandler) {
        this.authenticationManager = authenticationManager;
        this.failureHandler = failureHandler;
        this.setFilterProcessesUrl(LOGIN_URL);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            Admin admin = new ObjectMapper()
                    .readValue(req.getInputStream(), Admin.class);
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            admin.getUsername(),
                            admin.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        logger.info(auth);

        Claims claims = Jwts.claims()
                .setSubject(((User) auth.getPrincipal()).getUsername());

        String token = Jwts.builder()
                .setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        response.addHeader("Access-Control-Expose-Headers", HEADER_STRING);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        logger.info("Failed authentication!");

        response.getWriter().write(new Gson().toJson(ErrorDTO.builder()
                .error("invalid parameters")
                .message("Username or password is incorrect!")
                .build()));
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        this.failureHandler.onAuthenticationFailure(request, response, failed);
    }
}
