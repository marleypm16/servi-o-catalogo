package catalogo.project.Servico.Catalogo.Config;

import catalogo.project.Servico.Catalogo.Repository.UserRepository;
import catalogo.project.Servico.Catalogo.Services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService tokenService;

    @Autowired
    private UserRepository userRepository;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var token = this.getToken(request);
        if(token != null){
            var login = tokenService.verifyToken(token);
            if(login != null){
                UserDetails user = userRepository.findByUsername(login);

                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }


        }
        filterChain.doFilter(request, response);
    }


    private String getToken(HttpServletRequest request) {
      String  authHeader = request.getHeader("Authorization");
      if (authHeader != null && authHeader.startsWith("Bearer ")) {
          return authHeader.replace("Bearer ", "");
      }
      return null;

    }
}
