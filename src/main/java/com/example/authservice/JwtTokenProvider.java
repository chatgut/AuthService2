package com.example.authservice;
import com.example.authservice.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {
    @Autowired
    private JwtConfig jwtConfig;

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", user.getUsername());
        claims.put("sub",user.getUuid());
        Date now = new Date();
        Date expiration = new Date(now.getTime() + jwtConfig.getExpiration() * 1000);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiration)
                .setSubject(user.getUuid())
                .signWith(SignatureAlgorithm.HS512, jwtConfig.getSecret())
                .compact();
    }

    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
        return (String) claims.get("username");
    }
    public String getUUIDFromToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token).getBody();
        return (String)claims.get("sub");
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtConfig.getSecret()).parseClaimsJws(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

