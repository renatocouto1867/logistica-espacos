package com.ntw.logistica_espacos.model.repository;

import com.ntw.logistica_espacos.model.entity.Recurso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecursoRepository extends JpaRepository<Recurso, Long> {

}
