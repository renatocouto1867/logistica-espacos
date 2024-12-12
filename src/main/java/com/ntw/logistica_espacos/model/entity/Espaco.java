package com.ntw.logistica_espacos.model.entity;

import com.ntw.logistica_espacos.model.entity.enuns.StatusEspaco;
import com.ntw.logistica_espacos.model.entity.enuns.TipoEspaco;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Espaco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoEspaco tipo;

    private Integer capacidade;

    private String localizacao;

    @Enumerated(EnumType.STRING)
    private StatusEspaco status;

    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "espaco_recurso",
            joinColumns = @JoinColumn(name = "espaco_id"),
            inverseJoinColumns = @JoinColumn(name = "recurso_id")
    )
    private List<Recurso> recursosDisponiveis;

    private LocalDate dataCadastro;
    private LocalDate dataProcedimento;
    private String notasAdicionais;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoEspaco getTipo() {
        return tipo;
    }

    public void setTipo(TipoEspaco tipo) {
        this.tipo = tipo;
    }

    public Integer getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(Integer capacidade) {
        this.capacidade = capacidade;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public StatusEspaco getStatus() {
        return status;
    }

    public void setStatus(StatusEspaco status) {
        this.status = status;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Recurso> getRecursosDisponiveis() {
        return recursosDisponiveis;
    }

    public void setRecursosDisponiveis(List<Recurso> recursosDisponiveis) {
        this.recursosDisponiveis = recursosDisponiveis;
    }

    public LocalDate getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public LocalDate getDataProcedimento() {
        return dataProcedimento;
    }

    public void setDataProcedimento(LocalDate dataProcedimento) {
        this.dataProcedimento = dataProcedimento;
    }

    public String getNotasAdicionais() {
        return notasAdicionais;
    }

    public void setNotasAdicionais(String notasAdicionais) {
        this.notasAdicionais = notasAdicionais;
    }
}
