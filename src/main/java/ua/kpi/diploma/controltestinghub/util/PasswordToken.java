package ua.kpi.diploma.controltestinghub.util;

import io.jsonwebtoken.*;
import org.springframework.context.annotation.PropertySource;
import ua.kpi.diploma.controltestinghub.model.User;

import java.util.Base64;
import java.util.Date;

@PropertySource("classpath:constants.properties")
public class PasswordToken {
    private String RESET_SECRET = "jf3e8aspol2c";

    private String RESET_SECRET_ENC = Base64.getEncoder().encodeToString(RESET_SECRET.getBytes());


    public String generatePasswordResetToken(User user) {
        String token = Jwts.builder()
                .setSubject(user.getEmail())
                .setExpiration(new Date((new Date()).getTime() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS512, RESET_SECRET_ENC)
                .compact();
        return token;
    }

    public String getEmailFromResetToken(String token){
        return Jwts.parser()
                .setSigningKey(RESET_SECRET_ENC)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public String resolveToken(String token){
        if (token != null && token.startsWith("Bearer")){
            return token.replace("Bearer", "");
        }
        return null;
    }

    public boolean validatePasswordResetToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(RESET_SECRET_ENC).parseClaimsJws(token);
            return !claimsJws.getBody().getExpiration().before(new Date());
        }catch (JwtException | IllegalArgumentException e){
            throw new JwtException("Password JWT token exception");
        }
    }
}
