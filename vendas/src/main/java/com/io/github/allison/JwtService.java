package com.io.github.allison;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import com.io.github.allison.domain.entity.Usuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


@Service
public class JwtService {

    @Value("${security.jwt.expire}")
    private String expire;

    @Value("${security.jwt.key-assign}")
    private String keyAssign;


    public String generateToken( Usuario usuario){
        long expString = Long.valueOf(expire);
        LocalDateTime dateTimeExpire = LocalDateTime.now().plusMinutes(expString);
        Instant instant = dateTimeExpire.atZone(ZoneId.systemDefault()).toInstant();
        Date data = Date.from(instant);


        return Jwts
        
                .builder()
                .setSubject(usuario.getLogin())
                .setExpiration(data)
                .signWith(SignatureAlgorithm.HS512, keyAssign)
                .compact();
     

    
    }

    private Claims getClaims(String token) throws ExpiredJwtException{
        return Jwts
                    .parser()
                    .setSigningKey(keyAssign)
                    .parseClaimsJws(token)
                    .getBody();
    }

    public boolean tokenValid(String token){

        try{
                Claims claims = getClaims(token);
                Date datExpire = claims.getExpiration();
               LocalDateTime localDateTime =  datExpire.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();


               return !LocalDateTime.now().isAfter(localDateTime);

        }catch(Exception e){
            return false;
        }

    }

    public static void main(String[] args) {
        ConfigurableApplicationContext contexto = SpringApplication.run(VendasApplication.class);
        JwtService service = contexto.getBean(JwtService.class);

        Usuario usuario = Usuario.builder().login("allison").build();
        String token  = service.generateToken(usuario);
        System.out.println(token);

    }


}
