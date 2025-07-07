package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Procedimento;

public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long> {
    List<Procedimento> findByCliente_CpfOrderByHorarioDesc(String cpf);
}