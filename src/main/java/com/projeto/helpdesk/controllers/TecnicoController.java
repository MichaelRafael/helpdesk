package com.projeto.helpdesk.controllers;

import com.projeto.helpdesk.dtos.TecnicoDTO;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tecnico")
public class TecnicoController {

    @Autowired
    public TecnicoService tecnicoService;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
        Tecnico tecnico = tecnicoService.findById(id);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDTO>> findAll() {
        List<Tecnico> list = tecnicoService.findAll();
        return ResponseEntity.ok().body(list.stream().map(x -> new TecnicoDTO(x)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<TecnicoDTO> save(@Valid @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoService.save(tecnicoDTO);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TecnicoDTO> upDate(@Valid @PathVariable Integer id, @RequestBody TecnicoDTO tecnicoDTO) {
        Tecnico tecnico = tecnicoService.upDate(id, tecnicoDTO);
        return ResponseEntity.ok().body(new TecnicoDTO(tecnico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tecnicoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

