package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    // Consulta otimizada para evitar LazyInitializationException
    @Query("SELECT a FROM Agenda a LEFT JOIN FETCH a.cliente ORDER BY a.horarioAtendimento ASC")
    List<Agenda> findAllWithClientes();
}
