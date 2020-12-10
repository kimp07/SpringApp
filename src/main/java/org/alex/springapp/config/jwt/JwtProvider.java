package org.alex.springapp.config.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 *
 * @author Alex
 */
@Component
public class JwtProvider {

    private static final Logger LOG = LogManager.getLogger(JwtProvider.class);

    private static final String JWT_SECRET = "alex_springapp";
    private static final int JWT_TERM = 15; //Days

    public String generateToken(String login) {
        Date date = Date.from(
                LocalDate
                        .now().plusDays(JWT_TERM)
                        .atStartOfDay(ZoneId.systemDefault())
                        .toInstant()
        );
        return Jwts.builder()
                .setSubject(login)
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            LOG.error("Invalid token cause ExpiredJwtException: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOG.error("Invalid token cause MalformedJwtException: {}", e.getMessage());
        } catch (SignatureException e) {
            LOG.error("Invalid token cause SignatureException: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOG.error("Invalid token cause UnsupportedJwtException: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG.error("Invalid token cause IllegalArgumentException: {}", e.getMessage());
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
