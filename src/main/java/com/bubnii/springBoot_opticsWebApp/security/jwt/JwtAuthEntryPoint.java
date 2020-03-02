package com.bubnii.springBoot_opticsWebApp.security.jwt;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
    private static final Logger LOGGER = Logger.getLogger(JwtAuthEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException {

        LOGGER.error("Unauthenticated user exception: {}", e);
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
    }
}
