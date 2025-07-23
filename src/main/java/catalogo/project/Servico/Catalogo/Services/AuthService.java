package catalogo.project.Servico.Catalogo.Services;

import catalogo.project.Servico.Catalogo.DTO.LoginRequestDTO;
import catalogo.project.Servico.Catalogo.DTO.LoginResponseDTO;
import catalogo.project.Servico.Catalogo.DTO.RegisterRequestDTO;
import catalogo.project.Servico.Catalogo.Entity.Users;
import catalogo.project.Servico.Catalogo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public Users registerUser(RegisterRequestDTO registerRequestDTO) {
        String senhaCriptografada = passwordEncoder.encode(registerRequestDTO.password());
        Users user = new Users();
        user.setEmail(registerRequestDTO.email());
        user.setPassword(senhaCriptografada);
        user.setRole(registerRequestDTO.role());
        user.setUsername(registerRequestDTO.username());
        return userRepository.save(user);

    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginRequestDTO.username(), loginRequestDTO.password());

        // 1. A autenticação é feita
        var auth = this.authenticationManager.authenticate(usernamePassword);

        // 2. Extrai o usuário principal (a entidade Users) do objeto de autenticação
        var user = (Users) auth.getPrincipal();

        // 3. Gera o token usando o usuário autenticado
        String token = jwtService.generateToken(user.getUsername()); // Supondo que o serviço de token agora aceite o objeto User

        // 4. Retorna a resposta usando os dados canônicos do usuário
        return new LoginResponseDTO(user.getUsername(), token);
    }


}
