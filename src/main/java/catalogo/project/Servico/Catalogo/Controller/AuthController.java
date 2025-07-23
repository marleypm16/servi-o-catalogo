package catalogo.project.Servico.Catalogo.Controller;

import catalogo.project.Servico.Catalogo.DTO.LoginResponseDTO;
import catalogo.project.Servico.Catalogo.DTO.LoginRequestDTO;
import catalogo.project.Servico.Catalogo.DTO.RegisterRequestDTO;
import catalogo.project.Servico.Catalogo.Entity.Users;
import catalogo.project.Servico.Catalogo.Services.AuthService;
import catalogo.project.Servico.Catalogo.Services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO authRequestDTO) {
        return ResponseEntity.ok(authService.login(authRequestDTO));
    }
    @PostMapping("/register")
    public Users registerUser(@RequestBody RegisterRequestDTO registerRequestDTO) {
        return authService.registerUser(registerRequestDTO);

    }
}
