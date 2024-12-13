package com.ntw.logistica_espacos.controller;

import com.ntw.logistica_espacos.model.entity.Reserva;
import com.ntw.logistica_espacos.model.entity.dto.RelatorioReservaDTO;
import com.ntw.logistica_espacos.model.entity.enuns.StatusReserva;
import com.ntw.logistica_espacos.model.entity.enuns.TipoEspaco;
import com.ntw.logistica_espacos.model.service.ReservaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/reserva")
@Tag(name = "Reserva", description = "Métodos disponíveis para Reserva")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @PostMapping
    public ResponseEntity<Reserva> cadastrarReserva(@RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.cadastrarReserva(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> editarReserva(@PathVariable Long id, @RequestBody Reserva reserva) {
        return ResponseEntity.ok(reservaService.editarReserva(id, reserva));
    }

    //so desativo a reserva
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelarReserva(@PathVariable Long id) {
        reservaService.cancelarReserva(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Reserva>> obterReservas() {
        return ResponseEntity.ok(reservaService.obterReservas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obterPorId(@PathVariable Long id) {
        return ResponseEntity.ok(reservaService.obterPorId(id));
    }

    @GetMapping("/relatorio")
    public ResponseEntity<List<Reserva>> gerarRelatorio(
            @RequestParam(required = false) LocalDateTime dataInicio,
            @RequestParam(required = false) LocalDateTime dataTermino,
            @RequestParam(required = false) TipoEspaco tipoEspaco,
            @RequestParam(required = false) String nomeResponsavel,
            @RequestParam(required = false) StatusReserva statusReserva) {

        List<Reserva> relatorio = reservaService.gerarRelatorio(dataInicio, dataTermino, tipoEspaco, nomeResponsavel, statusReserva);
        return ResponseEntity.ok(relatorio);
    }

    //como é uma API, não deveolve uma tabela, ai fiz essa versão simplificada.
    @GetMapping("/relatorio/tabela")
    public ResponseEntity<List<RelatorioReservaDTO>> gerarRelatorioTabela(
            @RequestParam(required = false) LocalDateTime dataInicio,
            @RequestParam(required = false) LocalDateTime dataTermino,
            @RequestParam(required = false) TipoEspaco tipoEspaco,
            @RequestParam(required = false) String nomeResponsavel,
            @RequestParam(required = false) StatusReserva statusReserva) {

        List<Reserva> reservas = reservaService.gerarRelatorio(dataInicio, dataTermino, tipoEspaco, nomeResponsavel, statusReserva);
        List<RelatorioReservaDTO> relatorioDTOs = reservaService.gerarRelatorioDTO(reservas);
        return ResponseEntity.ok(relatorioDTOs);
    }

}

