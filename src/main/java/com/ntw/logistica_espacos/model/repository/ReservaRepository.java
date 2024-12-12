package com.ntw.logistica_espacos.model.repository;

import com.ntw.logistica_espacos.model.entity.Reserva;
import com.ntw.logistica_espacos.model.entity.Usuario;
import com.ntw.logistica_espacos.model.entity.enuns.StatusReserva;
import com.ntw.logistica_espacos.model.entity.enuns.TipoEspaco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    @Query("SELECT r FROM Reserva r WHERE r.espaco.id = :espacoId " +
            "AND ((:dataInicio BETWEEN r.dataInicio AND r.dataTermino) " +
            "OR (:dataTermino BETWEEN r.dataInicio AND r.dataTermino) " +
            "OR (r.dataInicio BETWEEN :dataInicio AND :dataTermino))")
    List<Reserva> findConflitosDeHorario(@Param("espacoId") Long espacoId,
                                         @Param("dataInicio") LocalDateTime dataInicio,
                                         @Param("dataTermino") LocalDateTime dataTermino);

    //método para buscar reservas de um usuário
    List<Reserva> findByResponsavel(Usuario responsavel);


   //Relatórios
   @Query("SELECT r FROM Reserva r " +
           "JOIN r.espaco e " +
           "JOIN r.responsavel resp " +
           "WHERE (:dataInicio IS NULL OR r.dataInicio >= :dataInicio) " +
           "AND (:dataTermino IS NULL OR r.dataTermino <= :dataTermino) " +
           "AND (:tipoEspaco IS NULL OR e.tipo = :tipoEspaco) " +
           "AND (:nomeResponsavel IS NULL OR resp.nome LIKE %:nomeResponsavel%) " +
           "AND (:statusReserva IS NULL OR r.status = :statusReserva)")
   List<Reserva> findAllByFilters(
           LocalDateTime dataInicio,
           LocalDateTime dataTermino,
           @Param("tipoEspaco") TipoEspaco tipoEspaco, // Alteração aqui
           String nomeResponsavel,
           StatusReserva statusReserva);
}
