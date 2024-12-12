package com.ntw.logistica_espacos.model.entity;

import com.ntw.logistica_espacos.model.entity.enuns.PeriodoEvento;
import com.ntw.logistica_espacos.model.entity.enuns.StatusReserva;
import com.ntw.logistica_espacos.model.entity.enuns.TipoEvento;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Espaco espaco;

    @ManyToOne
    private Usuario responsavel;

    @ManyToOne
    private Cliente cliente;

    private String nomeEvento;

    @Enumerated(EnumType.STRING)
    private TipoEvento tipoEvento;

    private LocalDateTime dataInicio;
    private LocalDateTime dataTermino;

    @Enumerated(EnumType.STRING)
    private PeriodoEvento periodo;

    private Integer quantidadePessoas;

    private String objetivo;
    private String observacoesAdicionais;

    @Enumerated(EnumType.STRING)
    private StatusReserva status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Espaco getEspaco() {
        return espaco;
    }

    public void setEspaco(Espaco espaco) {
        this.espaco = espaco;
    }

    public Usuario getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Usuario responsavel) {
        this.responsavel = responsavel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDateTime getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(LocalDateTime dataTermino) {
        this.dataTermino = dataTermino;
    }

    public PeriodoEvento getPeriodo() {
        return periodo;
    }

    public void setPeriodo(PeriodoEvento periodo) {
        this.periodo = periodo;
    }

    public Integer getQuantidadePessoas() {
        return quantidadePessoas;
    }

    public void setQuantidadePessoas(Integer quantidadePessoas) {
        this.quantidadePessoas = quantidadePessoas;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public String getObservacoesAdicionais() {
        return observacoesAdicionais;
    }

    public void setObservacoesAdicionais(String observacoesAdicionais) {
        this.observacoesAdicionais = observacoesAdicionais;
    }

    public StatusReserva getStatus() {
        return status;
    }

    public void setStatus(StatusReserva status) {
        this.status = status;
    }
}
