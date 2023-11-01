package com.mohamed.applicationbancaire.config;

import com.mohamed.applicationbancaire.repositories.UserRepository;
import com.mohamed.applicationbancaire.token.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String BEARER = "Bearer ";

    private final JwtUtils jwtUtils;
    private final UserRepository repository;

    private final TokenRepository tokenRepository;
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain)
            throws ServletException, IOException
    {
        String authHeader = request.getHeader(AUTHORIZATION);
        String userEmail;
        String jwt;

        /* on impose pour chaque requête il faut une nouveau authentification
            * ajouter un token dans postman même l'user est déja authentifié
            * SecurityContextHolder.getContext().setAuthentication(null);
            * mais c'est le role de SessionCreationPolicy.STATELESS dans SecurityConfig
        */
        if(authHeader == null || !authHeader.startsWith(BEARER)){
            filterChain.doFilter(request,response);
            return;
        }

        jwt = authHeader.substring(7);
        userEmail = jwtUtils.extractUsername(jwt);

        if(userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null/* ça veut dire qu'il n'est pas authentifié*/){
            UserDetails userDetails = repository.findByEmail(userEmail).orElseThrow(
                    ()-> new EntityNotFoundException("User not found while validation JWT")
            );
            var isTokenValid = tokenRepository.findByToken(jwt)
                    .map( token -> !token.isExpired() && !token.isInvoked() )
                    .orElse(false);
            if(jwtUtils.isTokenValid(jwt, userDetails) && isTokenValid){
                UsernamePasswordAuthenticationToken authenticationToken =new UsernamePasswordAuthenticationToken(
                        userDetails , null ,userDetails.getAuthorities()
                );
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
