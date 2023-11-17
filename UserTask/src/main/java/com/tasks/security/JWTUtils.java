package com.tasks.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    private final String secretKey = "KishoreJWT";
    public String generateToken(Authentication authentication){
        String email = authentication.getName();
        Date curentDate   = new Date();
        Date expiryDate = new Date(curentDate.getTime()+60*60*1000);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(curentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secretKey).compact();

    }

    public String getEmailFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    public boolean isTokenValid(String token){
            Claims claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
            return claims.getExpiration().before(new Date());
    }

}
