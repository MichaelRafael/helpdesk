package com.projeto.helpdesk.controllers;

import com.projeto.helpdesk.dtos.ChamadoDTO;
import com.projeto.helpdesk.models.Chamado;
import com.projeto.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/chamado")
public class ChamadoController {

    @Autowired
    private ChamadoService chamadoService;

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado chamado = chamadoService.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }


    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = chamadoService.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(x -> new ChamadoDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    public ResponseEntity<ChamadoDTO> save(@Valid @RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.save(chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChamadoDTO> upDate(@PathVariable Integer id,@Valid @RequestBody ChamadoDTO chamadoDTO) {
        Chamado chamado = chamadoService.upDate(id, chamadoDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(chamado));
    }

}
