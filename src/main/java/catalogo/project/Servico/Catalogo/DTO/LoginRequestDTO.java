package catalogo.project.Servico.Catalogo.DTO;

public record LoginRequestDTO(
        String username,
        String email,
        String password
) {
}
