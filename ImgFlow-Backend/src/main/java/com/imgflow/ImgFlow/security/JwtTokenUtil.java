package com.imgflow.ImgFlow.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil {

    private final String SECRET = "mysecretkeymysecretkeymysecretkey123mysecretkeymysecretkeymysecretkey123";
    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour

//secret key is signed using hashbasedmessageauthenticationcode(hmac) crypto algorithm
    public Key getSigningKey(){
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }
    public String generateToken(String email) { //Jwts class has static helper methods
        return Jwts.builder()   //returns JwtBuilder obj
                .setSubject(email)   //JwtBuilder static methods
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(),SignatureAlgorithm.HS256)   //setting the signed key and the algorithm used to sign the key
                .compact();
    }
//extracts email from token by first sets the signed key and then the token if valid then gets claims(roles,username,etc)
    public String getEmailFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
//if signed key and signed key from the token matches then its valid token.
    public boolean validateToken(String token) {
        try {
            Jwts.parser()     //parser splits token into header payload and signature
                    .setSigningKey(getSigningKey()).parseClaimsJws(token); //takes signedkey and token and then checks signature from token matches the signature and payload(claims/body of token) are also valid then token is valid
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
