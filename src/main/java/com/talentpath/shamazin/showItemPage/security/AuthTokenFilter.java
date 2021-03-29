package com.talentpath.shamazin.showItemPage.security;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;



@Component
public class AuthTokenFilter extends OncePerRequestFilter {

    @Value("shamazin.app.jwtsecret")
    String secret;

    //need to build a user detail object up
    @Autowired
    UserDetailsService detailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String token = extractJwt(request); //could come back null or could come back a real token
        if(token!=null) {
            //parser is a static method, signin key is secret password to decrypt
            Jws<Claims> claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            String username = claims.getBody().getSubject();
            UserDetails details = detailsService.loadUserByUsername(username);
            //take in a jwt and output a decrypted token
            UsernamePasswordAuthenticationToken convertedToken =
                    new UsernamePasswordAuthenticationToken(details, null, details.getAuthorities());
            convertedToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            //will tell authentication manager that should be looking at it with username and password


            SecurityContextHolder.getContext().setAuthentication(convertedToken);
            //trying to get somewhere without token, which is okay, as long as not an OAuth route
        }else if(token==null){
                SecurityContextHolder.getContext().setAuthentication(null);
        }
        //always want to make sure rest of filter chain executes
        filterChain.doFilter(request, response);

    }

    //if don't find header called "Authorization", should be going to one of endpoints that doesn't require Login
    private String extractJwt(HttpServletRequest request){
        String authHeader = request.getHeader("Authorization");
        if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.substring(7);
        }
        return null;
    }
}
