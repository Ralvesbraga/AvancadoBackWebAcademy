package br.ufac.sgcmapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.controller.dto.AtendimentoDto;
import br.ufac.sgcmapi.controller.mapper.AtendimentoMapper;
import br.ufac.sgcmapi.service.AtendimentoService;

@RestController
@RequestMapping("/atendimento")
public class AtendimentoController implements ICrudController<AtendimentoDto> {

    private final AtendimentoService servico;
    private final AtendimentoMapper mapper;

    public AtendimentoController(AtendimentoService servico,
    AtendimentoMapper mapper) {
        this.servico = servico;
        this.mapper =mapper;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<AtendimentoDto>> get(@RequestParam(required = false) String termoBusca) {
        var registros = servico.get(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDto> get(@PathVariable Long id) {
        var registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/inserir")
    public ResponseEntity<AtendimentoDto> insert(@RequestBody AtendimentoDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.save(objetoConvertido);
        var dto = mapper.toDto(registro);
        return ResponseEntity.created(null).body(dto);
    }
    
    @PutMapping("/atualizar")
    public ResponseEntity<AtendimentoDto> update(@RequestBody AtendimentoDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.save(objetoConvertido);
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<AtendimentoDto> updateStatus(@PathVariable Long id) {
        var registro = servico.updateStatus(id);
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }
    
}
