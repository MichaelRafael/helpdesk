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
        Tecnico tec2 = new Tecnico(null, "Roberto Alves", "84647581039", "roberto_alves@hotmail.com", "123");
        Tecnico tec3 = new Tecnico(null, "Camila Pitanga", "66907000032", "camila-pitanga@hotmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        Tecnico tec4 = new Tecnico(null, "Magno Malta", "81406873080", "magno_malta@hotmail.com", "123");

        Cliente cli1 = new Cliente(null, "Rosangela Conceição", "04448004414", "rosangela@hotmail.com", "123");
        Cliente cli2 = new Cliente(null, "José da Manga", "41406756083", "jose_manga@hotmail.com", "123");
        Cliente cli3 = new Cliente(null, "Rodrigo Costa", "71831608073", "rodrigo_costa@hotmail.com", "123");
        Cliente cli4 = new Cliente(null, "Amanda Costa", "37941111098", "amanda_costa@hotmail.com", "123");

        Chamado chamado1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro chamado", tec1, cli1);
        Chamado chamado2 = new Chamado(null, Prioridade.BAIXA, Status.ABERTO, "Chamado 02", "Segundo chamado", tec1, cli2);
        Chamado chamado3 = new Chamado(null, Prioridade.BAIXA, Status.ANDAMENTO, "Chamado 03", "Terceiro chamado", tec3, cli3);
        Chamado chamado4 = new Chamado(null, Prioridade.MEDIA, Status.ABERTO, "Chamado 04", "Quarto chamado", tec2, cli4);
        Chamado chamado5 = new Chamado(null, Prioridade.ALTA, Status.ANDAMENTO, "Chamado 05", "Quinto chamado", tec4, cli4);

        tecnicoRepository.saveAll(List.of(tec1, tec2, tec3, tec4));

        clienteRepository.saveAll(List.of(cli1, cli2, cli3, cli4));

        chamadoRepository.saveAll(List.of(chamado1, chamado2, chamado3, chamado4, chamado5));
    }
}
