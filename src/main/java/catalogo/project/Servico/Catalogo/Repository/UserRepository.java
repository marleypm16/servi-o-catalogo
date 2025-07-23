package catalogo.project.Servico.Catalogo.Repository;

import catalogo.project.Servico.Catalogo.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    UserDetails findByUsername(String username);
}
