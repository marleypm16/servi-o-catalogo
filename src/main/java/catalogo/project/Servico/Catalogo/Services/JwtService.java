package catalogo.project.Servico.Catalogo.Services;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {
    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return   JWT.create()
                    .withSubject(username)
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(3600)))
                    .withIssuer("catalogo-api")
                    .sign(algorithm)
            ;
        }catch (JWTCreationException exception){
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String verifyToken(String token) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
          return   JWT.require(algorithm)
                    .withIssuer("catalogo-api") // Verifica se o emissor é o mesmo
                    .build()
                    .verify(token) // Valida a assinatura e a expiração
                    .getSubject(); // Se tudo estiver ok, retorna o "subject" (username)
        }catch (JWTVerificationException exception){
            return "Erro ao verificar token JWT" + exception;
        }
    }
}
