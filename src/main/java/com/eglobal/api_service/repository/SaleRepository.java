package com.eglobal.api_service.repository;

import com.eglobal.api_service.entity.SaleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SaleRepository extends JpaRepository<SaleEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE SaleEntity s SET s.estatus = :estatus WHERE s.id = :id AND s.referencia = :referencia")
    int actualizarEstatus(@Param("id") Long id, @Param("referencia") String referencia, @Param("estatus") String estatus);

    Optional<SaleEntity> findByIdAndReferencia(Long id, String referencia);
}
