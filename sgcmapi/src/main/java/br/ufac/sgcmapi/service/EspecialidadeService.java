package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.repository.EspecialidadeRepository;

@Service
public class EspecialidadeService implements ICrudService<Especialidade> {

    @Autowired
    private EspecialidadeRepository repo;

    @Override
    public List<Especialidade> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return repo.busca(termoBusca);
        }
        return repo.findAll();
    }

    @Override
    public Especialidade get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Especialidade save(Especialidade objeto) {
       return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
