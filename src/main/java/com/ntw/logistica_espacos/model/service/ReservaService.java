package com.ntw.logistica_espacos.model.service;


import com.ntw.logistica_espacos.model.entity.Reserva;
import com.ntw.logistica_espacos.model.entity.dto.RelatorioReservaDTO;
import com.ntw.logistica_espacos.model.entity.enuns.StatusReserva;
import com.ntw.logistica_espacos.model.entity.enuns.TipoEspaco;
import com.ntw.logistica_espacos.model.repository.ReservaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public Reserva cadastrarReserva(Reserva reserva) {
        verificarConflitos(reserva); // Validação de conflitos
        reserva.setStatus(StatusReserva.PENDENTE); // Status inicial
        return reservaRepository.save(reserva);
    }

    public Reserva editarReserva(Long id, Reserva novaReserva) {
        Reserva reservaExistente = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada!"));

        verificarConflitos(novaReserva);
        reservaExistente.setNomeEvento(novaReserva.getNomeEvento());
        reservaExistente.setDataInicio(novaReserva.getDataInicio());
        reservaExistente.setDataTermino(novaReserva.getDataTermino());
        reservaExistente.setPeriodo(novaReserva.getPeriodo());
        reservaExistente.setQuantidadePessoas(novaReserva.getQuantidadePessoas());
        reservaExistente.setObjetivo(novaReserva.getObjetivo());
        reservaExistente.setObservacoesAdicionais(novaReserva.getObservacoesAdicionais());
        reservaExistente.setStatus(novaReserva.getStatus());

        return reservaRepository.save(reservaExistente);
    }

    public void cancelarReserva(Long id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));
        reserva.setStatus(StatusReserva.CANCELADA);
        reservaRepository.save(reserva);
    }

    public List<Reserva> obterReservas() {
        return reservaRepository.findAll();
    }

    public Reserva obterPorId(Long id) {
        return reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva não encontrada"));
    }

    // aqui e Verificado os conflitos de horário
    private void verificarConflitos(Reserva novaReserva) {
        List<Reserva> reservasConflitantes = reservaRepository
                .findConflitosDeHorario(novaReserva.getEspaco().getId(),
                        novaReserva.getDataInicio(),
                        novaReserva.getDataTermino());

        if (!reservasConflitantes.isEmpty()) {
            throw new IllegalArgumentException("Já existe uma reserva para esse espaço no período selecionado");
        }
    }

    // Método para gerar relatório com filtros
    public List<Reserva> gerarRelatorio(
            LocalDateTime dataInicio, LocalDateTime dataTermino,
            TipoEspaco tipoEspaco, String nomeResponsavel,

            StatusReserva statusReserva) {

        return reservaRepository.findAllByFilters(dataInicio, dataTermino, tipoEspaco, nomeResponsavel, statusReserva);
    }

    // Método para gerar relatório com filtros com DTO
    public List<RelatorioReservaDTO> gerarRelatorioDTO(List<Reserva> reservas) {
        List<RelatorioReservaDTO> relatorioDTOs = new ArrayList<>();
        for (Reserva reserva : reservas) {
            RelatorioReservaDTO dto = new RelatorioReservaDTO();
            dto.setCodigoReserva(reserva.getId());
            dto.setNomeEvento(reserva.getNomeEvento());
            dto.setTipoEvento(reserva.getTipoEvento().toString());
            dto.setNomeEspaco(reserva.getEspaco().getNome());
            dto.setTipoEspaco(reserva.getEspaco().getTipo().toString());
            dto.setDataReserva(reserva.getDataInicio());
            dto.setPeriodo(reserva.getPeriodo().toString());
            dto.setTurno(reserva.getPeriodo().toString());
            dto.setTotalParticipantes(reserva.getQuantidadePessoas());
            dto.setSituacaoReserva(reserva.getStatus().toString());
            dto.setNomeResponsavel(reserva.getResponsavel().getNome());

            relatorioDTOs.add(dto);
        }
        return relatorioDTOs;
    }
}
