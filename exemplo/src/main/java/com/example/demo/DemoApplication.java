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
         ClienteRepository clienteRepo,
         AgendaRepository agendaRepo,
         ProcedimentoRepository procRepo
    ) {
      return args -> {
        if (clienteRepo.count() == 0) {
          // — Cliente de exemplo —
          Cliente cli = new Cliente();
          cli.setCpf("123.456.789-00");
          cli.setNomeCompleto("Cliente Teste");
          cli.setTelefone("(11) 99999-9999");
          cli.setSexo("Masculino");
          cli.setIdade(30);
          clienteRepo.save(cli);

          // — Agendamento de exemplo —
          Agenda ag = new Agenda();
          ag.setCliente(cli);
          ag.setHorarioAtendimento(LocalDateTime.now().plusHours(2));
          ag.setProcedimento("Consulta de Rotina");
          agendaRepo.save(ag);

          // — Procedimento de exemplo —
          Procedimento pr = new Procedimento();
          pr.setCliente(cli);
          pr.setDescricao("Consulta de Rotina");
          pr.setData(LocalDate.now());
          pr.setHorario(LocalDateTime.now());
          pr.setValor(120.0);
          procRepo.save(pr);

          System.out.println("Dados iniciais carregados com sucesso!");
        }
      };
    }
}