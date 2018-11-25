package com.AdminPanel.Angular5SpringBoot.security;

import com.AdminPanel.Angular5SpringBoot.security.exception.TokenAuthenticationHeaderNotFound;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException {

        if (authException instanceof TokenAuthenticationHeaderNotFound) {
            response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, authException.getMessage());
        } else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        }
    }
}
