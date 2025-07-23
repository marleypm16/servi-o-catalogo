package catalogo.project.Servico.Catalogo.DTO;

import catalogo.project.Servico.Catalogo.Entity.enums.Roles;

public record RegisterRequestDTO(
        String username,
        String password,
        String email,
        Roles role
) {
}
