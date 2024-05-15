package app.romail.moodle_plus_plus.security;

import app.romail.moodle_plus_plus.domain.Account;
import app.romail.moodle_plus_plus.domain.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public Long extractId(String token) {
        return Long.parseLong(extractClaim(token, Claims::getSubject));
    }

    public String extractScope(String token) {
        return extractClaim(token, Claims::getAudience);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        Jws<Claims> claimsJws = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
        return claimsJws.getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(Account account, boolean isRefreshToken) {
        if (isRefreshToken) {
            return createToken(account.getId(), (1000L * 60 * 60 * 24 * 30), "app.romail.moodle_plus_plus.refresh_token", account.getRoles() ); // Refresh token expires in 30 days
        }
        return createToken(account.getId(), (1000L * 60 * 60 * 5), "app.romail.moodle_plus_plus.access_token", account.getRoles()); // Access token expires in 5 hours
    }

    private String createToken(Long subject, Long expirationTime, String scope, List<Role> roles) {
        return Jwts.builder()
                .setSubject(String.valueOf(subject))
                .setAudience(scope)
                .setIssuer("app.romail.moodle_plus_plus")
                .claim("roles", roles)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)) // 10 hours
                .signWith(SECRET_KEY, SignatureAlgorithm.HS256).compact();
    }

    public Boolean validateToken(String token, Long id) {
        final Long extractedUsername = extractId(token);
        return (extractedUsername.equals(id) && !isTokenExpired(token));
    }
}
