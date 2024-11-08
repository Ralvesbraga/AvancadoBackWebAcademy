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

import br.ufac.sgcmapi.model.Paciente;
import br.ufac.sgcmapi.service.PacienteService;

@RestController
@RequestMapping("/paciente")
public class PacienteController implements ICrudController<Paciente> {

    private final PacienteService servico;

    public PacienteController(PacienteService servico) {
        this.servico = servico;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<Paciente>> get(@RequestParam(required = false) String termoBusca) {
        var registros = servico.get(termoBusca);
        return ResponseEntity.ok(registros);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Paciente> get(@PathVariable Long id) {
        var registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(registro);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<Paciente> insert(@RequestBody Paciente objeto) {
        var registro = servico.save(objeto);
        return ResponseEntity.created(null).body(registro);
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<Paciente> update(@RequestBody Paciente objeto) {
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
