package com.example.demo.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento, Long> {

    /**
     * Retorna todos os procedimentos ordenados pela data decrescente.
     */
    List<Procedimento> findAllByOrderByDataDesc();

    /**
     * Retorna todos os procedimentos de um cliente, ordenados pela data mais recente.
     */
    List<Procedimento> findByClienteCpfOrderByDataDesc(String cpf);

    /**
     * Retorna todos os procedimentos de um cliente realizados após a data fornecida.
     * Útil para histórico (por ex., últimos 6 meses).
     */
    List<Procedimento> findByClienteCpfAndDataAfterOrderByDataDesc(
        @Param("cpf") String cpf,
        @Param("dataInicial") LocalDate dataInicial
    );

    /**
     * Soma dos valores dos procedimentos em um dia específico.
     * Se não houver nenhum, retorna 0.
     */
    @Query("SELECT COALESCE(SUM(p.valor), 0) FROM Procedimento p WHERE p.data = :data")
    Double totalPorDia(@Param("data") LocalDate data);

    /**
     * Soma dos valores dos procedimentos em um intervalo de datas (inclusive).
     * Se não houver nenhum, retorna 0.
     */
    @Query("SELECT COALESCE(SUM(p.valor), 0) FROM Procedimento p WHERE p.data BETWEEN :inicio AND :fim")
    Double totalEntreDatas(
        @Param("inicio") LocalDate inicio,
        @Param("fim") LocalDate fim
    );
}
