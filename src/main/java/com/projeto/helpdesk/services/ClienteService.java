package com.projeto.helpdesk.services;

import com.projeto.helpdesk.dtos.ClienteDTO;
import com.projeto.helpdesk.dtos.TecnicoDTO;
import com.projeto.helpdesk.exceptions.DataIntegrityViolationException;
import com.projeto.helpdesk.exceptions.ObjectNotFoundException;
import com.projeto.helpdesk.models.Cliente;
import com.projeto.helpdesk.models.Pessoa;
import com.projeto.helpdesk.models.Tecnico;
import com.projeto.helpdesk.repositories.ClienteRepository;
import com.projeto.helpdesk.repositories.PessoaRepository;
import com.projeto.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente save(ClienteDTO clienteDTO) {
        clienteDTO.setId(null);
        clienteDTO.setSenha(bCryptPasswordEncoder.encode(clienteDTO.getSenha()));
        validaPorCpfEmail(clienteDTO);
        Cliente cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);
    }

    public Cliente upDate(Integer id, ClienteDTO clienteDTO) {
        clienteDTO.setId(id);
        Cliente cliente = findById(id);
        validaPorCpfEmail(clienteDTO);
        cliente = new Cliente(clienteDTO);
        return clienteRepository.save(cliente);

    }

    public void delete(Integer id) {
        Cliente cliente = findById(id);
        if (!cliente.getChamados().isEmpty()) {
            throw new DataIntegrityViolationException("Cliente possui chamados, não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    private void validaPorCpfEmail(ClienteDTO clienteDTO) {
        Optional<Pessoa> pessoa = pessoaRepository.findByCpf(clienteDTO.getCpf());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), clienteDTO.getId())) {
            throw new DataIntegrityViolationException("CPF já cadastrado na base de dados!");
        }

        pessoa = pessoaRepository.findByEmail(clienteDTO.getEmail());
        if (pessoa.isPresent() && !Objects.equals(pessoa.get().getId(), clienteDTO.getId())) {
            throw new DataIntegrityViolationException("EMAIL já cadastrado na base de dados!");
        }
    }
}
