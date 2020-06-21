package com.bubnii.springBoot_opticsWebApp.security.jwt;

import com.bubnii.springBoot_opticsWebApp.security.model.UserPrinciple;
import io.jsonwebtoken.*;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    private static final String jwtSecret = "secret";
    private static final Logger LOGGER = Logger.getLogger(JwtProvider.class);

    public String generateAccessToken(final Authentication authentication) {

        final UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrinciple.getUserType()).toString())
                .setId((userPrinciple.getId()).toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String generateRefreshToken(Authentication authentication) {
        final UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject((userPrinciple.getUserType()).toString())
                .setId((userPrinciple.getId()).toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }

    public boolean validateJwtToken(final String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Invalid JWT signature -> Message - " + e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Invalid JWT token -> Message - " + e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("Expired JWT token -> Message - " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Unsupported JWT token -> Message - " + e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("JWT claims string is empty -> Message - " + e.getMessage());
        }
        return false;
    }

    public Integer getIdFromJwtToken(final String token) {
        return Integer.valueOf(Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getId());
    }
}
