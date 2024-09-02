package br.com.w4solution.alphacontrol.repository;

import br.com.w4solution.alphacontrol.model.pessoa.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuaioRepository extends JpaRepository<Usuario, Long> {
    UserDetails findByUsuario(String usuario);
}
