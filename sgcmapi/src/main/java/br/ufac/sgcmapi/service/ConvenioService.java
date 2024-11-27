package br.ufac.sgcmapi.service;

import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import br.ufac.sgcmapi.model.Convenio;
import br.ufac.sgcmapi.repository.ConvenioRepository;
import br.ufac.sgcmapi.service.dto.ItemRespostaAnsDto;
import br.ufac.sgcmapi.service.dto.RespostaAnsDto;

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

    @Scheduled(fixedDelay = 3000)
    public void verificarCadastroAns(){
        var url = "https://www.ans.gov.br/operadoras-entity/v1/operadoras";
        var convenios= repo.findAll();
        var cliente = RestClient.create();
        for (Convenio convenio : convenios) {
            var cnpjSemFormatacao = convenio.getCnpj().replace("[^0-9]", "");
            var resultado = cliente.get()
                .uri(url + "?cnpj=" + cnpjSemFormatacao)
                .retrieve()
                .body(ItemRespostaAnsDto.class);
                var item = resultado;
                System.out.println(convenio.getNome() + ": " + item.ativa());
                convenio.setAtivo(item.ativa());
                repo.save(convenio);
            }
    }
    
}
