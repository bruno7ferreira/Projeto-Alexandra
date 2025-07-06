package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.model.Agenda;
import com.example.demo.model.Cliente;
import com.example.demo.model.Procedimento;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.ClienteRepository;
import com.example.demo.repository.ProcedimentoRepository;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(
            AgendaRepository agendaRepo,
            ClienteRepository clienteRepo,
            ProcedimentoRepository procedimentoRepo) {
        return args -> {
            if (clienteRepo.count() == 0 && agendaRepo.count() == 0) {
                // — Cliente de teste —
                Cliente cliente = new Cliente();
                cliente.setCpf("123.456.789-00");
                cliente.setNome("Cliente Teste");
                cliente.setTelefone("(11) 99999-9999");
                cliente.setSexo("Masculino");
                cliente.setIdade(30);
                clienteRepo.save(cliente);

                // — Agendamento de teste —
                Agenda agenda = new Agenda();
                agenda.setCliente(cliente);
                agenda.setHorarioAtendimento(LocalDateTime.now().plusHours(2));
                agenda.setProcedimento("Consulta de Rotina");
                agendaRepo.save(agenda);

                // — Procedimento de teste —
                Procedimento procedimento = new Procedimento();
                procedimento.setDescricao("Consulta de Rotina");
                procedimento.setData(LocalDate.now());
                // ** aqui garantimos que o campo horário não fique nulo **
                procedimento.setHorario(LocalDateTime.now());
                procedimento.setValor(120.0);
                procedimento.setCliente(cliente);
                procedimentoRepo.save(procedimento);

                System.out.println("Dados iniciais carregados com sucesso!");
            }
        };
    }
}
