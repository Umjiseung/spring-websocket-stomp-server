package server.global.security.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import server.domain.auth.presentation.dto.response.TokenResponseDto;
import server.global.security.exception.ExpiredTokenException;
import server.global.security.exception.InvalidTokenException;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.LocalDateTime;
import java.util.Date;


@Component
@RequiredArgsConstructor
public class JwtProvider {
    private static final String AUTHORITIES_KEY = "auth";
    private static final long ACCESS_TOKEN_TIME = 60L * 60 * 24;
    public static final long REFRESH_TOKEN_TIME = 60L * 60 * 24 * 7;

    private final JwtProperties jwtProperties;

    private Key getSignInAccessKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getAccessSecret().getBytes(StandardCharsets.UTF_8));
    }

    private Key getSignInSecretKey() {
        return Keys.hmacShaKeyFor(jwtProperties.getRefreshSecret().getBytes(StandardCharsets.UTF_8));
    }

    public TokenResponseDto generateTokenDto(String nickname) {
        return TokenResponseDto.builder()
                .accessToken(generateAccessToken(nickname))
                .refreshToken(generateRefreshToken(nickname))
                .accessTokenExpiresIn(LocalDateTime.now().plusSeconds(ACCESS_TOKEN_TIME))
                .refreshTokenExpiresIn(LocalDateTime.now().plusSeconds(REFRESH_TOKEN_TIME))
                .build();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(getSignInAccessKey()).build().parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException e) {
            throw new ExpiredTokenException();
        } catch (Exception e) {
            throw new InvalidTokenException();
        }
    }

    public String generateAccessToken(String nickname) {
        Date accessTokenExpiresIn = new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME*1000);

        return Jwts.builder()
                .setSubject(nickname)
                .claim(AUTHORITIES_KEY, "JWT")
                .setIssuedAt(new Date())
                .setExpiration(accessTokenExpiresIn)
                .signWith(getSignInAccessKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String nickname) {
        Date refreshTokenExpiresIn = new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME*1000);

        return Jwts.builder()
                .setSubject(nickname)
                .setExpiration(refreshTokenExpiresIn)
                .signWith(getSignInSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }
}
