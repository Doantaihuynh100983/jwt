package com.example.demojwt.security.jwt;

import com.example.demojwt.security.userpincal.UserPrincial;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static  final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private JwtParser jwtParser;

    private static final String INVALID_JWT_TOKEN = "Invalid JWT token.";


    private String jwtSecret = "tai.doan@formos.com"; // key
    private int jwtExpiration = 86400; // thời gian sống của token
    // tạo ra token
    public String createToken(Authentication authentication){
        UserPrincial userPrincial = (UserPrincial) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrincial.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }


    public boolean validateToken(String authToken) {
        try {
            jwtParser.parseClaimsJws(authToken);
            return true;
        } catch (ExpiredJwtException e) {
            logger.error(INVALID_JWT_TOKEN, e);
        } catch (UnsupportedJwtException e) {
            logger.error(INVALID_JWT_TOKEN, e);
        } catch (MalformedJwtException e) {
            logger.error(INVALID_JWT_TOKEN, e);
        } catch (SignatureException e) {
            logger.error(INVALID_JWT_TOKEN, e);
        } catch (IllegalArgumentException e) { // TODO: should we let it bubble (no catch), to avoid defensive programming and follow the fail-fast principle?
            logger.error(INVALID_JWT_TOKEN, e.getMessage());
        }

        return false;
    }

    public String getUserNameFromToken(String token){
        String userName  = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
