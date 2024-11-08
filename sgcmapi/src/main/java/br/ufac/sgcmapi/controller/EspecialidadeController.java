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

import br.ufac.sgcmapi.model.Especialidade;
import br.ufac.sgcmapi.service.EspecialidadeService;

@RestController
@RequestMapping("/config/especialidade")
public class EspecialidadeController implements ICrudController<Especialidade> {

    private final EspecialidadeService servico;

    public EspecialidadeController(EspecialidadeService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<Especialidade>> get(@RequestParam(required = false) String termoBusca) {
        var registros = servico.get(termoBusca);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Especialidade> get(@PathVariable Long id) {
        var registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<Especialidade> insert(@RequestBody Especialidade objeto) {
        var registro = servico.save(objeto);
        return ResponseEntity.created(null).body(registro);
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<Especialidade> update(@RequestBody Especialidade objeto) {
        var registro = servico.save(objeto);
        return ResponseEntity.ok(registro);
    }

    @Override
    @DeleteMapping("/remover/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servico.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
