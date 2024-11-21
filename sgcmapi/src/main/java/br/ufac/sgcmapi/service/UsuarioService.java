package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.repository.UsuarioRepository;

@Service
public class UsuarioService implements ICrudService<Usuario> {

    private final UsuarioRepository repo;

    public UsuarioService(UsuarioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Usuario> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return repo.busca(termoBusca);
        }
        return repo.findAll();
    }

    @Override
    public Usuario get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario objeto) {
        if (objeto.getSenha() == null || objeto.getSenha().isBlank()) {
            var usuario = get(objeto.getId());
            if (usuario != null) {
                objeto.setSenha(usuario.getSenha());
            }
        } else {
            var passEncoder = new BCryptPasswordEncoder();
            var senhaCriptografada = passEncoder.encode(objeto.getSenha());
            objeto.setSenha(senhaCriptografada);
        }
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    public Usuario getByNomeUsuario(String nomeUsuario) {
        return repo.findByNomeUsuario(nomeUsuario);
    }
    
}
