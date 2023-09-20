package com.projeto.helpdesk.controllers;

import com.projeto.helpdesk.dtos.ClienteDTO;
import com.projeto.helpdesk.dtos.TecnicoDTO;
import com.projeto.helpdesk.models.Cliente;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.services.ClienteService;
import com.projeto.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    public ClienteService clienteService;

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente cliente = clienteService.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<Cliente> list = clienteService.findAll();
        return ResponseEntity.ok().body(list.stream().map(x -> new ClienteDTO(x)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> save(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.save(clienteDTO);
        //URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId()).toUri();
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> upDate(@Valid @PathVariable Integer id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = clienteService.upDate(id, clienteDTO);
        return ResponseEntity.ok().body(new ClienteDTO(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }

}

