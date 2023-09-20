package com.projeto.helpdesk.services;

import com.projeto.helpdesk.dtos.TecnicoDTO;
import com.projeto.helpdesk.exceptions.DataIntegrityViolationException;
import com.projeto.helpdesk.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.models.Pessoa;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.repositories.PessoaRepository;
import com.projeto.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        Optional<Tecnico> tecnico = tecnicoRepository.findById(id);
        return tecnico.orElseThrow(() -> new ObjectNotFoundException("Técnico não encontrado"));
    }

    public List<Tecnico> findAll() {
        return tecnicoRepository.findAll();
    }

    public Tecnico save(TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(null);
        validaPorCpfEmail(tecnicoDTO);
        Tecnico tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);
    }

    public Tecnico upDate(Integer id, TecnicoDTO tecnicoDTO) {
        tecnicoDTO.setId(id);
        Tecnico tecnico = findById(id);
        validaPorCpfEmail(tecnicoDTO);
        tecnico = new Tecnico(tecnicoDTO);
        return tecnicoRepository.save(tecnico);

    }

    public void delete(Integer id) {
        Tecnico tecnico = findById(id);
        if (!tecnico.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Técnico possui chamados, não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    private void validaPorCpfEmail(TecnicoDTO tecnicoDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(tecnicoDTO.getCpf());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnicoDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        pessoa = pessoaRepository.findByEmail(tecnicoDTO.getEmail());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), tecnicoDTO.getId())) {
            throw new DataIntegrityViolationException("EMAIL já cadastrado na base de dados!");
        }
    }
}
