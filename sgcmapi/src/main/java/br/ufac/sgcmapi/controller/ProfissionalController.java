package br.ufac.sgcmapi.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufac.sgcmapi.controller.dto.ProfissionalDto;
import br.ufac.sgcmapi.controller.mapper.ProfissionalMapper;
import br.ufac.sgcmapi.service.ProfissionalService;
import br.ufac.sgcmapi.validator.groups.OnInsert;
import br.ufac.sgcmapi.validator.groups.OnUpdate;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController implements ICrudController<ProfissionalDto> {

    private final ProfissionalService servico;
    private final ProfissionalMapper mapper;

    public ProfissionalController(
            ProfissionalService servico,
            ProfissionalMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<ProfissionalDto>> get(@RequestParam(required = false) String termoBusca) {
        var registros = servico.get(termoBusca);
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ProfissionalDto> get(@PathVariable Long id) {
        var registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<ProfissionalDto> insert(@RequestBody @Validated(OnInsert.class) ProfissionalDto objeto) {
        var objetoConvertido = mapper.toEntity(objeto);
        var registro = servico.save(objetoConvertido);
        var dto = mapper.toDto(registro);
        return ResponseEntity.created(null).body(dto);
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<ProfissionalDto> update(@RequestBody @Validated(OnUpdate.class) ProfissionalDto objeto) {
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
    
}
