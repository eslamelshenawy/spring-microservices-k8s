package vmware.services.gateway.service;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import vmware.services.gateway.model.UserPrincipal;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Component
@Slf4j
public class JWTTokenProvider implements Serializable {

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Value("${jwt.expirationInMs}")
    private int jwtExpirationInMs;

    public String generateToken(UserPrincipal userPrincipal){
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userPrincipal.getUsername());

//        List<String> roles = userPrincipal
//                .getAuthorities()
//                .stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.toList());
//
//        return Jwts
//                .builder()
//                .setIssuer("Demo App")
//                .setSubject(userPrincipal.getUsername())
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(new Date().getTime() + jwtExpirationInMs * 10000))
//                .claim("Roles", roles)
//                .signWith(SignatureAlgorithm.HS512, jwtSecret)
//                .compact();
    }
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().
                setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationInMs * 1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    public boolean validateToken(String jwt) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
            return true;
        } catch (SignatureException ex) {
            log.error("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;


    }
    public String getUserNameFromToken(String token){
        return  Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }


}
