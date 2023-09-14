package com.projeto.helpdesk.services;

import com.projeto.helpdesk.enuns.Perfil;
import com.projeto.helpdesk.enuns.Prioridade;
import com.projeto.helpdesk.enuns.Status;
import com.projeto.helpdesk.models.Chamado;
import com.projeto.helpdesk.models.Cliente;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.repositories.ChamadoRepository;
import com.projeto.helpdesk.repositories.ClienteRepository;
import com.projeto.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {

        Tecnico tec1 = new Tecnico(null, "Michael Rafael", "04131638495", "michael-ras@hotmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);

        Cliente cli1 = new Cliente(null, "Rosangela Conceição", "04448004414", "rosangela@hotmail.com", "123");

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamdo", tec1, cli1);

        tecnicoRepository.saveAll(List.of(tec1));

        clienteRepository.saveAll(List.of(cli1));

        chamadoRepository.saveAll(List.of(chamado1));
    }
}
