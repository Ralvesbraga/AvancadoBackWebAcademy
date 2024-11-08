package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Unidade;
import br.ufac.sgcmapi.repository.UnidadeRepository;

@Service
public class UnidadeService implements ICrudService<Unidade> {

    private final UnidadeRepository repo;

    public UnidadeService(UnidadeRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Unidade> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return repo.busca(termoBusca);
        }
        return repo.findAll();
    }

    @Override
    public Unidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Unidade save(Unidade objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);        
    }
    
}
