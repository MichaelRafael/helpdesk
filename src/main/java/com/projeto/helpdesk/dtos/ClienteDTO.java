package com.projeto.helpdesk.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.projeto.helpdesk.enuns.Perfil;
import com.projeto.helpdesk.models.Cliente;
import com.projeto.helpdesk.models.Tecnico;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO {

    protected Integer id;
    @NotBlank(message = "O campo NOME é requerido")
    protected String nome;

    @NotBlank(message = "O campo CPF é requerido")
    protected String cpf;

    @NotBlank(message = "O campo EMAIL é requerido")
    protected String email;

    @NotBlank(message = "O campo SENHA é requerido")
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCriacao = LocalDate.now();

    public ClienteDTO() {
        super();
        addPerfis(Perfil.CLIENTE);
    }

    public ClienteDTO(Integer id, String nome, String cpf, String email, String senha,
                      Set<Integer> perfis, LocalDate dataCriacao) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.perfis = perfis;
        this.dataCriacao = dataCriacao;
        addPerfis(Perfil.CLIENTE);
    }

    public ClienteDTO(Cliente cliente) {
        super();
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.senha = cliente.getSenha();
        this.perfis = cliente.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
        this.dataCriacao = cliente.getDataCriacao();
        addPerfis(Perfil.CLIENTE);
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfis(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
