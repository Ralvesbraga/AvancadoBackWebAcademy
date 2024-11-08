package br.ufac.sgcmapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufac.sgcmapi.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query("""
        SELECT p FROM Usuario p
        WHERE p.nomeCompleto LIKE %:termoBusca%
        OR p.nomeUsuario LIKE %:termoBusca%
    """)
    List<Usuario> busca(String termoBusca);

    Usuario findByNomeUsuario(String nomeUsuario);
    
}
