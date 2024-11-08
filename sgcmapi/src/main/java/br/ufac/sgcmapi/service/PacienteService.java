package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.repository.PacienteRepository;

@Service
public class PacienteService implements ICrudService<Paciente> {

    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Paciente> get(String termoBusca) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return repo.busca(termoBusca);
        }
        return repo.findAll();
    }

    @Override
    public Paciente get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Paciente save(Paciente objeto) {
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }
    
}
