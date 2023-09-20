package com.projeto.helpdesk.services;

import com.projeto.helpdesk.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.models.Chamado;
import com.projeto.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado!"));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }
}
