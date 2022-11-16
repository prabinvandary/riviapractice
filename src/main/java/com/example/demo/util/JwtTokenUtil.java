package com.example.demo.util;

import com.example.demo.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtTokenUtil implements Serializable {
    private static final long serialVersionUID=-96333329472346880L;
    private static final long JWT_TOKEN_VALIDITY=60*60*48;

    @Value("${jwt.secret}")
    private String secret;


    public String getUsernameFromToken(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }
    public String getIdFromToken(String token){
        return getClaimFromToken(token, Claims::getId);
    }
    public Date getIssuedAtDateFromToken(String token){
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token){
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims,T> claimsResolver){
        final Claims claims=getClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }
    public Claims getClaimsFromToken(String token){

        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        final Date expiration=getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    private Boolean ignoreTokenExpiration(String token){

        return false;
    }

    public String generateToken(CustomUserDetails userDetails){
        Map<String,Object> claims=new HashMap<>();
        return doGenerateToken(claims,userDetails.getUsername(),""+userDetails.getUser().getUserId());
    }

    private String doGenerateToken(Map<String, Object> claims, String subject, String id) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setId(id).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }
    public Boolean canTokenBeRefreshed(String token){
        return (!isTokenExpired(token)||ignoreTokenExpiration(token));
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String userName=getUsernameFromToken(token);
        return (userName.equals(userDetails.getUsername())&&!isTokenExpired(token));
    }

}