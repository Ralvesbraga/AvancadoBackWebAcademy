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

import br.ufac.sgcmapi.controller.dto.UsuarioDto;
import br.ufac.sgcmapi.controller.mapper.UsuarioMapper;
import br.ufac.sgcmapi.model.Usuario;
import br.ufac.sgcmapi.service.UsuarioService;

@RestController
@RequestMapping("/config/usuario")
public class UsuarioController implements ICrudController<Usuario> {

    private final UsuarioService servico;
    private final UsuarioMapper mapper;

    public UsuarioController(
            UsuarioService servico,
            UsuarioMapper mapper) {
        this.servico = servico;
        this.mapper = mapper;
    }

    @Override
    @GetMapping("/consultar")
    public ResponseEntity<List<UsuarioDto>> get(@RequestParam(required = false) String termoBusca) {
        var registros = servico.get(termoBusca);
        // var dtos = new ArrayList<UsuarioDto>();
        // for (Usuario item : registros) {
        //     dtos.add(new UsuarioDto(item.getId(),
        //                             item.getNomeCompleto(),
        //                             item.getNomeUsuario(),
        //                             item.getPapel().name(),
        //                             item.isAtivo()));
        // }
        // var dtos = registros.stream().map(item -> UsuarioDto.toDto(item)).toList();
        var dtos = registros.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto> get(@PathVariable Long id) {
        var registro = servico.get(id);
        if (registro == null) {
            return ResponseEntity.notFound().build();
        }
        var dto = mapper.toDto(registro);
        return ResponseEntity.ok(dto);
    }

    @Override
    @PostMapping("/inserir")
    public ResponseEntity<UsuarioDto> insert(@RequestBody Usuario objeto) {
        var registro = servico.save(objeto);
        var dto = mapper.toDto(registro);
        return ResponseEntity.created(null).body(dto);
    }

    @Override
    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioDto> update(@RequestBody Usuario objeto) {
        var registro = servico.save(objeto);
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
