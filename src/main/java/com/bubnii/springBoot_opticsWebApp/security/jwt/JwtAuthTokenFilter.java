package com.bubnii.springBoot_opticsWebApp.security.jwt;

import com.bubnii.springBoot_opticsWebApp.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
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
import java.util.Objects;

@Component
public class JwtAuthTokenFilter extends OncePerRequestFilter {

    private final JwtProvider tokenProvider;
    private final UserDetailsServiceImpl userDetailsService;

    public JwtAuthTokenFilter(JwtProvider tokenProvider, UserDetailsServiceImpl userDetailsService) {
        this.tokenProvider = tokenProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(final HttpServletRequest request,
                                    final HttpServletResponse response,
                                    final FilterChain chain) throws ServletException, IOException {

        final String jwt = getJwt(request);

        if (Objects.nonNull(jwt) && tokenProvider.validateJwtToken(jwt)) {
            final Integer id = tokenProvider.getIdFromJwtToken(jwt);
            final UserDetails userDetails = userDetailsService.loadUserById(id);

            final UsernamePasswordAuthenticationToken authentication
                    = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authentication);
        }


        chain.doFilter(request, response);

    }

    public String getJwt(HttpServletRequest request) {

        final String authHeader = request.getHeader("Authorization");

        if (Objects.nonNull(authHeader) && authHeader.startsWith("Bearer ")) {
            return authHeader.replace("Bearer ", "");
        }

        return null;
    }
}
