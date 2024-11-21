package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.ufac.sgcmapi.model.Atendimento;
import br.ufac.sgcmapi.model.EStatus;
import br.ufac.sgcmapi.repository.AtendimentoRepository;

@Service
public class AtendimentoService implements ICrudService<Atendimento>, IPageService<Atendimento> {

    private final AtendimentoRepository repo;

    public AtendimentoService(AtendimentoRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Atendimento> get(String termoBusca) {
        var ordenacao = Sort.by("data").ascending();
        var registros = this.get(termoBusca, Pageable.unpaged(ordenacao));
        return registros.getContent();
    }

    @Override
    public Page<Atendimento> get(String termoBusca, Pageable page) {
        if (termoBusca != null && !termoBusca.isBlank()) {
            return repo.busca(termoBusca, page);
        }
        return repo.findAll(page);
    }

    @Override
    public Atendimento get(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Atendimento save(Atendimento objeto) {
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return repo.save(objeto);
    }

    @Override
    public void delete(Long id) {
        var registro = this.get(id);
        if (registro != null) {
            registro.setStatus(EStatus.CANCELADO);
            this.save(registro);
        }
    }

    public Atendimento updateStatus(Long id) {
        var registro = this.get(id);
        if (registro != null) {
            var novoStatus = registro.getStatus().proximo();
            registro.setStatus(novoStatus);
            this.save(registro);
        }
        return registro;
    }

    
}
