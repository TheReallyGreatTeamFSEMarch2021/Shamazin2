package com.talentpath.shamazin.showItemPage.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//jwt - json web token
//entry point, they login, we hand them web login
//every time request, they hand us back same web login
//this class is to handle that

@Component //allows spring to build it
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            AuthenticationException e) throws IOException, ServletException {
        //in this method login has failed
        //we need to use the response to indicate an error

        //TODO: add logger
        //HttpServletResponse is an enum
        httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: unauthorized user!*");

    }
}
