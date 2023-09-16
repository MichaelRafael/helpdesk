package com.projeto.helpdesk.services;

import com.projeto.helpdesk.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }
}
