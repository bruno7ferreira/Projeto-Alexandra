package com.example.demo.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.Agenda;
import com.example.demo.model.Cliente;
import com.example.demo.repository.AgendaRepository;
import com.example.demo.repository.ClienteRepository;

@Controller
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private AgendaRepository agendaRepo;

    @Autowired
    private ClienteRepository clienteRepo;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("agendamentos", agendaRepo.findAll());
        model.addAttribute("clientes", clienteRepo.findAll());
        return "agenda";
    }

    @PostMapping("/salvar")
    public String salvar(
        @RequestParam String clienteCpf,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime horarioAtendimento,
        @RequestParam String procedimento
    ) {
        Cliente c = clienteRepo.findById(clienteCpf).orElseThrow();
        Agenda ag = new Agenda();
        ag.setCliente(c);
        ag.setHorarioAtendimento(horarioAtendimento);
        ag.setProcedimento(procedimento);
        agendaRepo.save(ag);
        return "redirect:/agenda?success";
    }
}