package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;

@Service
public class ConvenioService implements ICrudService<Convenio> {

    private final ConvenioRepository repo;

    public ConvenioService(ConvenioRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Convenio> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return repo.busca(termoBusca);
        }
        return repo.findAll();
    }

    @Override
    public Convenio get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Convenio save(Convenio objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
