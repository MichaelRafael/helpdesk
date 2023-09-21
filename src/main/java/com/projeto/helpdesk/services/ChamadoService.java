package com.projeto.helpdesk.services;

import com.projeto.helpdesk.dtos.ChamadoDTO;
import com.projeto.helpdesk.enuns.Prioridade;
import com.projeto.helpdesk.enuns.Status;
import com.projeto.helpdesk.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.models.Chamado;
import com.projeto.helpdesk.models.Cliente;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository chamadoRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> chamado = chamadoRepository.findById(id);
        return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado!"));
    }

    public List<Chamado> findAll() {
        return chamadoRepository.findAll();
    }

    public Chamado save(ChamadoDTO chamadoDTO) {
        return chamadoRepository.save(newChamado(chamadoDTO));

    }

    public Chamado upDate(Integer id, ChamadoDTO chamadoDTO) {
        chamadoDTO.setId(id);
        Chamado chamado = findById(id);
        chamado = newChamado(chamadoDTO);
        return chamadoRepository.save(chamado);
    }

    private Chamado newChamado(ChamadoDTO chamadoDTO) {
        Tecnico tecnico = tecnicoService.findById(chamadoDTO.getTecnico());
        Cliente cliente = clienteService.findById(chamadoDTO.getCliente());

        Chamado chamado = new Chamado();
        if (chamadoDTO.getId() != null) {
            chamado.setId(chamadoDTO.getId());
        }

        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(chamadoDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(chamadoDTO.getStatus()));
        if (chamado.getStatus().getCodigo().equals(2)) {
            chamado.setDataFechamento(LocalDate.now());
        }
        chamado.setTitulo(chamadoDTO.getTitulo());
        chamado.setObservacoes(chamadoDTO.getObservacoes());
        return chamado;
    }
}
