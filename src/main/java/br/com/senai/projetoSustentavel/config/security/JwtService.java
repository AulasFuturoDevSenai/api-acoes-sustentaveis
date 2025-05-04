package br.com.senai.projetoSustentavel.config.security;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import br.com.senai.projetoSustentavel.model.entity.Responsavel;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.function.Function;

@Service
public class JwtService {

    private static final String SECRET_KEY = "ef8cd17c8d4c4dcf0be30b16f04ba98bd8f588bcceb71c84c489e083edcdf7a84299c5cf8711aeb375802eff3c7f0c62eb37eddc4cba0ff01571ada5cdb6a99fd96f6fd5afa72b40a5ca9927b1204e048a6ebc94d30b0d65a327dd3c2ad83103c5512db5d2fb3479978e8b37ea34d2090a35d3e70d3cff99b0c84513a7d9d10822a2d27bda93081a2cc6fc7adddd921666d6cc80e5db1e51623e0f3205a8abee9433a0095ce70e9dedb16711d2e2c4c39707a3ac9d8165880f095b96e1433908ffb39418bf57efe0e8b95705940760e06cecdd169f3dd7688842fce0f5040b90167d61c71ad05740cd39d2f87ad5c51aba1e1354e5f5f61be4b5283f05c9e4ca";
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 horas

    private Key getSignInKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        if (userDetails instanceof Responsavel responsavel) {
            claims.put("role", responsavel.getRole()); // adiciona role no token
        }

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractRole(String token) {
        return extractClaim(token, claims -> claims.get("role", String.class));
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
